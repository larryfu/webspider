package cn.larry.web.storage;

import javax.annotation.Resource;

import cn.larry.mysearcher.dao.ClimbedHistoryDao;
import cn.larry.mysearcher.daoextends.HibernateDaoExtend;
import cn.larry.mysearcher.entity.ClimbHistory;
import cn.larry.web.dstool.PageQueue;
import cn.larry.web.dstool.DomainQueue;
import cn.larry.web.util.TimeUtil;


public class ClimbHistoryDBOperate {
	@Resource
	private HibernateDaoExtend  hibernateDaoExtend;
	public ClimbHistoryDBOperate(){
		hibernateDaoExtend=new HibernateSupport().getHibernateDaoExtend();
	}
	public  void  storeClimbHistory(String startDomain,String endDomain,String startTime){
		String endTime = TimeUtil.getCurrentTime();
		int climbedDomain = DomainQueue.getDomainNum();
		int storedPage = PageQueue.getStoredPageNum();
		int climbedPage = DomainQueue.getPageNum();
		ClimbHistory climbhistory = new ClimbHistory(startTime ,endTime,startDomain,endDomain,climbedDomain,storedPage,climbedPage);
		hibernateDaoExtend.save(climbhistory);
	}

}
