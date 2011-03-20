package com.easytaxi.common.tree.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.asiainfo.cscqm.login.bo.StaffInfo;
import com.asiainfo.cscqm.qmgr.bo.QualityStaff;
import com.easytaxi.common.exception.ServiceException;
import com.easytaxi.common.tree.bo.StaffInfoTree;

public interface IStaffInfoTreeDao {
	/**
	 * 获取所有的团队，班组，员工
	 * @return
	 * @throws DataAccessException
	 */
	public List<StaffInfoTree> getStaffInfoTreeList() throws DataAccessException;
	
	/**
	 *  获取所有的团队
	 * @return
	 * @throws DataAccessException
	 */
	public List<StaffInfoTree> getStaffInfoTreeTeamList() throws DataAccessException;
	/**
	 *   根据团队获取对应的班组
	 * @param teamId
	 * @return
	 * @throws DataAccessException
	 */
	public List<StaffInfoTree> getStaffInfoTreeGroupListByTeamId(String teamId) throws DataAccessException;
	/**
	 * 根据班组获取班组对应的员工
	 * @param groupId
	 * @return
	 * @throws DataAccessException
	 */
	public List<StaffInfoTree> getStaffInfoTreeListByGroupId(String groupId) throws DataAccessException;
	/**
	 * 获得当前月相应质检类型的员工信息
	 * @param QualityStaff
	 * @return
	 * @throw ServiceException
	 */
	public List<StaffInfoTree> getQmStaffInfoListThisMonth(QualityStaff qualityStaff)throws DataAccessException;

}
