package com.hanains.mysite.service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.hanains.mysite.dao.BoardDao;
import com.hanains.mysite.vo.BoardVo;

@Service
public class BoardService {

	@Autowired
	private BoardDao dao;

	public Model list(Model model, String pg, String kwd, String searchType) {
		int page = 1;
		if (pg != null)
			page = Integer.parseInt(pg);
		model.addAttribute("list", dao.getListPage(page, kwd, searchType));
		model.addAttribute("boardSize", dao.getBoardSize(kwd, searchType));
		model.addAttribute("pageSize", dao.PAGE_ROW);

		return model;
	}

	public BoardVo getView(long no) {
		return dao.getView(no);
	}

	public void upCount(long no) {
		dao.upCount(no);
	}

	public void delete(long no) {
		dao.delete(no);
	}

	public void modify(BoardVo vo) {
		dao.modify(vo);
	}

	public void write(BoardVo vo, MultipartFile file1,HttpSession session) {
		if(vo.getGroup_no()==0){
			vo.setGroup_no(dao.getMaxGroup() + 1);
			vo.setOrder_no(1);
			vo.setDepth(0);
		} else {
			if (vo.getOrder_no() == 1)
				vo.setOrder_no(dao.getMaxOrder(vo.getGroup_no()) + 1);
			vo.setDepth(vo.getDepth() + 1);
		}
		String fileName = fileSave(file1, session);
		vo.setFileName(fileName);
		dao.insert(vo);
	}

	private String fileSave(MultipartFile file1,HttpSession session) {
		
		if (!file1.isEmpty()) {
			String root = session.getServletContext().getRealPath("/");
			String path = root+"\\upload";
			File dir = new File(path);
			if(!dir.isDirectory()) dir.mkdirs();
			
			String fileOriginalName = file1.getOriginalFilename();
			String extName = fileOriginalName.substring(fileOriginalName.lastIndexOf(".") + 1,
					fileOriginalName.length());
			String fileName = file1.getName();
			Long size = file1.getSize();

			String saveFileName = genSaveFileName(extName);
			String url = "/product-images/" + saveFileName;

			System.out.println(" ######## fileOriginalName : " + fileOriginalName);
			System.out.println(" ######## fileName : " + fileName);
			System.out.println(" ######## fileSize : " + size);
			System.out.println(" ######## fileExtensionName : " + extName);
			System.out.println(" ######## saveFileName : " + saveFileName);
			System.out.println(" ######## url : " + url);

			writeFile(file1, path, saveFileName);
			return saveFileName;
		}
		return null;
	}

	private void writeFile(MultipartFile file, String path, String fileName) {
		FileOutputStream fos = null;
		try {
			byte fileData[] = file.getBytes();
			fos = new FileOutputStream(path + "\\" + fileName);
			fos.write(fileData);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (Exception e) {
				}
			}
		}
	}

	private String genSaveFileName(String extName) {

		Calendar calendar = Calendar.getInstance();
		String fileName = "";

		fileName += calendar.get(Calendar.YEAR);
		fileName += calendar.get(Calendar.MONTH+1);
		fileName += calendar.get(Calendar.DATE);
		fileName += calendar.get(Calendar.HOUR);
		fileName += calendar.get(Calendar.MINUTE);
		fileName += calendar.get(Calendar.SECOND);
		fileName += calendar.get(Calendar.MILLISECOND);
		fileName += ("." + extName);

		return fileName;
	}

}
