-- 글쓰기
insert
  into board
values ( board_no_seq.nextval, '제목입니다.', '내용입니다.', 2, 0, SYSDATE );

-- 글보기
select no, title, content, member_no
  from board
 where no=1;

-- 조회수 늘리기
update board
   set view_cnt = view_cnt + 1		
 where no=1;

-- 글수정
update board
   set title='수정된 제목',
	   content='스정된 내용'
 where no=1;

-- 삭제
delete
  from board 
 where no = 1;

-- commit
commit;

-- 리스트
   select a.no,
	      a.title,
	      a.member_no,
          b.name as member_name,
          a.view_cnt,
          to_char(a.reg_date, 'yyyy-mm-dd hh:mi:ss')
     from board a,
          member b
    where a.member_no = b.no
 order by a.reg_date desc;