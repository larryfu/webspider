package cn.larry.web.storage;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import cn.larry.mysearcher.daoextends.HibernateDaoExtend;

public class HibernateSupport {
	private static ApplicationContext ctx = null;
	public void initApplicationContext(){
		if(ctx == null){
			try{
				//String path = this.getClass().getClassLoader().getResource(".").getPath()+"applicationContext.xml";
				String path = this.getClass().getClassLoader().getResource("").toString();
				path = path.substring(0,path.length()-8);
					System.out.println(path+"------------------------------------------------------------------");
				 ctx = new FileSystemXmlApplicationContext(path+"applicationContext.xml");
				}catch(Exception e){
					e.printStackTrace();
				}
		}
	}
	public  HibernateDaoExtend getHibernateDaoExtend(){
		HibernateDaoExtend hibernateDaoExtend = null;
		try{
				initApplicationContext();
				hibernateDaoExtend = ctx.getBean("hibernateDaoExtend",HibernateDaoExtend.class);
			}catch(Exception e){
				e.printStackTrace();
			}
		return hibernateDaoExtend;
	}
	public Object getBean(String beanName){
		Object obj = new Object();
		try {
			initApplicationContext();
			 obj =  ctx.getBean(beanName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
		
	}
}
