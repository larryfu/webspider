package cn.larry.web.storage;


import java.util.HashSet;
import java.util.List;
import java.util.Set;












import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.larry.mysearcher.dao.ClimbedDomainDao;
import cn.larry.mysearcher.dao.UnclimbedDomainDao;
import cn.larry.mysearcher.dao.impl.ClimbedDomainDaoHibernate;
import cn.larry.mysearcher.dao.impl.UnclimedDomainDaoHibernate;
import cn.larry.mysearcher.daoextends.HibernateDaoExtend;
import cn.larry.mysearcher.entity.ClimbedDomain;
import cn.larry.mysearcher.entity.UnclimbedDomain;
import cn.larry.web.util.TimeUtil;


public class DomainDBOperate {
private HibernateDaoExtend hibernateDaoExtend ;
public DomainDBOperate(){
	hibernateDaoExtend=new HibernateSupport().getHibernateDaoExtend();
}
	public synchronized  void storeDomain(String domain,int pages){
		String time = TimeUtil.getCurrentTime();
		ClimbedDomain climbedDomain = new ClimbedDomain(domain,time,pages);
		hibernateDaoExtend.save(climbedDomain);
	}

	public   Set<String> getClimedDomain(){
		Set<String> domainSet = new HashSet<String>();
		List<ClimbedDomain> domainLst = hibernateDaoExtend.find("from cn.larry.mysearcher.entity.ClimbedDomain");
		for(ClimbedDomain cd:domainLst){
			domainSet.add(cd.getDomain());
		}
		return domainSet;
	}
	public synchronized  void storeUnclimedDomain(String domain){
		UnclimbedDomain ud = new UnclimbedDomain(domain);
		hibernateDaoExtend.save(ud);
	}

	public  Set<String> getUnclimedDomain(){
		Set<String> domainSet = new HashSet<String>();
		List<UnclimbedDomain> domainLst =  hibernateDaoExtend.find("from cn.larry.mysearcher.entity.UnclimbedDomain");
		for(UnclimbedDomain cd:domainLst){
			domainSet.add(cd.getDomain());
		}
		return domainSet;
	}
	public  void clearUnclimedDomain(){
		//hibernateDaoExtend.();
		hibernateDaoExtend.clear("cn.larry.mysearcher.entity.UnclimbedDomain");
	}
}
