package cn.larry.mysearcher.dao;

import java.util.List;

import cn.larry.mysearcher.entity.UnclimbedDomain;

public interface UnclimbedDomainDao {
	public List<UnclimbedDomain> getUnclimbedDomain();
	public void insertClimbedDomain(UnclimbedDomain unclimbedDomain);
	public void deleteUnclimbedDomainByUrl(String url);
	public void deleteUnclimbedDomain();
}
