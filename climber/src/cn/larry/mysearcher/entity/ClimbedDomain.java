package cn.larry.mysearcher.entity;

public class ClimbedDomain {
	private int id;
	private String domain;
	private String climbtime;
	private int pages;
	public ClimbedDomain(){
		
	}
	public ClimbedDomain(String domain,String climbtime,int pages){
		this.domain = domain;
		this.climbtime=climbtime;
		this.pages=pages;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getClimbtime() {
		return climbtime;
	}
	public void setClimbtime(String climbtime) {
		this.climbtime = climbtime;
	}
	public int getPages() {
		return pages;
	}
	public void setPages(int pages) {
		this.pages = pages;
	}
	
}
