package com.hanains.mysite.vo;

public class BoardVo {
		private long no;
		private String title;
		private String content;
		private long member_no;
		private long view_cnt;
		private String reg_date;
		private String member_name;
		private long group_no;
		private long order_no;
		private long depth;
		private String fileName;
		private String fileOriginalName;
		
		public BoardVo(){}

		public long getNo() {
			return no;
		}

		public void setNo(long no) {
			this.no = no;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public long getMember_no() {
			return member_no;
		}

		public void setMember_no(long member_no) {
			this.member_no = member_no;
		}

		public long getView_cnt() {
			return view_cnt;
		}

		public void setView_cnt(long view_cnt) {
			this.view_cnt = view_cnt;
		}

		public String getReg_date() {
			return reg_date;
		}

		public void setReg_date(String reg_date) {
			this.reg_date = reg_date;
		}

		public String getMember_name() {
			return member_name;
		}

		public void setMember_name(String member_name) {
			this.member_name = member_name;
		}
		
		public long getGroup_no() {
			return group_no;
		}

		public void setGroup_no(long group_no) {
			this.group_no = group_no;
		}

		public long getOrder_no() {
			return order_no;
		}

		public void setOrder_no(long order_no) {
			this.order_no = order_no;
		}

		public long getDepth() {
			return depth;
		}

		public void setDepth(long depth) {
			this.depth = depth;
		}

		public String getFileName() {
			return fileName;
		}

		public void setFileName(String fileName) {
			this.fileName = fileName;
		}

		public String getFileOriginalName() {
			return fileOriginalName;
		}

		public void setFileOriginalName(String fileOriginalName) {
			this.fileOriginalName = fileOriginalName;
		}

		@Override
		public String toString() {
			return "BoardVo [no=" + no + ", title=" + title + ", content=" + content + ", member_no=" + member_no
					+ ", view_cnt=" + view_cnt + ", reg_date=" + reg_date + ", member_name=" + member_name
					+ ", group_no=" + group_no + ", order_no=" + order_no + ", depth=" + depth + ", fileName="
					+ fileName + ", fileOriginalName=" + fileOriginalName + "]";
		}
		
}
