package cn.larry.web.climb;

import java.util.HashMap;
import java.util.Map;

public class WebSpiderManager {
	
	private static int instanceIndex = 0;
	private static Map<Integer,WebSpider > instanceMap = new HashMap();
	private WebSpiderManager(){
	}
	public static int regeistWebSpiderInstance(WebSpider webSpider){
		instanceMap.put(instanceIndex, webSpider);
		instanceIndex++;
		return instanceIndex-1;
	}
	public static WebSpider getWebSpiderInstance(int index){
		return instanceMap.get(index);
	}
	public static void removeInstance(int index){
		instanceMap.remove(index);
	}

}
