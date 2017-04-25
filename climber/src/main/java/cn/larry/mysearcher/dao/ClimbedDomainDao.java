package cn.larry.mysearcher.dao;

import java.util.List;

import cn.larry.mysearcher.entity.ClimbedDomain;


public interface ClimbedDomainDao {
	public List<ClimbedDomain> getClimbedDomain();
	public void insertClimbedDomain(ClimbedDomain climbedDomain);
	public void deleteClimbedDomainByUrl(String url);
}
