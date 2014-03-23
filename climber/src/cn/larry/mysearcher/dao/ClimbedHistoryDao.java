package cn.larry.mysearcher.dao;

import java.util.List;

import cn.larry.mysearcher.entity.ClimbHistory;

public interface ClimbedHistoryDao {
	public List<Object> getClimbHistory();
	public void insertClimbedDomain(ClimbHistory climbHistory);
	public void deleteClimbHistoryByUrl(String url);
}
