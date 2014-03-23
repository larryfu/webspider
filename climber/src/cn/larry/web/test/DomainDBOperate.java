package cn.larry.web.test;


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
import cn.larry.mysearcher.entity.ClimbedDomain;
import cn.larry.mysearcher.entity.UnclimbedDomain;
import cn.larry.web.util.TimeUtil;

@Controller
@RequestMapping("/DomainDB")
public class DomainDBOperate {
	@Resource
	private ClimbedDomainDao climbedDomainDao ;
	@Resource
	private UnclimbedDomainDao unclimbedDomainDao ;
	public void setUnclimbedDomainDao(UnclimbedDomainDao unclimbedDomainDao) {
		this.unclimbedDomainDao = unclimbedDomainDao;
	}
	public UnclimbedDomainDao getUnclimbedDomainDao() {
		return unclimbedDomainDao;
	}
	public void setClimbedDomainDao(ClimbedDomainDao climbedDomainDao) {
		this.climbedDomainDao = climbedDomainDao;
	}
	public synchronized  void storeDomain(String domain,int pages){
		String time = TimeUtil.getCurrentTime();
		ClimbedDomain climbedDomain = new ClimbedDomain(domain,time,pages);
		climbedDomainDao.insertClimbedDomain(climbedDomain);
	}
	@RequestMapping("/get")
	@ResponseBody
	public   Set<String> getClimedDomain(){
		Set<String> domainSet = new HashSet<String>();
		List<ClimbedDomain> domainLst = climbedDomainDao.getClimbedDomain();
		for(ClimbedDomain cd:domainLst){
			domainSet.add(cd.getDomain());
		}
		return domainSet;
	}
	public synchronized  void storeUnclimedDomain(String domain){
		UnclimbedDomain ud = new UnclimbedDomain(domain);
		unclimbedDomainDao.insertClimbedDomain(ud);
	}

	public  Set<String> getUnclimedDomain(){
		Set<String> domainSet = new HashSet<String>();
		List<UnclimbedDomain> domainLst = unclimbedDomainDao.getUnclimbedDomain();
		for(UnclimbedDomain cd:domainLst){
			domainSet.add(cd.getDomain());
		}
		return domainSet;
	}
	public  void clearUnclimedDomain(){
		unclimbedDomainDao.deleteUnclimbedDomain();
	}
}
