package cn.larry.web.dstool;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import javax.annotation.Resource;

import cn.larry.web.storage.DomainDBOperate;

public class DomainQueue {
	private static Queue<String> domainQueue = new LinkedList<String>();
	private static Set domainSet=new HashSet<String>(); 
	public static DomainQueue instance=null;
	private static int pageNum = 0;
	private static int domainNum = 0;
	private static String currentDomain = null;
	@Resource
	static DomainDBOperate domainDBOperate;
	public static DomainQueue getInstance(){
		if(instance == null){
			instance = new DomainQueue();
		}
		return instance;
	}
	public static Queue getDomainQueue(){
		return new LinkedList(domainQueue);
	}
	private DomainQueue(){
	}
	public static void   initUnclimedDomain(){
		domainDBOperate = new DomainDBOperate();
		Set<String> unclimedDomainSet = domainDBOperate.getUnclimedDomain();
		domainDBOperate.clearUnclimedDomain();
		for(String domain:unclimedDomainSet){
			addDomain(domain);
		}
		initDomainSet(new DomainDBOperate().getClimedDomain());
	}
	public synchronized static void addPageNum(){
		pageNum++;
	}
	public static int getPageNum(){
		return pageNum;
	}
	public synchronized static void addDomainNum(){
		domainNum++;
	}
	public static int getDomainNum(){
		return domainNum;
	}
	public static  void addDomain(String url){
		//System.out.println("offerUrl"+url+"Queue Size"+domainQueue.size());
		if(!domainQueue.contains(url)){
		synchronized(domainQueue){
			domainQueue.offer(url);
			domainSet.add(url);
			if(domainQueue.size()==1){
			domainQueue.notifyAll();
			}
		}
		}
	}
	public static Set getDomainSet(){
		return domainSet;
	}
	public static void initDomainSet(Set domainset){
		 domainSet =  domainset;
	}
	public static  String pollUrl(){
		synchronized(domainQueue){
		while(domainQueue.isEmpty()){
			try {
					domainQueue.wait();	
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//System.out.println("pollUrl"+"Queue Size"+domainQueue.size());
		return domainQueue.poll();
		}
	}
	public synchronized static String getCurrentDomain() {
		return currentDomain;
	}
	public  synchronized static void setCurrentDomain(String currentDomain) {
		DomainQueue.currentDomain = currentDomain;
	}
	public static void storeUnclimbedDomain(){
		for(String domain:domainQueue){
			System.out.println("store unclimbed domain"+domain);
			new DomainDBOperate().storeUnclimedDomain(domain);
		}
	}
	public static void clear(){
		domainQueue = new LinkedList<String>();
		domainSet=new HashSet<String>(); 
		DomainQueue instance=null;
		pageNum = 0;
		domainNum = 0;
		String currentDomain = null;
		instance=null;
	}
}
