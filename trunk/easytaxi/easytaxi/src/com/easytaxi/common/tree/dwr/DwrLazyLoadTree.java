package com.easytaxi.common.tree.dwr;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.easytaxi.common.exception.ServiceException;
import com.easytaxi.common.tree.bo.StaffInfoTree;
import com.easytaxi.common.tree.service.IStaffInfoTreeService;
import com.easytaxi.common.util.BeanFactoryUtil;

/**
 * 懒加载获取树
 * 
 * @author renmian
 * 
 */
public class DwrLazyLoadTree {
	private final Log logger = LogFactory.getLog(getClass());

	private IStaffInfoTreeService getStaffInfoTreeService() {
		return (IStaffInfoTreeService) BeanFactoryUtil
				.getBean("staffInfoTreeService");
	}

	public List<StaffInfoTree> getChildStaffInfoTreeList(String nodeId, String nodeName,
			int nodeLevel) {
		List<StaffInfoTree> staffInfoTreeList = null;
		try {
			if (nodeLevel == 1) {// 根据团队获取班组
				staffInfoTreeList = getStaffInfoTreeService()
						.getStaffInfoTreeGroupListByTeamId(nodeId);
			} else if (nodeLevel == 2) {// 根据班组获取其员工
				staffInfoTreeList = getStaffInfoTreeService()
						.getStaffInfoTreeListByGroupId(nodeId);
			}
		} catch (ServiceException e) {
			// TODO: handle exception
			logger.error(e);
		}
	
		if(staffInfoTreeList == null || staffInfoTreeList.size() == 0){
			staffInfoTreeList = new ArrayList<StaffInfoTree>();
			StaffInfoTree staffInfoTree = new StaffInfoTree();
			staffInfoTree.setPid(nodeId);
			staffInfoTree.setPname(nodeName);
			staffInfoTree.setNodeId(nodeId);
			staffInfoTree.setNodeName(nodeName);
			staffInfoTreeList.add(staffInfoTree);
		}	
		return staffInfoTreeList;
	}

}
