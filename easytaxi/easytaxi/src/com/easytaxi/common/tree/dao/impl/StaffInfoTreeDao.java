package com.easytaxi.common.tree.dao.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.asiainfo.cscqm.login.bo.StaffInfo;
import com.asiainfo.cscqm.qmgr.bo.QualityStaff;
import com.easytaxi.common.dao.BaseSqlMapDao;
import com.easytaxi.common.tree.bo.StaffInfoTree;
import com.easytaxi.common.tree.dao.IStaffInfoTreeDao;

/**
 * @spring.bean name="staffInfoTreeDao"
 * @spring.property name="sqlMapClient" ref="sqlMapClient"
 * @author renmian
 *
 */
public class StaffInfoTreeDao extends BaseSqlMapDao implements IStaffInfoTreeDao {

	public List<StaffInfoTree> getQmStaffInfoListThisMonth(
			QualityStaff qualityStaff) throws DataAccessException {
		// TODO Auto-generated method stub
		return (List<StaffInfoTree>)getSqlMapClientTemplate().queryForList("getQmStaffInfoListThisMonth", qualityStaff);
	}

	public List<StaffInfoTree> getStaffInfoTreeGroupListByTeamId(String teamId)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return (List<StaffInfoTree>)getSqlMapClientTemplate().queryForList("getStaffInfoTreeGroupListByTeamId", teamId);
	}

	public List<StaffInfoTree> getStaffInfoTreeListByGroupId(String groupId)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return (List<StaffInfoTree>)getSqlMapClientTemplate().queryForList("getStaffInfoTreeListByGroupId", groupId);
	}

	public List<StaffInfoTree> getStaffInfoTreeTeamList()
			throws DataAccessException {
		// TODO Auto-generated method stub
		return (List<StaffInfoTree>)getSqlMapClientTemplate().queryForList("getStaffInfoTreeTeamList");
	}

	public List<StaffInfoTree> getStaffInfoTreeList()
			throws DataAccessException {
		// TODO Auto-generated method stub
		return (List<StaffInfoTree>) this.getSqlMapClientTemplate().queryForList("getStaffInfoTreeList");
	}
	

}
