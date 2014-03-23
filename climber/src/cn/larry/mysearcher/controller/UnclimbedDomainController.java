package cn.larry.mysearcher.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.larry.mysearcher.dao.ClimbedHistoryDao;
import cn.larry.mysearcher.dao.UnclimbedDomainDao;
import cn.larry.mysearcher.entity.ClimbHistory;
import cn.larry.mysearcher.entity.UnclimbedDomain;

@Controller
@RequestMapping("/unclimbeddomain")
public class UnclimbedDomainController {
	@Resource
	private UnclimbedDomainDao   unclimbedDomainDao;
	
	public void setUnclimbedDomainDao(UnclimbedDomainDao unclimbedDomainDao){
		this.unclimbedDomainDao = unclimbedDomainDao;
	}
	
	@RequestMapping("/get")
	@ResponseBody
	public List<?> getUnclimbedDomain(){
		List<?> result = new ArrayList();
		System.out.print("getunclimbeddomain");
		try{
		result = unclimbedDomainDao.getUnclimbedDomain();
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
}
