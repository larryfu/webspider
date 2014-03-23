package cn.larry.mysearcher.daoextends;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

@Component
public class HibernateDaoExtend {
	
	@Resource
	private  SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	public List find(String hql){
		List<Object> historyLst = new ArrayList<Object>();
		Session ss = getSession();
		Transaction ts = ss.beginTransaction();
		try{
		historyLst = ss.createQuery(hql).list();
		ts.commit();
		}catch(Exception e){
			e.printStackTrace();
		}
		return historyLst;
	}
	public void save(Object obj){
		Session ss = getSession();
		Transaction ts = ss.beginTransaction();
		try{
		ss.save(obj);
		ts.commit();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void update(Object obj){
		Session ss = getSession();
		Transaction ts = ss.beginTransaction();
		try{
		ss.update(obj);
		ts.commit();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void delete(Object obj){
		Session ss = getSession();
		Transaction ts = ss.beginTransaction();
		try{
		ss.delete(obj);
		ts.commit();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public List SQLQuery(String sql){
		List<Object> historyLst = new ArrayList<Object>();
		Session ss = getSession();
		Transaction ts = ss.beginTransaction();
		try{
		historyLst = ss.createSQLQuery(sql).list();
		ts.commit();
		}catch(Exception e){
			e.printStackTrace();
		}
		return historyLst;
	}
	public void clear(String table){
		Session ss = getSession();
		Transaction ts = ss.beginTransaction();
		try{
		String sql = "delete from "+table;
		Query query=ss.createQuery(sql);
		query.executeUpdate();
		ts.commit();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void sqlUpdate(String sql){
		Session ss = getSession();
		Transaction ts = ss.beginTransaction();
		try{
		Query query=ss.createQuery(sql);
		query.executeUpdate();
		ts.commit();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
