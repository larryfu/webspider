package cn.larry.mysearcher.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import cn.larry.mysearcher.dao.ClimbedHistoryDao;
import cn.larry.mysearcher.daoextends.HibernateDaoExtend;
import cn.larry.mysearcher.entity.ClimbHistory;

public class ClimbedHistoryDaoHibernate   implements ClimbedHistoryDao{


	@Resource
	private HibernateDaoExtend hibernateDaoExtend;

	public HibernateDaoExtend getHibernateDaoExtend(){
		return this.hibernateDaoExtend;
	}
	public void setHibernateDaoExtend(HibernateDaoExtend hibernateDaoExtend){
		this.hibernateDaoExtend=hibernateDaoExtend;
	}

	public List<Object> getClimbHistory() {
		List<Object> historyLst = new ArrayList<Object>();
		try{
		historyLst = hibernateDaoExtend.find("from cn.larry.mysearcher.entity.ClimbHistory");
		}catch(Exception e){
			e.printStackTrace();
		}
		return historyLst;	
	}

	public void insertClimbedDomain(ClimbHistory climbHistory) {
		hibernateDaoExtend.save(climbHistory);
	}

	public void deleteClimbHistoryByUrl(String url) {
		// TODO Auto-generated method stub
		
	}

}
