package cn.larry.web.climb;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import cn.larry.mysearcher.entity.Pages;
import cn.larry.web.dstool.PageQueue;
import cn.larry.web.dstool.DomainQueue;
import cn.larry.web.storage.DomainDBOperate;
import cn.larry.web.util.TextAnalyse;

public class DomainInsideClimber  implements WebClimb,Runnable{
	
	private static boolean iscontinue ;
	private int pageNum ;
	String currentDomain;
	public static void stop(){
		iscontinue = false;
	}
	private  Set<String>  urlSet;
	Queue<String> currentUrlQueue ;
	DomainDBOperate domainDBOperate ;
	public DomainInsideClimber(){
		init();
	}
	private void init(){
		urlSet = new HashSet<String>();
		currentUrlQueue = new LinkedList<String>();
		domainDBOperate = new DomainDBOperate();
		iscontinue = true; 
		pageNum = 0;
	}
	@Override
	public void run() {
		TextAnalyse  textAnalyse = new TextAnalyse();
		// TODO Auto-generated method stub
		while(iscontinue){
			if(currentUrlQueue.isEmpty()){
				if(currentDomain!=null){
				domainDBOperate.storeDomain(currentDomain,pageNum);
				}
				pageNum = 0;
				currentDomain =  DomainQueue.pollUrl();
				urlSet.clear();
				currentUrlQueue.offer(currentDomain);
				DomainQueue.addDomainNum();
				System.out.println("get new domain "+currentDomain);
				DomainQueue.setCurrentDomain(currentDomain);
			}
					String currentUrl = currentUrlQueue.poll();
					String page = getPage(currentUrl);
					pageNum++;
					System.out.println("insideclimb get page url"+currentUrl);
					List<String> urlLst = textAnalyse.getURL(page);
					addURL(urlLst);
					Pages pg= textAnalyse.getPageInfo(page, currentUrl);
					storePage(pg);
					DomainQueue.addPageNum();
		}
		
	}

	@Override
	public void addURL(List<String> urlLst) {
		// TODO Auto-generated method stub
		for(String str:urlLst){
			//System.out.println("url-------"+str);
			if(str.startsWith(currentDomain)){
				boolean isValid = (str.endsWith("/")||str.endsWith(".html")||str.endsWith(".htm")||str.endsWith(".jsp")||str.endsWith(".asp")||str.endsWith(".php"));
				if(isValid&&!urlSet.contains(str)){
				urlSet.add(str);
				currentUrlQueue.offer(str);
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

}
