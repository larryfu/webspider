package cn.larry.mysearcher.controller;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.larry.mysearcher.entity.ClimbHistory;
import cn.larry.mysearcher.dao.ClimbedHistoryDao;
@Controller
@RequestMapping("/climbHistory")
public class ClimbHistoryController  {
	
	private ClimbHistory climbedHistory;
	
	@Resource
	private ClimbedHistoryDao climbedHistoryDao;
	
	public ClimbHistory getClimbedHistory() {
		return climbedHistory;
	}
	public void setClimbedHistory(ClimbHistory climbedHistory) {
		this.climbedHistory = climbedHistory;
	}
	public ClimbedHistoryDao getClimbedHistoryDao() {
		return climbedHistoryDao;
	}
	public void setClimbedHistoryDao(ClimbedHistoryDao climbedHistoryDao) {
		this.climbedHistoryDao = climbedHistoryDao;
	}

	@RequestMapping("/get")
	@ResponseBody
	public List<?> getHistory(){
		System.out.println("history start---------");
		 List<?> history = climbedHistoryDao.getClimbHistory();
		 if(history==null){
			 System.out.println("history is null");
			 return null;
		 }
		 System.out.println("history size:------------"+history.size());
		// System.out.println("history:"+((ClimbHistory)history.get(0)).getClimbeddomain());
		 return history;
	}
}
