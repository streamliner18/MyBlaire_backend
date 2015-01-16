package com.myBlaire.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;

public interface BaseDao<T extends Serializable, PK extends Serializable> {

	/***************************************************************************
	 * 新增
	 **************************************************************************/

	/* (non-Javadoc)
	 * @see org.books.dao.BaseDao#save(T)
	 */
	public abstract Serializable save(T entity); 

	/***************************************************************************
	 * 更新
	 **************************************************************************/
	/* (non-Javadoc)
	 * @see org.books.dao.BaseDao#update(T)
	 */
	public abstract void update(T t);

	/* (non-Javadoc)
	 * @see org.books.dao.BaseDao#update(java.lang.String, java.lang.Object[])
	 */
	public abstract void update(final String hql, final Object[] params);

	/* (non-Javadoc)
	 * @see org.books.dao.BaseDao#update(java.lang.Class, java.lang.String, java.lang.Object, java.lang.String, java.lang.Object)
	 */
	public abstract void update(Class<T> entityClass, String pkName,
			Object pkValue, String propName, Object propValue);

	/* (non-Javadoc)
	 * @see org.books.dao.BaseDao#update(java.lang.Class, java.lang.String, java.lang.Object, java.lang.String[], java.lang.Object[])
	 */
	public abstract void update(Class<T> entityClass, String pkName,
			Object pkValue, String[] propNames, Object[] propValues);

	/* (non-Javadoc)
	 * @see org.books.dao.BaseDao#saveorupdate(T)
	 */
	public abstract void saveorupdate(T entity);

	/***************************************************************************
	 * 删除
	 **************************************************************************/

	/* (non-Javadoc)
	 * @see org.books.dao.BaseDao#delete(T)
	 */
	public abstract void delete(T entity);

	/* (non-Javadoc)
	 * @see org.books.dao.BaseDao#deleteById(java.lang.Class, PK)
	 */
	public abstract void deleteById(Class<T> entityClass, PK id);

	/* (non-Javadoc)
	 * @see org.books.dao.BaseDao#deleteByProperty(java.lang.Class, java.lang.String, java.lang.Object)
	 */
	public abstract void deleteByProperty(final Class<T> entityClass,
			final String propName, final Object propValue);

	/* (non-Javadoc)
	 * @see org.books.dao.BaseDao#deleteByProperty(java.lang.Class, java.lang.String[], java.lang.Object[])
	 */
	public abstract void deleteByProperty(final Class<T> entityClass,
			final String[] propNames, final Object[] propValues);

	/***************************************************************************
	 * 查询
	 **************************************************************************/

	/* (non-Javadoc)
	 * @see org.books.dao.BaseDao#findEnityById(java.lang.Class, PK)
	 */
	public abstract T findEnityById(Class<T> entityClass, PK id);

	/* (non-Javadoc)
	 * @see org.books.dao.BaseDao#findAll(java.lang.Class)
	 */
	public abstract List<T> findAll(Class<T> entityClass);

	/* (non-Javadoc)
	 * @see org.books.dao.BaseDao#findAllAsc(java.lang.Class, java.lang.String)
	 */
	public abstract List<T> findAllAsc(Class<T> entityClass,
			String orderProperty);

	/* (non-Javadoc)
	 * @see org.books.dao.BaseDao#findAllDesc(java.lang.Class, java.lang.String)
	 */
	public abstract List<T> findAllDesc(Class<T> entityClass,
			String orderProperty);

	/* (non-Javadoc)
	 * @see org.books.dao.BaseDao#findAll_order(java.lang.Class, java.lang.String, java.lang.String)
	 */
	public abstract List<T> findAll_order(Class<T> entityClass,
			String orderProperty, String orderType);

	/*
	 * 自定义排序 查询所有实体集合
	 *
	 * @param entityClass
	 *            实体类型信息
	 * @return 查询到的实体对象集合
	 */
	/* (non-Javadoc)
	 * @see org.books.dao.BaseDao#findAll_order(java.lang.Class, java.lang.String)
	 */
	public abstract List<T> findAll_order(Class<T> entityClass, String orderDesc);

	/* (non-Javadoc)
	 * @see org.books.dao.BaseDao#findAllByProperty(java.lang.Class, java.lang.String, java.lang.Object)
	 */
	public abstract List<T> findAllByProperty(Class<T> entityClass,
			String propertyName, Object propertyValue);

	/* (non-Javadoc)
	 * @see org.books.dao.BaseDao#findAllByProperty_order(java.lang.Class, java.lang.String, java.lang.Object, java.lang.String)
	 */
	public abstract List<T> findAllByProperty_order(Class<T> entityClass,
			String propertyName, Object propertyValue, String orderDesc);

	/* (non-Javadoc)
	 * @see org.books.dao.BaseDao#findAllByProperties(java.lang.Class, java.lang.String[], java.lang.Object[])
	 */
	public abstract List<T> findAllByProperties(Class<T> entityClass,
			String[] propertyNames, Object[] propertyValues);

	/* (non-Javadoc)
	 * @see org.books.dao.BaseDao#findAllByProperties_order(java.lang.Class, java.lang.String[], java.lang.Object[], java.lang.String)
	 */
	public abstract List<T> findAllByProperties_order(Class<T> entityClass,
			String[] propertyNames, Object[] propertyValues, String orderDesc);

	/* (non-Javadoc)
	 * @see org.books.dao.BaseDao#findAllByLikeProperty(java.lang.Class, java.lang.String, java.lang.String)
	 */
	public abstract List<T> findAllByLikeProperty(Class<T> entityClass,
			String propertyName, String propertyValue);

	/* (non-Javadoc)
	 * @see org.books.dao.BaseDao#findAllByLikeProperty_order(java.lang.Class, java.lang.String, java.lang.String, java.lang.String)
	 */
	public abstract List<T> findAllByLikeProperty_order(Class<T> entityClass,
			String propertyName, String propertyValue, String orderDesc);

	/* (non-Javadoc)
	 * @see org.books.dao.BaseDao#findAllByHQL(java.lang.String)
	 */
	public abstract List<T> findAllByHQL(final String hql);

	/* (non-Javadoc)
	 * @see org.books.dao.BaseDao#findAllByHQL(java.lang.String, java.lang.Object[])
	 */
	public abstract List<T> findAllByHQL(final String hql, final Object[] params);
	
	/* 
	 * hql查询返回map集合
	 * 
	 */
	public abstract List<T> findMapByHQL(final String hql, final Object[] params,int start,
			int limit);

	/* (non-Javadoc)
	 * @see org.books.dao.BaseDao#findAllByPage(java.lang.Class, int, int)
	 */
	public abstract List<T> findAllByPage(Class<T> entityClass, int start,
			int limit);

	/* (non-Javadoc)
	 * @see org.books.dao.BaseDao#findAllByPage_order(java.lang.Class, java.lang.String, int, int)
	 */
	public abstract List<T> findAllByPage_order(Class<T> entityClass,
			String orderDesc, int start, int limit);

	/* (non-Javadoc)
	 * @see org.books.dao.BaseDao#findAllByPropertyPage(java.lang.Class, java.lang.String, java.lang.Object, int, int)
	 */
	public abstract List<T> findAllByPropertyPage(Class<T> entityClass,
			String propertyName, Object propertyValue, int start, int limit);

	/* (non-Javadoc)
	 * @see org.books.dao.BaseDao#findAllByPropertyPage_order(java.lang.Class, java.lang.String, java.lang.Object, java.lang.String, int, int)
	 */
	public abstract List<T> findAllByPropertyPage_order(Class<T> entityClass,
			String propertyName, Object propertyValue, String orderDesc,
			int start, int limit);

	/* (non-Javadoc)
	 * @see org.books.dao.BaseDao#findAllByPropertiesPage(java.lang.Class, java.lang.String[], java.lang.Object[], int, int)
	 */
	public abstract List<T> findAllByPropertiesPage(Class<T> entityClass,
			String[] propertyNames, Object[] propertyValues, int start,
			int limit);

	/* (non-Javadoc)
	 * @see org.books.dao.BaseDao#findAllByPropertiesPage_order(java.lang.Class, java.lang.String[], java.lang.Object[], java.lang.String, int, int)
	 */
	public abstract List<T> findAllByPropertiesPage_order(Class<T> entityClass,
			String[] propertyNames, Object[] propertyValues, String orderDesc,
			int start, int limit);

	/* (non-Javadoc)
	 * @see org.books.dao.BaseDao#findAllByLikePropertyPage(java.lang.Class, java.lang.String, java.lang.String, int, int)
	 */
	public abstract List<T> findAllByLikePropertyPage(Class<T> entityClass,
			String propertyName, String propertyValue, int start, int limit);

	/* (non-Javadoc)
	 * @see org.books.dao.BaseDao#findAllByLikePropertyPage_order(java.lang.Class, java.lang.String, java.lang.String, java.lang.String, int, int)
	 */
	public abstract List<T> findAllByLikePropertyPage_order(
			Class<T> entityClass, String propertyName, String propertyValue,
			String orderDesc, int start, int limit);

	/* (non-Javadoc)
	 * @see org.books.dao.BaseDao#findAllByHQLPage(java.lang.String, java.lang.Object[], int, int)
	 */
	public abstract List<T> findAllByHQLPage(final String hql,
			final Object[] params, final int start, final int limit);

	/* (non-Javadoc)
	 * @see org.books.dao.BaseDao#getTotalCount(java.lang.Class)
	 */
	public abstract Long getTotalCount(final Class<T> entityClass);

	/* (non-Javadoc)
	 * @see org.books.dao.BaseDao#getTotalCountByHQL(java.lang.String, java.lang.Object[])
	 */
	public abstract Long getTotalCountByHQL(final String hql,
			final Object[] params);

	/* (non-Javadoc)
	 * @see org.books.dao.BaseDao#getTotalCountByHQL(java.lang.String)
	 */
	public abstract Long getTotalCountByHQL(final String hql);
	/***************************************************************************
	 * 以下部分是sql
	 **************************************************************************/
	
	/**
	 * 执行sql查询
	 * @param sql
	 * @return
	 */
	public abstract List<T> execquerySql(final String sql,final Object[] param,final Integer start,final Integer limit);
	/**
	 * 执行sql查询返回map
	 * @param sql
	 * @return
	 */
	public abstract List<T> execquerySql2(final String sql,final Object[] param,final Integer start,final Integer limit);
	
	/**
	 * 执行增 删 改 sql
	 * @param sql
	 */
	public abstract void execUpdateSql(final String sql,final Object[] param);
	/***************************************************************************
	 * 以下部分是QBE查询
	 **************************************************************************/

	/* (non-Javadoc)
	 * @see org.books.dao.BaseDao#findAllByQBE(java.lang.Class, T)
	 */
	public abstract List<T> findAllByQBE(final Class<T> entityClass,
			final T example);

	/* (non-Javadoc)
	 * @see org.books.dao.BaseDao#findAllByQBEPage(java.lang.Class, T, int, int)
	 */
	public abstract List<T> findAllByQBEPage(final Class<T> entityClass,
			final T example, final int start, final int limit);

	/* (non-Javadoc)
	 * @see org.books.dao.BaseDao#findAllByQBEPage(java.lang.Class, T, int, int, org.hibernate.criterion.Order[])
	 */
	public abstract List<T> findAllByQBEPage(final Class<T> entityClass,
			final T example, final int start, final int limit,
			final Order[] orders);

	/* (non-Javadoc)
	 * @see org.books.dao.BaseDao#getStatisticalValueByQBE(java.lang.Class, T, org.hibernate.criterion.Projection)
	 */
	public abstract Object getStatisticalValueByQBE(final Class<T> entityClass,
			final T example, final Projection projection);

	/* (non-Javadoc)
	 * @see org.books.dao.BaseDao#getTotalCountByExample(java.lang.Class, T)
	 */
	public abstract Integer getTotalCountByExample(final Class<T> entityClass,
			final T example);

	/***************************************************************************
	 * 以下是QBC查询
	 **************************************************************************/

	/* (non-Javadoc)
	 * @see org.books.dao.BaseDao#findAllByQBCPage(java.lang.Class, int, int, org.hibernate.criterion.Criterion[], org.hibernate.criterion.Order[], org.hibernate.criterion.Projection[], boolean)
	 */
	public abstract Object findAllByQBCPage(final Class<T> entityClass,
			final int start, final int limit, final Criterion[] criterions,
			final Order[] orders, final Projection[] projs,
			final boolean isUniqueResult);

	/* (non-Javadoc)
	 * @see org.books.dao.BaseDao#findAllByQBCPage(java.lang.Class, int, int, org.hibernate.criterion.Criterion[], org.hibernate.criterion.Order[])
	 */
	public abstract List<T> findAllByQBCPage(final Class<T> entityClass,
			final int start, final int limit, final Criterion[] criterions,
			final Order[] orders);

	/* (non-Javadoc)
	 * @see org.books.dao.BaseDao#findAllByQBCPage(java.lang.Class, int, int, org.hibernate.criterion.Criterion[])
	 */
	public abstract List<T> findAllByQBCPage(final Class<T> entityClass,
			final int start, final int limit, final Criterion[] criterions);

	/* (non-Javadoc)
	 * @see org.books.dao.BaseDao#findAllByQBCPage(java.lang.Class, org.hibernate.criterion.Criterion[])
	 */
	public abstract List<T> findAllByQBCPage(final Class<T> entityClass,
			final Criterion[] criterions);

	/* (non-Javadoc)
	 * @see org.books.dao.BaseDao#getTotalCountByQBC(java.lang.Class, org.hibernate.criterion.Criterion[])
	 */
	public abstract Integer getTotalCountByQBC(final Class<T> entityClass,
			final Criterion[] criterions);
	
	public abstract void saveAll(Collection<T> collection);

}