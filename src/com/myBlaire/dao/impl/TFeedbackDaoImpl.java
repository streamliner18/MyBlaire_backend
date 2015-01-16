package com.myBlaire.dao.impl;

import java.util.List;
import java.util.Map;

import com.myBlaire.dao.BaseDao;
import com.myBlaire.dao.TFeedbackDao;
import com.myBlaire.domain.TFeedback;
import com.myBlaire.util.PageInfo;

public class TFeedbackDaoImpl implements TFeedbackDao {
	private BaseDao baseDao;
	
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public PageInfo<Map<String, String>> getList(Integer page,Integer size) throws Exception {
		// TODO Auto-generated method stub
		String sql="select f.id as id,f.content as content,f.client_version as clientVersion,u.user_name as userName,date_format(f.feedback_time,'%Y-%m-%d %H:%m:%s')  as feedbackTime,f.app_veraion as appVeraion from " +
				"t_feedback f inner join t_user u on f.feedback_user=u.user_Id order by f.feedback_time  desc";
		
		String sql_count="select count(*)  from " +
				"t_feedback f inner join t_user u on f.feedback_user=u.user_Id order by f.feedback_time desc";
		List<Map<String, String>> rows=baseDao.execquerySql2(sql, null, page, size);
		List list_count=baseDao.execquerySql(sql_count, null, -1, -1);
		Integer total=0;
		if(list_count.size()>0){
			total=Integer.valueOf(list_count.get(0).toString());
		}
		return new PageInfo<Map<String,String>>(rows, total);
	}

}
