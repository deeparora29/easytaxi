package com.btit.common.tree.action;

import java.util.ArrayList;
import java.util.List;

import com.btit.common.tree.bo.NormalTree;

public class TestNormalTree {
	/**
	 * 构造树
	 * @return
	 */
	public List<NormalTree> getNormalList(){
		List<NormalTree> list = new ArrayList<NormalTree>();
		NormalTree tree = new NormalTree();
		tree.setNodeId("100");
		tree.setNodeName("食物");
		tree.setPid("-1");
		tree.setPname("");
		list.add(tree);
		
		tree = new NormalTree();
		tree.setNodeId("200");
		tree.setNodeName("玩具");
		tree.setPid("-1");
		tree.setPname("");
		list.add(tree);
		
		tree = new NormalTree();
		tree.setNodeId("300");
		tree.setNodeName("书");
		tree.setPid("-1");
		tree.setPname("");
		list.add(tree);
		
		tree = new NormalTree();
		tree.setNodeId("400");
		tree.setNodeName("语言");
		tree.setPid("-1");
		tree.setPname("");
		list.add(tree);
		
		tree = new NormalTree();
		tree.setNodeId("110");
		tree.setNodeName("水果");
		tree.setPid("100");
		tree.setPname("食物");
		list.add(tree);
		
		tree = new NormalTree();
		tree.setNodeId("120");
		tree.setNodeName("肉");
		tree.setPid("100");
		tree.setPname("食物");
		list.add(tree);
		
		tree = new NormalTree();
		tree.setNodeId("130");
		tree.setNodeName("奶制品");
		tree.setPid("100");
		tree.setPname("食物");
		list.add(tree);
		
		tree = new NormalTree();
		tree.setNodeId("111");
		tree.setNodeName("西红柿");
		tree.setPid("110");
		tree.setPname("水果");
		list.add(tree);
		
		tree = new NormalTree();
		tree.setNodeId("112");
		tree.setNodeName("香蕉");
		tree.setPid("110");
		tree.setPname("水果");
		list.add(tree);
		
		tree = new NormalTree();
		tree.setNodeId("113");
		tree.setNodeName("苹果");
		tree.setPid("110");
		tree.setPname("水果");
		list.add(tree);
		
		tree = new NormalTree();
		tree.setNodeId("114");
		tree.setNodeName("荔枝");
		tree.setPid("110");
		tree.setPname("水果");
		list.add(tree);
		
		tree = new NormalTree();
		tree.setNodeId("210");
		tree.setNodeName("PSP2");
		tree.setPid("200");
		tree.setPname("玩具");
		list.add(tree);
		
		tree = new NormalTree();
		tree.setNodeId("220");
		tree.setNodeName("小霸王学习机");
		tree.setPid("200");
		tree.setPname("玩具");
		list.add(tree);
		
		tree = new NormalTree();
		tree.setNodeId("230");
		tree.setNodeName("任天堂");
		tree.setPid("200");
		tree.setPname("玩具");
		list.add(tree);
		
		tree = new NormalTree();
		tree.setNodeId("310");
		tree.setNodeName("小人书");
		tree.setPid("300");
		tree.setPname("书");
		list.add(tree);
		
		tree = new NormalTree();
		tree.setNodeId("410");
		tree.setNodeName("英语");
		tree.setPid("400");
		tree.setPname("语言");
		list.add(tree);
		
		tree = new NormalTree();
		tree.setNodeId("420");
		tree.setNodeName("汉语");
		tree.setPid("400");
		tree.setPname("语言");
		list.add(tree);
		return list; 
	}
	
	

}
