package cn.larry.web.climb;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



import cn.larry.mysearcher.entity.Pages;
import cn.larry.web.dstool.PageQueue;
import cn.larry.web.dstool.DomainQueue;
import cn.larry.web.util.TextAnalyse;

public class DomainClimber  implements WebClimb,Runnable  {
	
	Queue<String> domainQueue ;
	private HashSet<String> domainSet; 
	private static boolean iscontinue ; 
	public static void stop(){
		iscontinue = false;
	}
	private void init(){
		 domainSet=new HashSet<String>();
		 iscontinue = true;
	}
	public DomainClimber(){
		System.out.println("init a domainclimber");
		init();
		domainQueue = DomainQueue.getDomainQueue();
	}
	public DomainClimber(String startdomain){
		domainQueue.offer(startdomain);
		DomainQueue.addDomain(startdomain);
	}
	@Override
	public void addURL(List<String> urlLst) {
		// TODO Auto-generated method stub
		for(String str:urlLst){
			
			if(str.matches("http(s)?://(.)*")){
				String domain = null;
				Pattern p = Pattern.compile("http://([^/])*/",Pattern.CASE_INSENSITIVE);
				Matcher m = p.matcher(str);
				while(m.find()){
					 domain= str.substring(m.start(),m.end());	 
				}
				boolean isValid = (str.endsWith("/")||str.endsWith(".html")||str.endsWith(".htm")||str.endsWith(".jsp")||str.endsWith(".asp")||str.endsWith(".php"));
				if(isValid&&!domainSet.contains(domain)){
					DomainQueue.addDomain(domain);
					domainQueue.offer(domain);
					domainSet.add(domain);
					//System.out.println("insertdomain--------"+domain);
				}
			}
		}
	}

	@Override
	public void storePage(Pages page) {
		// TODO Auto-generated method stub
		PageQueue.offerPage(page);
	}

	@Override
	public String getPage(String URL) {
		// TODO Auto-generated method stub
		return (new PageGetter()).getPage(URL);
	}
	
	public void run(){
		String currentUrl;
		TextAnalyse  textAnalyse = new TextAnalyse();
		while((currentUrl= (String) domainQueue.poll()) != null&&iscontinue){
			String page = getPage(currentUrl);
			System.out.println("domainclimb get page url:"+currentUrl);
			List<String> urlLst =  textAnalyse.getURL(page);
			addURL(urlLst);
			Pages pg = textAnalyse.getPageInfo(page, currentUrl);
			storePage(pg);
		}
		
		System.out.println("DomainClimber end--------------------------------------");
	}
}
