package com.easytaxi.common.tree.service;

import java.util.List;

import com.asiainfo.cscqm.login.bo.StaffInfo;
import com.asiainfo.cscqm.qmgr.bo.QualityStaff;
import com.easytaxi.common.exception.ServiceException;
import com.easytaxi.common.tree.bo.StaffInfoTree;

public interface IStaffInfoTreeService {
	/**
	 * 获取全部的菜单树
	 * @return
	 * @throws ServiceException
	 */
	public List<StaffInfoTree> getStaffInfoTreeList() throws ServiceException;
	/**
	 * 获取所有的团队信息
	 * @return
	 * @throws ServiceException
	 */
	public List<StaffInfoTree> getStaffInfoTreeTeamList() throws ServiceException;
	/**
	 * 根据团队获取班组
	 * @param teamId
	 * @return
	 * @throws ServiceException
	 */
	public List<StaffInfoTree> getStaffInfoTreeGroupListByTeamId(String teamId) throws ServiceException;
	/**
	 * 根据班组获取员工信息
	 * @param groupId
	 * @return
	 * @throws ServiceException
	 */
	public List<StaffInfoTree> getStaffInfoTreeListByGroupId(String groupId) throws ServiceException;
	
	/**
	 * 获得当前月相应质检类型的员工信息
	 * @param QualityStaff
	 * @return
	 * @throw ServiceException
	 */
	public List<StaffInfoTree> getQmStaffInfoListThisMonth(QualityStaff qualityStaff)throws ServiceException;

}
