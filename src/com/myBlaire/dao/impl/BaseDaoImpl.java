package com.myBlaire.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.myBlaire.dao.BaseDao;

 

@SuppressWarnings("unchecked")
/**
 * T extends Serializable T其实就是一个占位符, 你也可以换成其它字母,但作为惯例 当我们需要一种类型时,我们都T来占位,表示Type ,
 * 后面的extends Serializable 表示在实际应用过程中,替换T的类型必须实现Serializable接口. PK => Primary
 * Key
 */
public class BaseDaoImpl<T extends Serializable, PK extends Serializable>
		extends HibernateDaoSupport implements BaseDao<T, PK> {
	/***************************************************************************
	 * 新增
	 **************************************************************************/
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pb.dao.BaseDao#save(T)
	 */
	public Serializable save(T entity) {
		
		return super.getHibernateTemplate().save(entity);
	}
	
	/***************************************************************************
	 * 更新
	 **************************************************************************/
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pb.dao.BaseDao#update(T)
	 */
	public void update(T t) {
		super.getHibernateTemplate().update(t);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pb.dao.BaseDao#update(java.lang.String, java.lang.Object[])
	 */
	public void update(final String hql, final Object[] params) {
		// 使用回调接口完成操作
		super.getHibernateTemplate().executeWithNativeSession(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createQuery(hql);
						System.out.println(hql);
						for (int i = 0; i < params.length; i++) {
							query.setParameter(i, params[i]);
						}
						query.executeUpdate();
						return null;
					}
				});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pb.dao.BaseDao#update(java.lang.Class, java.lang.String,
	 * java.lang.Object, java.lang.String, java.lang.Object)
	 */
	public void update(Class<T> entityClass, String pkName, Object pkValue,
			String propName, Object propValue) {
		this.update(entityClass, pkName, pkValue, new String[] { propName },
				new Object[] { propValue });
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pb.dao.BaseDao#update(java.lang.Class, java.lang.String,
	 * java.lang.Object, java.lang.String[], java.lang.Object[])
	 */
	public void update(Class<T> entityClass, String pkName, Object pkValue,
			String[] propNames, Object[] propValues) {
		if (!(propNames != null && propValues != null
				&& propNames.length == propValues.length && propNames.length > 0)) {
			throw new RuntimeException(
					"请确保提供的参数是正确的!属性名称的个数与属性值的个数必须一致!必须提供至少一个属性名称!");
		}
		String entityName = entityClass.getName();
		if (entityName.lastIndexOf(".") != -1) {
			entityName = entityName.substring(entityName.lastIndexOf(".") + 1);
		}
		String hql = "update " + entityName + " obj set ";
		for (int i = 0; i < propValues.length; i++) {
			hql += "obj." + propNames[i] + " =? and ";
		}
		if (hql.lastIndexOf("and") != -1) {
			hql = hql.substring(0, hql.lastIndexOf("and"));
		}
		hql += " where obj." + pkName + " = ?";
		Object[] ps = new Object[propValues.length + 1];
		for (int i = 0; i < propValues.length; i++) {
			ps[i] = propValues[i];
		}
		ps[ps.length - 1] = pkValue;
		this.update(hql, ps);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pb.dao.BaseDao#saveorupdate(T)
	 */
	public void saveorupdate(T entity) {
		super.getHibernateTemplate().saveOrUpdate(entity);
	}
	
	/***************************************************************************
	 * 删除
	 **************************************************************************/
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pb.dao.BaseDao#delete(T)
	 */
	public void delete(T entity) {
		super.getHibernateTemplate().delete(entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pb.dao.BaseDao#deleteById(java.lang.Class, PK)
	 */
	public void deleteById(Class<T> entityClass, PK id) {
		super.getHibernateTemplate()
				.delete(this.findEnityById(entityClass, id));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pb.dao.BaseDao#deleteByProperty(java.lang.Class,
	 * java.lang.String, java.lang.Object)
	 */
	public void deleteByProperty(final Class<T> entityClass,
			final String propName, final Object propValue) {
		this.deleteByProperty(entityClass, new String[] { propName },
				new Object[] { propValue });
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pb.dao.BaseDao#deleteByProperty(java.lang.Class,
	 * java.lang.String[], java.lang.Object[])
	 */
	public void deleteByProperty(final Class<T> entityClass,
			final String[] propNames, final Object[] propValues) {
		if (!(propNames != null && propValues != null
				&& propNames.length == propValues.length && propNames.length > 0)) {
			throw new RuntimeException(
					"请确保提供的参数是正确的!属性名称的个数与属性值的个数必须一致!必须提供至少一个属性名称!");
		}
		String entityName = entityClass.getName();
		if (entityName.lastIndexOf(".") != -1) {
			entityName = entityName.substring(entityName.lastIndexOf(".") + 1);
		}
		String hql = "delete from " + entityName + " obj where ";
		for (int i = 0; i < propNames.length; i++) {
			hql += " obj." + propNames[i] + " = ? and ";
		}
		if (hql.lastIndexOf("and") != -1) {
			hql = hql.substring(0, hql.lastIndexOf("and"));
		}
		this.update(hql, propValues);
	}
	
	/***************************************************************************
	 * 查询
	 **************************************************************************/
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pb.dao.BaseDao#findEnityById(java.lang.Class, PK)
	 */
	public T findEnityById(Class<T> entityClass, PK id) {
		return (T) super.getHibernateTemplate().get(entityClass, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pb.dao.BaseDao#findAll(java.lang.Class)
	 */
	public List<T> findAll(Class<T> entityClass) {
		return super.getHibernateTemplate().loadAll(entityClass);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pb.dao.BaseDao#findAllAsc(java.lang.Class, java.lang.String)
	 */
	public List<T> findAllAsc(Class<T> entityClass, String orderProperty) {
		return this.findAll_order(entityClass, orderProperty, "asc");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pb.dao.BaseDao#findAllDesc(java.lang.Class, java.lang.String)
	 */
	public List<T> findAllDesc(Class<T> entityClass, String orderProperty) {
		return this.findAll_order(entityClass, orderProperty, "desc");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pb.dao.BaseDao#findAll_order(java.lang.Class, java.lang.String,
	 * java.lang.String)
	 */
	public List<T> findAll_order(Class<T> entityClass, String orderProperty,
			String orderType) {
		String hql = "from " + entityClass.getName() + " obj order by obj."
				+ orderProperty + " " + orderType;
		return this.findAllByHQL(hql);
	}

	/*
	 * 自定义排序 查询所有实体集合
	 * 
	 * @param entityClass 实体类型信息
	 * 
	 * @return 查询到的实体对象集合
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pb.dao.BaseDao#findAll_order(java.lang.Class, java.lang.String)
	 */
	public List<T> findAll_order(Class<T> entityClass, String orderDesc) {
		String hql = "from " + entityClass.getName() + " obj order by "
				+ orderDesc;
		return this.findAllByHQL(hql);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pb.dao.BaseDao#findAllByProperty(java.lang.Class,
	 * java.lang.String, java.lang.Object)
	 */
	public List<T> findAllByProperty(Class<T> entityClass, String propertyName,
			Object propertyValue) {
		String queryString = "from " + entityClass.getName()
				+ " as model where model." + propertyName + "=?";
		return super.getHibernateTemplate().find(queryString, propertyValue);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pb.dao.BaseDao#findAllByProperty_order(java.lang.Class,
	 * java.lang.String, java.lang.Object, java.lang.String)
	 */
	public List<T> findAllByProperty_order(Class<T> entityClass,
			String propertyName, Object propertyValue, String orderDesc) {
		String queryString = "from " + entityClass.getName()
				+ " as model where model." + propertyName + "=? order by "
				+ orderDesc;
		return super.getHibernateTemplate().find(queryString, propertyValue);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pb.dao.BaseDao#findAllByProperties(java.lang.Class,
	 * java.lang.String[], java.lang.Object[])
	 */
	public List<T> findAllByProperties(Class<T> entityClass,
			String[] propertyNames, Object[] propertyValues) {
		if (!(propertyNames != null && propertyValues != null && propertyValues.length == propertyNames.length)) {
			throw new RuntimeException(
					"请提供正确的参数值！propertyNames与propertyValues必须一一对应!");
		}
		String queryString = "from " + entityClass.getName()
				+ " as model where ";
		for (int i = 0; i < propertyValues.length; i++) {
			queryString += " model." + propertyNames[i] + " = ? ";
			if (i != propertyValues.length - 1) {
				queryString += " and ";
			}
		}
		return this.findAllByHQL(queryString, propertyValues);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pb.dao.BaseDao#findAllByProperties_order(java.lang.Class,
	 * java.lang.String[], java.lang.Object[], java.lang.String)
	 */
	public List<T> findAllByProperties_order(Class<T> entityClass,
			String[] propertyNames, Object[] propertyValues, String orderDesc) {
		if (!(propertyNames != null && propertyValues != null && propertyValues.length == propertyNames.length)) {
			throw new RuntimeException(
					"请提供正确的参数值！propertyNames与propertyValues必须一一对应!");
		}
		String queryString = "from " + entityClass.getName()
				+ " as model where ";
		for (int i = 0; i < propertyValues.length; i++) {
			queryString += " model." + propertyNames[i] + " = ? ";
			if (i != propertyValues.length - 1) {
				queryString += " and ";
			}
		}
		queryString += " order by " + orderDesc;
		return this.findAllByHQL(queryString, propertyValues);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pb.dao.BaseDao#findAllByLikeProperty(java.lang.Class,
	 * java.lang.String, java.lang.String)
	 */
	public List<T> findAllByLikeProperty(Class<T> entityClass,
			String propertyName, String propertyValue) {
		String queryString = "from " + entityClass.getName()
				+ " as model where model." + propertyName + " like '%"
				+ propertyValue + "%'";
		return super.getHibernateTemplate().find(queryString);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pb.dao.BaseDao#findAllByLikeProperty_order(java.lang.Class,
	 * java.lang.String, java.lang.String, java.lang.String)
	 */
	public List<T> findAllByLikeProperty_order(Class<T> entityClass,
			String propertyName, String propertyValue, String orderDesc) {
		String queryString = "from " + entityClass.getName()
				+ " as model where model." + propertyName + " like '%"
				+ propertyValue + "%'";
		return super.getHibernateTemplate().find(queryString);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pb.dao.BaseDao#findAllByHQL(java.lang.String)
	 */
	public List<T> findAllByHQL(final String hql) {
		return this.findAllByHQLPage(hql, null, -1, -1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pb.dao.BaseDao#findAllByHQL(java.lang.String,
	 * java.lang.Object[])
	 */
	public List<T> findAllByHQL(final String hql, final Object[] params) {
		return this.findAllByHQLPage(hql, params, -1, -1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pb.dao.BaseDao#findAllByPage(java.lang.Class, int, int)
	 */
	public List<T> findAllByPage(Class<T> entityClass, int start, int limit) {
		String hql = "from " + entityClass.getName() + "  model";
		return this.findAllByHQLPage(hql, null, start, limit);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pb.dao.BaseDao#findAllByPage_order(java.lang.Class,
	 * java.lang.String, int, int)
	 */
	public List<T> findAllByPage_order(Class<T> entityClass, String orderDesc,
			int start, int limit) {
		String hql = "from " + entityClass.getName() + "  model order by "
				+ orderDesc;
		return this.findAllByHQLPage(hql, null, start, limit);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pb.dao.BaseDao#findAllByPropertyPage(java.lang.Class,
	 * java.lang.String, java.lang.Object, int, int)
	 */
	public List<T> findAllByPropertyPage(Class<T> entityClass,
			String propertyName, Object propertyValue, int start, int limit) {
		String queryString = "from " + entityClass.getName()
				+ " as model where model." + propertyName + "= ? ";
		return this.findAllByHQLPage(queryString,
				new Object[] { propertyValue }, start, limit);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pb.dao.BaseDao#findAllByPropertyPage_order(java.lang.Class,
	 * java.lang.String, java.lang.Object, java.lang.String, int, int)
	 */
	public List<T> findAllByPropertyPage_order(Class<T> entityClass,
			String propertyName, Object propertyValue, String orderDesc,
			int start, int limit) {
		String queryString = "from " + entityClass.getName()
				+ " as model where model." + propertyName + "=? order by "
				+ orderDesc;
		return this.findAllByHQLPage(queryString,
				new Object[] { propertyValue }, start, limit);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pb.dao.BaseDao#findAllByPropertiesPage(java.lang.Class,
	 * java.lang.String[], java.lang.Object[], int, int)
	 */
	public List<T> findAllByPropertiesPage(Class<T> entityClass,
			String[] propertyNames, Object[] propertyValues, int start,
			int limit) {
		if (!(propertyNames != null && propertyValues != null && propertyValues.length == propertyNames.length)) {
			throw new RuntimeException(
					"请提供正确的参数值！propertyNames与propertyValues必须一一对应!");
		}
		String queryString = "from " + entityClass.getName()
				+ " as model where ";
		for (int i = 0; i < propertyValues.length; i++) {
			queryString += " model." + propertyNames[i] + " = ? ";
			if (i != propertyValues.length - 1) {
				queryString += " and ";
			}
		}
		return this.findAllByHQLPage(queryString, propertyValues, start, limit);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pb.dao.BaseDao#findAllByPropertiesPage_order(java.lang.Class,
	 * java.lang.String[], java.lang.Object[], java.lang.String, int, int)
	 */
	public List<T> findAllByPropertiesPage_order(Class<T> entityClass,
			String[] propertyNames, Object[] propertyValues, String orderDesc,
			int start, int limit) {
		if (!(propertyNames != null && propertyValues != null && propertyValues.length == propertyNames.length)) {
			throw new RuntimeException(
					"请提供正确的参数值！propertyNames与propertyValues必须一一对应!");
		}
		String queryString = "from " + entityClass.getName()
				+ " as model where ";
		for (int i = 0; i < propertyValues.length; i++) {
			queryString += " model." + propertyNames[i] + " = ? ";
			if (i != propertyValues.length - 1) {
				queryString += " and ";
			}
		}
		queryString += " order by " + orderDesc;
		return this.findAllByHQLPage(queryString, propertyValues, start, limit);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pb.dao.BaseDao#findAllByLikePropertyPage(java.lang.Class,
	 * java.lang.String, java.lang.String, int, int)
	 */
	public List<T> findAllByLikePropertyPage(Class<T> entityClass,
			String propertyName, String propertyValue, int start, int limit) {
		String queryString = "from " + entityClass.getName()
				+ " as model where model." + propertyName + " like '%"
				+ propertyValue + "%'";
		return this.findAllByHQLPage(queryString, null, start, limit);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pb.dao.BaseDao#findAllByLikePropertyPage_order(java.lang.Class,
	 * java.lang.String, java.lang.String, java.lang.String, int, int)
	 */
	public List<T> findAllByLikePropertyPage_order(Class<T> entityClass,
			String propertyName, String propertyValue, String orderDesc,
			int start, int limit) {
		String queryString = "from " + entityClass.getName()
				+ " as model where model." + propertyName + " like '%"
				+ propertyValue + "%'";
		queryString += " order by " + orderDesc;
		return this.findAllByHQLPage(queryString, null, start, limit);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pb.dao.BaseDao#findAllByHQLPage(java.lang.String,
	 * java.lang.Object[], int, int)
	 */
	public List<T> findAllByHQLPage(final String hql, final Object[] params,
			final int start, final int limit) {
		return (List<T>) super.getHibernateTemplate().executeWithNativeSession(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createQuery(hql);
						if (params != null && params.length > 0) {
							for (int i = 0; i < params.length; i++) {
								query.setParameter(i, params[i]);
							}
						}
						// 表示是分页查询
						if (start != -1 && limit != -1) {
							query.setFirstResult((start-1)*limit);
							query.setMaxResults(limit);
						}
						return query.list();
					}
				});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pb.dao.BaseDao#getTotalCount(java.lang.Class)
	 */
	public Long getTotalCount(final Class<T> entityClass) {
		return (Long) super.getHibernateTemplate().executeWithNativeSession(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						String hql = "select count(o) from "
								+ entityClass.getName() + " o";
						Query query = session.createQuery(hql);
						Object obj = query.uniqueResult();
						return obj;
					}
				});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pb.dao.BaseDao#getTotalCountByHQL(java.lang.String,
	 * java.lang.Object[])
	 */
	public Long getTotalCountByHQL(final String hql, final Object[] params) {
		return (Long) super.getHibernateTemplate().executeWithNativeSession(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createQuery(hql);
						if (params != null && params.length > 0) {
							for (int i = 0; i < params.length; i++) {
								query.setParameter(i, params[i]);
							}
						}
						return query.uniqueResult();
					}
				});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pb.dao.BaseDao#getTotalCountByHQL(java.lang.String)
	 */
	public Long getTotalCountByHQL(final String hql) {
		return this.getTotalCountByHQL(hql, null);
	}

	/***************************************************************************
	 * 以下部分是QBE查询
	 **************************************************************************/
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pb.dao.BaseDao#findAllByQBE(java.lang.Class, T)
	 */

	public List<T> findAllByQBE(final Class<T> entityClass, final T example) {
		return this.findAllByQBEPage(entityClass, example, -1, -1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pb.dao.BaseDao#findAllByQBEPage(java.lang.Class, T, int, int)
	 */
	public List<T> findAllByQBEPage(final Class<T> entityClass,
			final T example, final int start, final int limit) {
		return this.findAllByQBEPage(entityClass, example, start, limit, null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pb.dao.BaseDao#findAllByQBEPage(java.lang.Class, T, int, int,
	 * org.hibernate.criterion.Order[])
	 */
	public List<T> findAllByQBEPage(final Class<T> entityClass,
			final T example, final int start, final int limit,
			final Order[] orders) {
		return (List<T>) super.getHibernateTemplate().executeWithNativeSession(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Criteria criteria = session.createCriteria(entityClass);
						criteria.add(Example.create(example));
						// 设置排序
						if (orders != null && orders.length > 0) {
							for (int i = 0; i < orders.length; i++) {
								criteria.addOrder(orders[i]);
							}
						}
						if (start != -1 && limit != -1) {
							criteria.setFirstResult(start);
							criteria.setMaxResults(limit);
						}
						return criteria.list();
					}
				});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pb.dao.BaseDao#getStatisticalValueByQBE(java.lang.Class, T,
	 * org.hibernate.criterion.Projection)
	 */
	public Object getStatisticalValueByQBE(final Class<T> entityClass,
			final T example, final Projection projection) {
		return super.getHibernateTemplate().executeWithNativeSession(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Criteria criteria = session.createCriteria(entityClass);
						criteria.add(Example.create(example));
						criteria.setProjection(projection);
						return criteria.uniqueResult();
					}
				});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pb.dao.BaseDao#getTotalCountByExample(java.lang.Class, T)
	 */
	public Integer getTotalCountByExample(final Class<T> entityClass,
			final T example) {
		return (Integer) super.getHibernateTemplate().executeWithNativeSession(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Criteria criteria = session.createCriteria(entityClass);
						criteria.add(Example.create(example));
						criteria.setProjection(Projections.rowCount());// 总行数
						return criteria.uniqueResult();
					}

				});
	}

	/***************************************************************************
	 * 以下是QBC查询
	 **************************************************************************/
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pb.dao.BaseDao#findAllByQBCPage(java.lang.Class, int, int,
	 * org.hibernate.criterion.Criterion[], org.hibernate.criterion.Order[],
	 * org.hibernate.criterion.Projection[], boolean)
	 */

	public Object findAllByQBCPage(final Class<T> entityClass, final int start,
			final int limit, final Criterion[] criterions,
			final Order[] orders, final Projection[] projs,
			final boolean isUniqueResult) {
		return super.getHibernateTemplate().executeWithNativeSession(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Criteria criteria = session.createCriteria(entityClass);
						// 添加条件
						if (criterions != null && criterions.length > 0) {
							for (int i = 0; i < criterions.length; i++) {
								criteria.add(criterions[i]);
							}
						}
						// 添加排序
						if (orders != null && orders.length > 0) {
							for (int i = 0; i < orders.length; i++) {
								criteria.addOrder(orders[i]);
							}
						}
						// 添加分组统计
						if (projs != null && projs.length > 0) {
							for (int i = 0; i < projs.length; i++) {
								criteria.setProjection(projs[i]);
							}
						}
						// 查看是否要分页
						if (start != -1 && limit != -1) {
							criteria.setFirstResult(start);
							criteria.setMaxResults(limit);
						}

						if (isUniqueResult) {
							return criteria.uniqueResult();
						} else {
							return criteria.list();
						}
					}
				});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pb.dao.BaseDao#findAllByQBCPage(java.lang.Class, int, int,
	 * org.hibernate.criterion.Criterion[], org.hibernate.criterion.Order[])
	 */
	public List<T> findAllByQBCPage(final Class<T> entityClass,
			final int start, final int limit, final Criterion[] criterions,
			final Order[] orders) {
		return (List<T>) this.findAllByQBCPage(entityClass, start, limit,
				criterions, orders, null, false);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pb.dao.BaseDao#findAllByQBCPage(java.lang.Class, int, int,
	 * org.hibernate.criterion.Criterion[])
	 */
	public List<T> findAllByQBCPage(final Class<T> entityClass,
			final int start, final int limit, final Criterion[] criterions) {
		

		
		return (List<T>) this.findAllByQBCPage(entityClass, start, limit,
				criterions, null, null, false);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pb.dao.BaseDao#findAllByQBCPage(java.lang.Class,
	 * org.hibernate.criterion.Criterion[])
	 */
	public List<T> findAllByQBCPage(final Class<T> entityClass,
			final Criterion[] criterions) {
		return (List<T>) this.findAllByQBCPage(entityClass, -1, -1, criterions,
				null, null, false);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pb.dao.BaseDao#getTotalCountByQBC(java.lang.Class,
	 * org.hibernate.criterion.Criterion[])
	 */
	public Integer getTotalCountByQBC(final Class<T> entityClass,
			final Criterion[] criterions) {
		return (Integer) this.findAllByQBCPage(entityClass, -1, -1, criterions,
				null, new Projection[] { Projections.rowCount() }, true);
		
		
	}

	public List<T> execquerySql(final String sql,final Object[] params,final Integer start,final Integer limit) {
		// TODO Auto-generated method stub
		
		return (List<T>) super.getHibernateTemplate().executeWithNativeSession(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createSQLQuery(sql);
						if (params != null && params.length > 0) {
							for (int i = 0; i < params.length; i++) {
								query.setParameter(i, params[i]);
							}
						}
						// 表示是分页查询
						if (start != -1 && limit != -1) {
							query.setFirstResult((start-1)*limit);
							query.setMaxResults(limit);
						}
						return query.list();
					}
				});
	}
	
	public List<T> execquerySql2(final String sql,final Object[] params,final Integer start,final Integer limit) {
		// TODO Auto-generated method stub	
		return (List<T>) super.getHibernateTemplate().executeWithNativeSession(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
						if (params != null && params.length > 0) {
							for (int i = 0; i < params.length; i++) {
								query.setParameter(i, params[i]);
							}
						}
						// 表示是分页查询
					
						if (start != -1 && limit != -1) {
							query.setFirstResult((start-1)*limit);
							query.setMaxResults(limit);
						}

						return query.list();
					}
				});
	}

	public void execUpdateSql(final String sql,final Object[] param) {
		// TODO Auto-generated method stub
		// 使用回调接口完成操作
		super.getHibernateTemplate().executeWithNativeSession(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createSQLQuery(sql);
						
						if(param!=null)
						{
							for (int i = 0; i < param.length; i++) {
								query.setParameter(i, param[i]);
							}
						}
						query.executeUpdate();
						return null;
					}
				});
	}

	public List<T> findMapByHQL(final String hql,final Object[] params,final int start,
			final int limit) {
		// TODO Auto-generated method stub
		return (List<T>) super.getHibernateTemplate().executeWithNativeSession(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createQuery(hql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
						if (params != null && params.length > 0) {
							for (int i = 0; i < params.length; i++) {
								query.setParameter(i, params[i]);
							}
						}
						// 表示是分页查询
						if (start != -1 && limit != -1) {
							query.setFirstResult((start-1)*limit);
							query.setMaxResults(limit);
						}
						return query.list();
					}
				});
	}

	public void saveAll(Collection<T> collection) {
		super.getHibernateTemplate().saveOrUpdateAll(collection);
		
	}
	
}