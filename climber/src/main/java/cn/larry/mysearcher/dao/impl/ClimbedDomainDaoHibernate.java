package cn.larry.mysearcher.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import cn.larry.mysearcher.dao.ClimbedDomainDao;
import cn.larry.mysearcher.daoextends.HibernateDaoExtend;
import cn.larry.mysearcher.entity.ClimbedDomain;


public class ClimbedDomainDaoHibernate  implements ClimbedDomainDao {

	
	@Resource
	private HibernateDaoExtend hibernateDaoExtend;

	public HibernateDaoExtend getHibernateDaoExtend(){
		return this.hibernateDaoExtend;
	}
	public void setHibernateDaoExtend(HibernateDaoExtend hibernateDaoExtend){
		this.hibernateDaoExtend=hibernateDaoExtend;
	} 
	
	public List<ClimbedDomain> getClimbedDomain() {
		// TODO Auto-generated method stub
		return hibernateDaoExtend.find("from cn.larry.mysearcher.entity.ClimbedDomain");
	}

	public void insertClimbedDomain(ClimbedDomain climbedDomain) {
		// TODO Auto-generated method stub
		hibernateDaoExtend.save(climbedDomain);
	}

	public void deleteClimbedDomainByUrl(String url) {
		// TODO Auto-generated method stub
		
	}

}
