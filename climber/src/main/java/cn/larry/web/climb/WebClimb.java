package cn.larry.web.climb;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import cn.larry.mysearcher.entity.Pages;


public interface WebClimb   {

	void addURL(List<String> urlLst);
	String getPage(String URL);
	void storePage(Pages page);
	
}
