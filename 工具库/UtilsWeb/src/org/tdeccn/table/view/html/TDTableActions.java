package org.tdeccn.table.view.html;

import org.extremecomponents.table.core.TableConstants;
import org.extremecomponents.table.core.TableModel;
import org.extremecomponents.table.view.html.TableActions;
import org.tdeccn.table.core.TDTableConstants;

public class TDTableActions extends TableActions {
	private String formId;
	private String formAction;
	private String formMethod;
	private String pageNoId;

	public TDTableActions(TableModel model){
		super(model);
        formId=model.getTableHandler().getTable().getTableId();
        formAction=model.getTableHandler().getTable().getAction();
        formMethod=model.getTableHandler().getTable().getMethod();
        pageNoId=model.getTableHandler().prefixWithTableId()+TableConstants.PAGE;
     
		
	}
	
    public String getExportAction(String exportView, String exportFileName) {
    	//(formid,action,method,etiid,type,fileName)
        StringBuffer action = new StringBuffer("javascript:");
        action.append("EccnUtil.doExport(");
        action.append("'"+formId+"',"); 
        action.append("'"+formAction+"',");
        action.append("'"+formMethod+"',");
        action.append("'"+TableConstants.EXPORT_TABLE_ID+"',");
        action.append("'"+exportView+"',");
        action.append("'"+exportFileName+"'");
        action.append(");");
        return action.toString();
    }
    
    public String getGotoPagesAction() {
        StringBuffer action = new StringBuffer("EccnUtil.gotoPageByInput(");

        action.append("'"+formId+"','"+formAction+"','"+formMethod+"',");
        action.append("'"+pageNoId+"','"+TableConstants.EXPORT_TABLE_ID+"',");
        action.append("'"+getTableModel().getTableHandler().prefixWithTableId() + TDTableConstants.PAGES+"'");
        
        action.append(");");
        
        
        String onInvokeAction = getTableModel().getTableHandler().getTable().getOnInvokeAction();
        if (onInvokeAction!=null&&onInvokeAction.length()>0){
        	action.append(onInvokeAction);
        }

        
        return action.toString();
    }

    
    public String getPageNavAction(int page) {

    	
        StringBuffer action = new StringBuffer("javascript:EccnUtil.gotoPage(");

        action.append("'"+formId+"','"+formAction+"','"+formMethod+"',");
        action.append("'"+pageNoId+"','"+TableConstants.EXPORT_TABLE_ID+"',"+page);
        
        action.append(");");
        
        
        String onInvokeAction = getTableModel().getTableHandler().getTable().getOnInvokeAction();
        if (onInvokeAction!=null&&onInvokeAction.length()>0){
        	action.append(onInvokeAction);
        }

        
        return action.toString();
    }
}
