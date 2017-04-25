package cn.larry.mysearcher.entity;

public class UnclimbedDomain {
	private int id;
	private String domain;
	public UnclimbedDomain(){
		
	}
	public UnclimbedDomain(String domain){
		this.domain= domain;
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
}
