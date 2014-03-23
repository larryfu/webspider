package cn.larry.mysearcher.entity;

public class ClimbHistory {
	private int id;
	private String starttime;
	private String endtime;
	private String  startdomain;
	private String  enddomain;
	private int climbeddomain;
	private int storedpage;
	private int climbedpage;
	public ClimbHistory(String starttime,String endtime,String  startdomain,String  enddomain,int climbeddomain,int storedpage,int climbedpage){
		this.starttime=starttime;
		this.endtime=endtime;
		this.startdomain=startdomain;
		this.enddomain=enddomain;
		this.climbeddomain=climbeddomain;
		this.storedpage=storedpage;
		this.climbedpage=climbedpage;
	}
	public ClimbHistory(){
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public String getStartdomain() {
		return startdomain;
	}
	public void setStartdomain(String startdomain) {
		this.startdomain = startdomain;
	}
	public String getEnddomain() {
		return enddomain;
	}
	public void setEnddomain(String enddomain) {
		this.enddomain = enddomain;
	}
	public int getClimbeddomain() {
		return climbeddomain;
	}
	public void setClimbeddomain(int climbeddomain) {
		this.climbeddomain = climbeddomain;
	}
	public int getStoredpage() {
		return storedpage;
	}
	public void setStoredpage(int storedpage) {
		this.storedpage = storedpage;
	}
	public int getClimbedpage() {
		return climbedpage;
	}
	public void setClimbedpage(int climbedpage) {
		this.climbedpage = climbedpage;
	}
}
