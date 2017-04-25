package cn.larry.mysearcher.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import cn.larry.mysearcher.dao.UnclimbedDomainDao;
import cn.larry.mysearcher.daoextends.HibernateDaoExtend;
import cn.larry.mysearcher.entity.UnclimbedDomain;

public class UnclimedDomainDaoHibernate  implements UnclimbedDomainDao {
	
		@Resource
		private HibernateDaoExtend hibernateDaoExtend;

		public HibernateDaoExtend getHibernateDaoExtend(){
			return this.hibernateDaoExtend;
		}
		public void setHibernateDaoExtend(HibernateDaoExtend hibernateDaoExtend){
			this.hibernateDaoExtend=hibernateDaoExtend;
		}

	
	public List<UnclimbedDomain> getUnclimbedDomain() {
		// TODO Auto-generated method stub
		List<UnclimbedDomain> unclimbedDomainLst = new ArrayList<UnclimbedDomain>();
		try{
				unclimbedDomainLst = hibernateDaoExtend.find("from cn.larry.mysearcher.entity.UnclimbedDomain");
		}catch(Exception e){
			e.printStackTrace();
		}
		return unclimbedDomainLst;
		}

	public void insertClimbedDomain(UnclimbedDomain unclimbedDomain) {
		// TODO Auto-generated method stub
		
	}

	public void deleteUnclimbedDomainByUrl(String url) {
		// TODO Auto-generated method stub
		
	}
//	@Override
	public void deleteUnclimbedDomain() {
		// TODO Auto-generated method stub
		
	}

}
