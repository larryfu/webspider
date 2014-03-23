package cn.larry.mysearcher.services;


import cn.larry.web.climb.WebSpider;
import cn.larry.web.climb.WebSpiderManager;

public class WebClimber {
	private WebSpider webspider;
	public WebClimber(){
		webspider =  new  WebSpider();
		System.out.println("init a web climber");
	}
	public WebClimber(String startdomain,int time){
		if(startdomain==""&&time<1)
		{
			webspider = new  WebSpider();
		}else if(time<1){
			webspider = new  WebSpider(startdomain);
		}else if(startdomain==""){
			webspider = new  WebSpider(time);
		}else {
			webspider = new  WebSpider(startdomain,time);
		}
	}
	public void startClimb(){
		Thread thr = new Thread(webspider);
		thr.start();
		System.out.println("webClimber start climb--------------------");
	}
	public int getIndex(){
		return webspider.getIndex();
	}
	public static boolean stopWebSpider(int index){
		WebSpider ws = WebSpiderManager.getWebSpiderInstance(index);
		if(ws!=null){
			WebSpiderManager.removeInstance(index);
			ws.stop();
			return true;
		}else{
			return false;
		}
	}
}
