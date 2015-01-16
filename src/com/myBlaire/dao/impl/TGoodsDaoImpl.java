package com.myBlaire.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.myBlaire.dao.BaseDao;
import com.myBlaire.dao.TGoodsDao;
import com.myBlaire.domain.TGoods;
import com.myBlaire.util.DateUtil;
import com.myBlaire.util.PageInfo;

public class TGoodsDaoImpl implements TGoodsDao {
	private BaseDao baseDao;
	
	
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}


	public PageInfo<TGoods> getList(TGoods good, Integer page, Integer size,Date beginDate,Date endDate) throws Exception{
		// TODO Auto-generated method stub
		StringBuffer hql=new StringBuffer("from TGoods where 1=1");
		StringBuffer hql_count=new StringBuffer("select count(*) from TGoods where 1=1");

		if(StringUtils.isNotBlank(good.getGoodName())){
			hql.append(" and goodName like '%"+good.getGoodName()+"%' ");
			hql_count.append(" and goodName like '%"+good.getGoodName()+"%' ");
		}
		if(StringUtils.isNotBlank(good.getNickName())){
			hql.append(" and nickName like '%"+good.getNickName()+"%' ");
			hql_count.append(" and nickName like '%"+good.getNickName()+"%' ");
		}
		if(beginDate!=null){
			hql.append(" and addTime >=date_format('"+DateUtil.dateConvertYMDHMS(beginDate)+"','%Y-%c-%d %h:%i:%s')");
			hql_count.append(" and addTime >=date_format('"+DateUtil.dateConvertYMDHMS(beginDate)+"','%Y-%c-%d %h:%i:%s')");
		}
		
		if(endDate!=null){
			hql.append(" and addTime <=date_format('"+DateUtil.dateConvertYMDHMS(endDate)+"','%Y-%c-%d %h:%i:%s')");
			hql_count.append(" and addTime <= date_format('"+DateUtil.dateConvertYMDHMS(endDate)+"','%Y-%c-%d %h:%i:%s')");
		}
		List<TGoods> rows=baseDao.findAllByHQL(hql.toString());
		List list_count=baseDao.findAllByHQL(hql_count.toString());
		Integer total=0;
		if(list_count.size()>0){
			total=Integer.valueOf(list_count.get(0).toString());
		}
		return new PageInfo<TGoods>(rows,total);
	}


	public List<Map<String, String>> getDcitList(String type) throws Exception{
		// TODO Auto-generated method stub
		String hql="select dictKey as dictKey,dictValue as dictValue from SysDict where dictType=? and dictDisabled=?";
		
		return baseDao.findMapByHQL(hql , new Object[]{type,false}, -1, -1);
	}


	public TGoods getGoodByGoodId(String goodId) throws Exception {
		// TODO Auto-generated method stub
		
		return (TGoods) baseDao.findEnityById(TGoods.class, goodId);
	}


	public void saveorupdateGood(TGoods good) throws Exception {
		// TODO Auto-generated method stub
		baseDao.saveorupdate(good);
	}

}
