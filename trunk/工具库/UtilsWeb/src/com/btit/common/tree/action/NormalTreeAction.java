package com.btit.common.tree.action;

import java.util.ArrayList;
import java.util.List;
import com.btit.common.taglib.tree.CommonBasicTree;
import com.btit.common.taglib.tree.IBasicTree;
import com.btit.common.taglib.tree.IBasicTreeItem;
import com.btit.common.tree.bo.NormalTree;
import com.opensymphony.xwork2.ActionSupport;

public class NormalTreeAction extends ActionSupport {
	
	private IBasicTree treeDesc;
	private IBasicTreeItem[] treeNodes;
	
	private IBasicTree checkboxTreeDesc;
	private IBasicTreeItem[] checkboxTreeNodes;
	
	/**
	 * 获取菜单
	 * @return
	 */
	public String getNoramlTree(){
		System.out.println("获取树");
		//重写定义树
		IBasicTree tree = new CommonBasicTree() {
			public String getTreeShowStr() {  //根节点文字显示
				// TODO Auto-generated method stub
				return "全部";
			}
			public String getImagePath(){// 自定义图片路径
				return "";
			}
		};
		
		IBasicTree checkboxTree = new CommonBasicTree() {
			public String getTreeShowStr() {  //根节点文字显示
				// TODO Auto-generated method stub
				return "全部";
			}
			public String getImagePath(){// 自定义图片路径
				return "";
			}
			public boolean getShowCheck() { //呈现复选框
				// TODO Auto-generated method stub
				return true;
			}
//			public boolean isLazyLoad(){    // 可以进行懒加载
//				return true;
//			}
		};
		//若树采用懒加载，那么仅只获取第一层节点（团队的节点）
		List<NormalTree> treeList = null;
		//todo:获取树的构造
		TestNormalTree test = new TestNormalTree();
		treeList = test.getNormalList();
		IBasicTreeItem[] group = new IBasicTreeItem[treeList.size()];
		for(int i = 0; i < treeList.size(); i++){
			NormalTree normalTree = treeList.get(i);
			group[i] = (IBasicTreeItem)normalTree.getAdapter(IBasicTreeItem.class);
		}
		this.setTreeDesc(tree);
		this.setTreeNodes(group);
		
		this.setCheckboxTreeDesc(checkboxTree);
		this.setCheckboxTreeNodes(group);
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

	public IBasicTree getCheckboxTreeDesc() {
		return checkboxTreeDesc;
	}

	public void setCheckboxTreeDesc(IBasicTree checkboxTreeDesc) {
		this.checkboxTreeDesc = checkboxTreeDesc;
	}

	public IBasicTreeItem[] getCheckboxTreeNodes() {
		return checkboxTreeNodes;
	}

	public void setCheckboxTreeNodes(IBasicTreeItem[] checkboxTreeNodes) {
		this.checkboxTreeNodes = checkboxTreeNodes;
	}

}
