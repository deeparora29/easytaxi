package com.btit.utilsweb.test.table.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessResourceFailureException;
import com.btit.utilsweb.test.table.vo.PageSortModel;

public class HibernateEntityDao {

	private SessionFactory sessionFactory;

	private Session session;

	public HibernateEntityDao() {
		sessionFactory = new Configuration().configure().buildSessionFactory();
		session = sessionFactory.openSession();
	}
	/**
	 * 关闭连接
	 */
	public void closeSessionFactory() {
		if (session != null) {
			session.close();
			sessionFactory.close();
		}
	}

	/**
	 * 获取Criteria
	 * 
	 * @param clss
	 * @return
	 * @throws DataAccessResourceFailureException
	 */
	private Criteria createCriteria(Class clss)
			throws DataAccessResourceFailureException {
		Criteria criteria = session.createCriteria(clss);
		return criteria;
	}

	/**
	 * 查找对象
	 */
	public List find(Criteria criteria, PageSortModel psm) {
		// default PageSortModel no order Map
		// if (psm.getOrderMap() != null) {
		// return find(criteria,createOrders(psm.getOrderMap()),psm);
		// }
		if (psm != null) {
			int totalCount = ((Integer) criteria.setProjection(
					Projections.rowCount()).uniqueResult()).intValue();

			psm.setTotalRows(totalCount);
			criteria.setProjection(null);

			if (!psm.isAll()) {
				criteria.setFirstResult(psm.getRowStart());
				criteria.setMaxResults(psm.getPageSize());
			}
		}

		return criteria.list();
	}

	/**
	 * 根据分页Modal来获取对象列表
	 * 
	 * @param psm
	 * @return
	 */
	public List find(Class clazz, PageSortModel psm) {
		Criteria criteria = createCriteria(clazz);
		if(psm.getSearchMap() != null && psm.getSearchMap().size() > 0){
			for(Entry<String, String> entry : (Set<Entry<String, String>>)psm.getSearchMap().entrySet()){
				if(StringUtils.isNotBlank(entry.getValue()))
					criteria.add(Restrictions.like(entry.getKey(), "%" + entry.getValue() + "%"));
			}
		}
		
		if(psm.getOrderMap() != null && psm.getOrderMap().size() > 0){
			for(Entry<String, String> entry : (Set<Entry<String, String>>)psm.getOrderMap().entrySet()){
				if(StringUtils.isNotBlank(entry.getValue())){
					if(entry.getValue().equalsIgnoreCase("asc"))
						criteria.addOrder(Order.asc(entry.getKey()));
					else
						criteria.addOrder(Order.desc(entry.getKey()));
				}
			}
		}
		
		
		return find(criteria, psm);
	}

	/**
	 * 找到全部对象的列表
	 * 
	 * @param clazz
	 * @return
	 */
	public List findAllObject(Class clazz) {
		Criteria criteria = createCriteria(clazz);
		return criteria.list();
	}

	/**
	 * 新增Obj
	 * 
	 * @param obj
	 */
	public void addObject(Object obj) {
		Transaction tx = session.beginTransaction();
		try {
			session.save(obj);
			tx.commit();
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			e.printStackTrace();
		}
	}

	/**
	 * 更新Obj
	 * 
	 * @param obj
	 */
	public void updateObject(Object obj) {
		Transaction tx = session.beginTransaction();
		try {
			session.update(obj);
			tx.commit();
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			e.printStackTrace();
		}
	}

	/**
	 * 删除Obj
	 * 
	 * @param obj
	 */
	public void deleteObject(Object obj) {
		Transaction tx = session.beginTransaction();
		try {
			session.delete(obj);
			tx.commit();
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			e.printStackTrace();
		}
	}
	/**
	 * 按条件查询分页数据
	 * @param clazz
	 * @param paramMap
	 * @param psm
	 * @return
	 */
	public List findObjectBy(Class clazz, HashMap<String, String> paramMap,
			PageSortModel psm) {
		Criteria criteria = createCriteria(clazz);
		if (paramMap != null && paramMap.size() > 0) {
			for (Entry<String, String> entry : paramMap.entrySet()) {
				if (StringUtils.isNotBlank(entry.getKey())) {
					criteria.add(Restrictions.like(entry.getKey(), "%"
							+ entry.getValue() + "%"));
				}
			}
		}
		return find(criteria, psm);
	}
	/**
	 * 按条件查询数据
	 * @param clazz
	 * @param paramMap
	 * @return
	 */
	public List findObjectBy(Class clazz, HashMap<String, String> paramMap) {
		return findObjectBy(clazz, paramMap, null);
	}

	public Object findById(Class clazz, String oid) {
		return session.get(clazz, oid);
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

}
