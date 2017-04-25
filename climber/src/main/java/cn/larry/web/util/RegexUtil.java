package cn.larry.web.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.larry.web.dstool.Position;

public class RegexUtil {
	private  Pattern ptn;
	private  Matcher mch;
	public  List regexMatch(String text,String pattern){
		List matches = new ArrayList();
		try{
		ptn =Pattern.compile(pattern,Pattern.CASE_INSENSITIVE);
		mch = ptn.matcher(text);
		while(mch.find()){
			if(mch.end()>mch.start()&&mch.start()>0)
				matches.add(new Position(mch.start(),mch.end()));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return matches;
	
	}
	public List getMatch(String text,String pattern){
		List<String> matchStrLst = new ArrayList();
		List<Position> match = regexMatch(text,pattern);
		for(Position pos:match){
			matchStrLst.add(text.substring(pos.getStart(),pos.getEnd()));
		}
		return matchStrLst;
	}
	public List<String> getMatch(String text,String pattern,int start,int end){
		List<String> matchStrLst = new ArrayList();
		List<Position> match = regexMatch(text,pattern);
		for(Position pos:match){
			matchStrLst.add(text.substring(pos.getStart()+start,pos.getEnd()-end));
		}
		return matchStrLst;
	}
}

