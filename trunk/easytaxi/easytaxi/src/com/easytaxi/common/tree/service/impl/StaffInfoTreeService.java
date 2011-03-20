package com.easytaxi.common.tree.service.impl;

import java.util.List;

import com.asiainfo.cscqm.login.bo.StaffInfo;
import com.asiainfo.cscqm.qmgr.bo.QualityStaff;
import com.easytaxi.common.exception.ServiceException;
import com.easytaxi.common.service.BaseService;
import com.easytaxi.common.tree.bo.StaffInfoTree;
import com.easytaxi.common.tree.dao.IStaffInfoTreeDao;
import com.easytaxi.common.tree.service.IStaffInfoTreeService;
/**
 * @spring.bean name="staffInfoTreeService"
 * @spring.property name="staffInfoTreeDao" ref="staffInfoTreeDao"
 * @author renmian
 *
 */
public class StaffInfoTreeService extends BaseService implements
		IStaffInfoTreeService {
	
	private IStaffInfoTreeDao staffInfoTreeDao;
	
	public List<StaffInfoTree> getStaffInfoTreeGroupListByTeamId(String teamId)
			throws ServiceException {
		// TODO Auto-generated method stub
		try {
			return getStaffInfoTreeDao().getStaffInfoTreeGroupListByTeamId(teamId);
		} catch (Exception e) {
			// TODO: handle exception
			String error = buildError("StaffInfoTreeService.getStaffInfoTreeGroupListByTeamId()出错：", e);
			throw new ServiceException(error);
		}
	}

	public List<StaffInfoTree> getStaffInfoTreeListByGroupId(String groupId)
			throws ServiceException {
		// TODO Auto-generated method stub
		try {
			return getStaffInfoTreeDao().getStaffInfoTreeListByGroupId(groupId);
		} catch (Exception e) {
			// TODO: handle exception
			String error = buildError("StaffInfoTreeService.getStaffInfoTreeListByGroupId()出错：", e);
			throw new ServiceException(error);
		}
	}

	public List<StaffInfoTree> getStaffInfoTreeTeamList()
			throws ServiceException {
		// TODO Auto-generated method stub
		try {
			return getStaffInfoTreeDao().getStaffInfoTreeTeamList();
		} catch (Exception e) {
			// TODO: handle exception
			String error = buildError("StaffInfoTreeService.getStaffInfoTreeTeamList()出错：", e);
			throw new ServiceException(error);
		}
	}

	public List<StaffInfoTree> getStaffInfoTreeList() throws ServiceException {
		// TODO Auto-generated method stub
		try {
			return getStaffInfoTreeDao().getStaffInfoTreeList();
		} catch (Exception e) {
			// TODO: handle exception
			String error = buildError("StaffInfoTreeService.getStaffInfoTreeList()出错：", e);
			throw new ServiceException(error);
		}
	}

	
	public List<StaffInfoTree> getQmStaffInfoListThisMonth(
			QualityStaff qualityStaff) throws ServiceException {
		// TODO Auto-generated method stub
		return getStaffInfoTreeDao().getQmStaffInfoListThisMonth(qualityStaff);
	}

	public IStaffInfoTreeDao getStaffInfoTreeDao() {
		return staffInfoTreeDao;
	}

	public void setStaffInfoTreeDao(IStaffInfoTreeDao staffInfoTreeDao) {
		this.staffInfoTreeDao = staffInfoTreeDao;
	}

	

}
