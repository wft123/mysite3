select *
from (SELECT *
		FROM (
  			SELECT rownum rnum, no, title, member_no, member_name,reg_date
    		FROM (
				 select a.no no,
						  a.title title,
						  a.member_no member_no,
						  b.name as member_name,
						  a.view_cnt view_cnt,
						  to_char(a.reg_date, 'yyyy-mm-dd hh:mi:ss') as reg_date
				 from board a,  member b
				 where a.member_no = b.no
				 order by a.reg_date desc )
			) pagetable
		where rnum <= 10
	)
where rnum >= 6;
