package cn.larry.mysearcher.dao;

import java.util.List;

import cn.larry.mysearcher.entity.Pages;

public interface PagesDao {
	public List<Pages> getPagesByUrl(String url);
	public List<Pages> getAllPages(String url);
	public List<Pages> getPagesByKeyword(String keyword);
	public void insertPage(Pages page);
	public void deletePageByUrl(String url);
	
}
