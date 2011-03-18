/**
 * <p>Copyright ? 2008 BTIT International Group Co. Ltd. </p>
 * <p>Project            :JZDW</p>
 * <p>JDK version used  :jdk1.5.0</p>
 * <p>Comments         :字段控制拦截器;</p>
 * <p>Version          :2.0</p>
 * <p>Modification history:2008.10.27</p>
 * <p>1.2008.10.27 Yuxd new</p>
 **/
package com.btit.cuncu.test.zdx;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.StrutsStatics;



import com.btit.cuncu.test.AppContext;
import com.btit.cuncu.test.menuPrograms.SupperActionSupport;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;


/**
 * <p>Title: 字段控制拦截器;</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright ? 2008</p>
 * <p>Company: BTIT</p>
 * @author :Yuxd
 * @version 2.0
 */
public class ColumnControlInterceptor extends AbstractInterceptor {
	/**
	 * 串行序号
	 */
	private static final long serialVersionUID = 3518094085530453904L;
	
	

	/* （非 Javadoc）
	 * @see com.opensymphony.xwork2.interceptor.AbstractInterceptor#intercept(com.opensymphony.xwork2.ActionInvocation)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
//		得到action实例
		Object action = invocation.getAction();
		
		ActionContext actionContext = invocation.getInvocationContext();
		HttpServletRequest request = (HttpServletRequest) actionContext
				.get(StrutsStatics.HTTP_REQUEST);
		
		String uri = "";
		if(request != null){
			uri = request.getRequestURI();
		}
		//System.err.println("uri:" + uri);
		
		
		String executeProgram = AppContext.getExecuteProgramByUri(uri);
		//System.err.println("executeProgram:" + executeProgram);
		Map columnControlMap = AppContext.getProgramRwMap(executeProgram);
		
		
		((SupperActionSupport) action).setColumnControlMap(columnControlMap);
		
        return invocation.invoke();   
	}
}
