package cn.larry.web.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import cn.larry.mysearcher.entity.Pages;
import cn.larry.web.dstool.Position;

public class TextAnalyse {
	//private String text;
	private final static String TAGA_PATTERN = "<a([^>])*>";
	private final static String URL_PATTERN = "href=\"([^\"])*\"";
	private final static String TITLE_PATTERN = "<(/)?title([^>])*>";
	//private final static String TITLE_END_PATTERN = "</title([^>])*>";
	private final static String DESCRIPTION_PATTERN  = "<meta name=(\"|')description(\"|')([^>])*>";
	private final static String DECRPTCONTENT_PATTERN = "content=(\"|')([^(\"|')])*(\"|')";
	private final static String CHARSETMETA_PATTERN = "<meta http-equiv=\"Content-Type\"([^>])*>";
	private final static String CHARSET_PATTERN = "charset=([^(\"|')])*(\"|')";
	private  RegexUtil regexUtil = new RegexUtil();
	public  List<String> getURL(String text){
		String str = setCorrectCharset(text);
		List<String> urlLst = new ArrayList<String>();
		List<String> tagStrLst = regexUtil.getMatch(str, TAGA_PATTERN);

		for(String tag:tagStrLst){
			//String tag = text.substring(tagPosLst.get(i).getStart(),tagPosLst.get(i).getEnd());
			List<Position> urlPos =  regexUtil.regexMatch(tag, URL_PATTERN); 
			if(urlPos.size()>0){
			String url = tag.substring(urlPos.get(0).getStart()+6,urlPos.get(0).getEnd()-1);
			urlLst.add(url);
			}
		
		}
		return urlLst;
	}
	public  Pages getPageInfo(String text,String url){
		
		String str = setCorrectCharset(text);
		Pages page = new Pages();
		page.setUrl(url);
		String time = TimeUtil.getCurrentTime();
		page.setAddtime(time);
		List<Position> titlePosLst = regexUtil.regexMatch(str, TITLE_PATTERN);
		if(titlePosLst!=null&&titlePosLst.size()>1){
			int start = titlePosLst.get(0).getEnd();
			int end = titlePosLst.get(1).getStart();
			page.setTitle(str.substring(start,end));
		}else{
			page.setTitle("sorry no title found");
		}
		System.out.println(page.getTitle());
		List<String> descriptionTagLst = regexUtil.getMatch(str,DESCRIPTION_PATTERN);
		for(String descriptionTag:descriptionTagLst){
			List<Position> descriptionPosLst = regexUtil.regexMatch(descriptionTag,DECRPTCONTENT_PATTERN );
			if(descriptionPosLst.size()>0){
			String description= descriptionTag.substring(descriptionPosLst.get(0).getStart()+9,descriptionPosLst.get(0).getEnd()-1);
			page.setDescription(description);
			}else{
				page.setDescription("sorry no description found");
			}
		}
		return page;	
		
	}
	public String getCharset(String text){
			List<String> charsetLst = null;
			List<String> charsetMetaLst = regexUtil.getMatch(text, CHARSETMETA_PATTERN);
			if(charsetMetaLst.size()==0){
				return null;
			}
			charsetLst = regexUtil.getMatch(charsetMetaLst.get(0), CHARSET_PATTERN,8,1);
			if(charsetLst.size()>0){
				String charset =  charsetLst.get(0);
				return charset;
			}else{
				return null;
			}
	}
	
	public String setCorrectCharset(String text){
		String charset = getCharset(text);
		if(charset == null) {
			return text;
		}
		String str = text;
		try {
			str = changeCharset(str,charset);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}
	
	  public String changeCharset(String str, String newCharset) throws UnsupportedEncodingException {
	        if(str != null) {
	            //用默认字符编码解码字符串。与系统相关，中文windows默认为GB2312
	            byte[] bs = str.getBytes();
	            return new String(bs, newCharset);    //用新的字符编码生成字符串
	        }
	        return null;
	    }
}
