package cn.larry.mysearcher.controller;




import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.larry.mysearcher.services.WebClimber;

@Controller
@RequestMapping("/climber")
public class ClimberController {
	
	

	@RequestMapping("/start")
	@ResponseBody
	public int startClimber(String startdomain, String times){
		System.out.println("startdomian:"+startdomain);
		times = times==null?"0":times;
		startdomain = startdomain==null?"":startdomain;
		int time = Integer.parseInt(times);
		WebClimber webClimber= new WebClimber(startdomain,time);
		System.out.println("start :----------------------------------");
		webClimber.startClimb();
		return webClimber.getIndex();
	}
	
	@RequestMapping("/stop")
	@ResponseBody
	public boolean stopClimber(int index){
		return WebClimber.stopWebSpider(index);
	}
}
