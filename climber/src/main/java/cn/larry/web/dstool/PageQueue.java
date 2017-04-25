package cn.larry.web.dstool;

import java.util.LinkedList;
import java.util.Queue;

import cn.larry.mysearcher.entity.Pages;

public class PageQueue {
	private static Queue<Pages> pgQueue = new LinkedList<Pages>();
	private static PageQueue instance = null;
	private static int storedPageNum = 0;
	public static PageQueue getInstance(){
		if(instance == null){
			instance = new PageQueue();
		}
		return instance;
	}
	private PageQueue(){
		//urlQueue.offer(url);
	}
	public static int getStoredPageNum (){
		return storedPageNum;
	}
	public synchronized static void addStoredPageNum(){
		storedPageNum++;
	}
	public static  void offerPage(Pages page){
		//System.out.println("offerUrl"+url+"Queue Size"+pgQueue.size());
		if(DomainQueue.getDomainSet().contains(page.getUrl())){
			return;
		}
		synchronized(pgQueue){
			pgQueue.offer(page);
			if(pgQueue.size()==1){
			pgQueue.notifyAll();
			}
		}
	}
	public static  Pages pollPage(){
		synchronized(pgQueue){
		while(pgQueue.isEmpty()){
			try {
					pgQueue.wait();	
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return pgQueue.poll();
		}
	}
	public static void clear(){
		 pgQueue = new LinkedList<Pages>();
		 instance = null;
		 storedPageNum = 0;
	}
}
