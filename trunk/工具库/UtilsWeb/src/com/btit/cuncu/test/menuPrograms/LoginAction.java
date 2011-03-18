/**
 * <p>Copyright ? 2008 BTIT International Group Co. Ltd. </p>
 * <p>Project            :JZDW</p>
 * <p>JDK version used  :jdk1.5.0</p>
 * <p>Comments         :登录Action</p>
 * <p>Version          :2.0</p>
 * <p>Modification history:2008.10.22</p>
 * <p>1.2008.10.22 Yuxd new</p>
 **/
package com.btit.cuncu.test.menuPrograms;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.btit.cuncu.test.AppContext;
import com.btit.cuncu.test.ApplicationHeader;
import com.opensymphony.xwork2.ActionContext;

/**
 * <p>Title: 登录Action;</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright ? 2008</p>
 * <p>Company: BTIT</p>
 * @author :Yuxd
 * @version 2.0
 */
public class LoginAction extends SupperActionSupport {
	private static final String SUCCESS = null;
	/**
	 * <p>用户登录接口对象</p>
	 */
	private LoginService loginService;
	/**
	 * <p>第一级菜单数目</p>
	 */
	private int menuSize;
	/**
	 * 选择菜单
	 */
	private Menu menu;
	
	/**
	 * <p>选择菜单对应二级菜单列表</p>
	 */
	private List selMenuList;
	/**
	 * <p>选择菜单对应二级菜单数目</p>
	 */
	private int selMenuSize;
	
	/**
	 * <p>第一级菜单列表,元素为Menu对象</p>
	 */
	private List menuList;
	/**
	 * <p>选择菜单对应程序Action</p>
	 */
	private String menuAction;
	
	
	/**
	 * <p>菜单列表</p>
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String menuList() throws Exception {
//		得到菜单列表
		menuList = loginService.getMenuList();
		menuSize = 0;
		if(menuList != null){
			menuSize = menuList.size();
		}

		return SUCCESS;
	}

	/**
	 * <p>菜单信息</p>
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String menuInfo() throws Exception {
		//得到选择菜单
		if(menu != null){
			menu = loginService.getMenu(menu.getMenuId());
	        //得到选择菜单的二级菜单
			selMenuList = loginService.getSelMenuList(menu);
			selMenuSize = 0;
			if(selMenuList != null){
				selMenuSize = selMenuList.size();
			}
		}
		return SUCCESS;
	}


	/**
	 * <p>菜单导航</p>
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String menuNav() throws Exception {
		//得到选择菜单
		if(menu != null){
			menu = loginService.getMenu(menu.getMenuId());
			
			//得到对应程序Action
			menuAction = loginService.getProgram(menu.getApplicationId());
			
			ActionContext ctx = ActionContext.getContext();
			Map session = ctx.getSession();
			Runtime btitRuntime =  new Runtime();
			
			btitRuntime.setMenuId(menu.getMenuId());
			ApplicationHeader applicationHeader = (ApplicationHeader)AppContext.getApplicationMap().get(menu.getApplicationId());
			btitRuntime.setApplicationId(menu.getApplicationId());
			if(applicationHeader != null){
				btitRuntime.setApplicationName(applicationHeader.getApplicationName());
			}
			
		}

		return SUCCESS;
	}

	public List getMenuList() {
		return menuList;
	}

	public void setMenuList(List menuList) {
		this.menuList = menuList;
	}

}
