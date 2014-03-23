package cn.larry.web.climb;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import cn.larry.web.climb.DomainClimber;
import cn.larry.web.climb.DomainInsideClimber;
import cn.larry.web.configuration.DomainConfigure;
import cn.larry.web.dstool.PageQueue;
import cn.larry.web.dstool.DomainQueue;
import cn.larry.web.storage.ClimbHistoryDBOperate;
import cn.larry.web.storage.DomainDBOperate;
import cn.larry.web.storage.StorageTask;
import cn.larry.web.util.TimeUtil;


public class WebSpider implements Runnable {
	DomainConfigure domainConfigure = new DomainConfigure();
	private  String startTime = null;
	private  String startDomain = null;
	private  String endDomain = null;
	private boolean goOn = true;
	private int time=0;
	private  int index;
	public void stop(){
		goOn = false;
	}
	public WebSpider(){
		index = WebSpiderManager.regeistWebSpiderInstance(this);
		System.out.println("init webspider --------------------");
		startDomain = domainConfigure.getCurrentDomain();
		DomainQueue.initUnclimedDomain();
	}
	public WebSpider(String startDomian){
		this.startDomain = startDomain;	
	}
	public WebSpider(int time){
		this.time = time;
		index = WebSpiderManager.regeistWebSpiderInstance(this);
	}
	public WebSpider(String startDomain,int time){
		this.startDomain = startDomain;	
		this.time = time;
		index = WebSpiderManager.regeistWebSpiderInstance(this);
	}
	public void initWebSpider(){
		
		if(startDomain==null){
			startDomain = domainConfigure.getCurrentDomain();	
			DomainQueue.initUnclimedDomain();
		}
		if(time==0){
			time = 20;
		}
		DomainQueue.getInstance();
		PageQueue.getInstance();
		DomainQueue.addDomain(startDomain);
	}
	public  void run(){
		initWebSpider();
		startTime = TimeUtil.getCurrentTime();
		System.out.println("web spider start climb*******************");
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(new Thread(new DomainClimber()));
		System.out.println("DomainClimber start----------");
		exec.execute( new Thread(new StorageTask()));
		System.out.println("StorageTask start----------");
		for(int i=0;i<3;i++){
			exec.execute(new DomainInsideClimber());
		}
		System.out.println("DomainInsideClimber start----------");
		try {
			int timeRemain = time;
			while(goOn&&timeRemain>0){
				Thread.sleep(1000);
				timeRemain--;
				}
			DomainClimber.stop();
			DomainInsideClimber.stop();
			Thread.sleep(3000);
			System.out.println("30s end -----------------------------------------------");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			endDomain = DomainQueue.getCurrentDomain();
			domainConfigure.saveCurrentDomain(endDomain);
			storeClimbHistory();
			DomainQueue.storeUnclimbedDomain();
			System.out.println("page number"+DomainQueue.getPageNum()+"domain number:"+DomainQueue.getDomainNum()+" stored page:"+PageQueue.getStoredPageNum()+"--------------------------------------------------");
			DomainQueue.clear();
			PageQueue.clear();
		}
	}
	public  void storeClimbHistory(){
		new ClimbHistoryDBOperate().storeClimbHistory(startDomain,endDomain,startTime);
	}
	private boolean isParamValid(String domain,int time){
		boolean domianValid = domain.startsWith("http:\\")||domain.startsWith("http:\\");
		if(domianValid&&time>0){
			return true;
		}else{
			return false;
		}
	}
	public int getIndex() {
		return index;
	}
}
