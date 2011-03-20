package com.easytaxi.common.tree.action;

import java.util.ArrayList;
import java.util.List;

import com.easytaxi.common.action.BaseAction;
import com.easytaxi.common.taglib.tree.CommonBasicTree;
import com.easytaxi.common.taglib.tree.IBasicTree;
import com.easytaxi.common.taglib.tree.IBasicTreeItem;
import com.easytaxi.common.tree.bo.StaffInfoTree;
import com.easytaxi.common.tree.service.IStaffInfoTreeService;

public class StaffInfoTreeAction extends BaseAction {
	
	private IStaffInfoTreeService getStaffInfoTreeService(){
		return (IStaffInfoTreeService)getBean("staffInfoTreeService");
	}
	
	private IBasicTree treeDesc;
	private IBasicTreeItem[] treeNodes;
	
	/**
	 * 获取菜单
	 * @return
	 */
	public String getStaffInfoTree(){
		logger.debug("获取员工树");
		//重写定义树
		IBasicTree tree = new CommonBasicTree() {
			public String getTreeShowStr() {  //根节点文字显示
				// TODO Auto-generated method stub
				return "所有员工";
			}
			public String getImagePath(){// 自定义图片路径
				return "";
			}
			public boolean getShowCheck() { //呈现复选框
				// TODO Auto-generated method stub
				return true;
			}
			public boolean isLazyLoad(){    // 可以进行懒加载
				return true;
			}
		};
		//若树采用懒加载，那么仅只获取第一层节点（团队的节点）
		List<StaffInfoTree> treeList = null;
		if(tree.isLazyLoad()){
			treeList = getStaffInfoTreeService().getStaffInfoTreeTeamList();
		}else{
			treeList = getStaffInfoTreeService().getStaffInfoTreeList();//一次获取团队
		}
		if(treeList == null)
			treeList = new ArrayList<StaffInfoTree>();
		IBasicTreeItem[] group = new IBasicTreeItem[treeList.size()];
		for(int i = 0; i < treeList.size(); i++){
			StaffInfoTree staffInfoTree = treeList.get(i);
			group[i] = (IBasicTreeItem)staffInfoTree.getAdapter(IBasicTreeItem.class);
		}
		this.setTreeDesc(tree);
		this.setTreeNodes(group);
		return this.SUCCESS;
	}

	public IBasicTree getTreeDesc() {
		return treeDesc;
	}

	public void setTreeDesc(IBasicTree treeDesc) {
		this.treeDesc = treeDesc;
	}

	public IBasicTreeItem[] getTreeNodes() {
		return treeNodes;
	}

	public void setTreeNodes(IBasicTreeItem[] treeNodes) {
		this.treeNodes = treeNodes;
	}
}
