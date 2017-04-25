package cn.larry.web.storage;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;

import cn.larry.mysearcher.dao.PagesDao;
import cn.larry.mysearcher.daoextends.HibernateDaoExtend;
import cn.larry.mysearcher.entity.Pages;
import cn.larry.web.dstool.PageQueue;

public class StorageTask implements Runnable{
	private PageQueue  pgQueue = PageQueue.getInstance(); 
	private Pages page = null;
	
	private HibernateDaoExtend hibernateDaoExtend ;
	
	public StorageTask(){
		hibernateDaoExtend=new HibernateSupport().getHibernateDaoExtend();
		}

//	@Override
	public void run() {
		// TODO Auto-generated method stub
		while((page =pgQueue.pollPage())!=null){
			System.out.println("save page-------------------");
			hibernateDaoExtend.save(page);
		}
	}
	
}
