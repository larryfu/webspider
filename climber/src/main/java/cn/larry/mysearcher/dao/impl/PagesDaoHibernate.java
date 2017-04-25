package cn.larry.mysearcher.dao.impl;

import java.util.List;

import javax.annotation.Resource;


import cn.larry.mysearcher.dao.PagesDao;
import cn.larry.mysearcher.daoextends.HibernateDaoExtend;
import cn.larry.mysearcher.entity.Pages;

public class PagesDaoHibernate  implements PagesDao{
	
	@Resource
	private HibernateDaoExtend hibernateDaoExtend;

	public HibernateDaoExtend getHibernateDaoExtend(){
		return this.hibernateDaoExtend;
	}
	public void setHibernateDaoExtend(HibernateDaoExtend hibernateDaoExtend){
		this.hibernateDaoExtend=hibernateDaoExtend;
	}
	
	public List<Pages> getPagesByUrl(String url) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Pages> getAllPages(String url) {
		// TODO Auto-generated method stub
		return hibernateDaoExtend.find("from cn.larry.mysearcher.entity.ClimbHistory");
	}

	public List<Pages> getPagesByKeyword(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}


	public void deletePageByUrl(String url) {
		// TODO Auto-generated method stub
		
	}
	//@Override
	public void insertPage(Pages page) {
		// TODO Auto-generated method stub
		hibernateDaoExtend.save(page);
	}

}
