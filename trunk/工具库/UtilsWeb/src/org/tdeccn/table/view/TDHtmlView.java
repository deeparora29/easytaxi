package org.tdeccn.table.view;

import java.util.Iterator;
import java.util.List;

import org.extremecomponents.table.bean.Column;
import org.extremecomponents.table.core.TableModel;
import org.extremecomponents.table.view.DefaultStatusBar;
import org.extremecomponents.table.view.View;
import org.extremecomponents.table.view.html.CalcBuilder;
import org.extremecomponents.table.view.html.FormBuilder;
import org.extremecomponents.table.view.html.RowBuilder;
import org.extremecomponents.util.HtmlBuilder;
import org.tdeccn.table.core.TDTableConstants;
import org.tdeccn.table.view.html.TDCalcBuilder;
import org.tdeccn.table.view.html.TDRowBuilder;
import org.tdeccn.table.view.html.TDTableBuilder;


public class TDHtmlView implements View {
    private HtmlBuilder html;
    private TDTableBuilder tableBuilder;
    private TDRowBuilder rowBuilder;
    private FormBuilder formBuilder;
    private CalcBuilder calcBuilder;
    
    private String paginationLocation;
    
    boolean bufferView;
    
    protected HtmlBuilder getHtmlBuilder() {
        return html;
    }

    protected CalcBuilder getCalcBuilder() {
        return calcBuilder;
    }

    protected FormBuilder getFormBuilder() {
        return formBuilder;
    }

    protected RowBuilder getRowBuilder() {
        return rowBuilder;
    }

    protected TDTableBuilder getTableBuilder() {
        return tableBuilder;
    }
    
    public void beforeBody(TableModel model) {
        bufferView = model.getTableHandler().getTable().isBufferView();
        if (bufferView) {
            html = new HtmlBuilder();
        } else {
            html = new HtmlBuilder(model.getContext().getWriter());
        }

        tableBuilder = new TDTableBuilder(html, model);
        rowBuilder = new TDRowBuilder(html, model);
        calcBuilder = new TDCalcBuilder(html, model);
        
        formBuilder = new FormBuilder(html, model);
        
        
        paginationLocation=model.getTableHandler().getTable().getAttributeAsString(TDTableConstants.PAGINATION_LOCATION);
        
        

        formBuilder.formStart();

        tableBuilder.themeStart();


        tableBuilder.title();
        
        
         toolbar(model,TDTableConstants.PAGINATION_LOCATION_TOP);

        
        StringBuffer cols=new StringBuffer();
        
        boolean useCustCol="true".equalsIgnoreCase(""+model.getTableHandler().getTable().getAttribute(TDTableConstants.USE_GOTO_PAGE));
        if (useCustCol) {
        	cols=cols.append(customColumnBar(model));
        }
        
        tableBuilder.tableStart();
        
        html.append(cols);
        
        tableBuilder.theadStart();

        
        tableBuilder.filterRow();
        tableBuilder.headerRow();

        tableBuilder.theadEnd();

        tableBuilder.tbodyStart();
        
    }
    

	
    public Object afterBody(TableModel model) {
        calcBuilder.defaultCalcLayout();

       
        String extendRow=(String)(model.getTableHandler().getTable().getAttribute("ExtendRow"));
    	
        if (extendRow!=null && extendRow.length()>0){
        	html.append(extendRow);
        }
        tableBuilder.tbodyEnd();
       
        tableBuilder.tableEnd();

		toolbar(model,TDTableConstants.PAGINATION_LOCATION_BOTTOM);
        
        
		
		
        tableBuilder.themeEnd();

        formBuilder.formEnd();
        
        if (this.bufferView) {
            return html.toString();
        }

        return "";
    }
    
    protected StringBuffer customColumnBar(TableModel model){
    	StringBuffer colhtml=new StringBuffer();
    	
    	
    	getHtmlBuilder().append("<table border=\"1\"  cellspacing=\"0\"  cellpadding=\"0\" ");
    	getHtmlBuilder().styleClass("customColumnBar");
    	getHtmlBuilder().close();

    	getHtmlBuilder().tr(1).close();

        List columns = model.getColumnHandler().getHeaderColumns();
        int i=0;
        String id=null;
        for (Iterator iter = columns.iterator(); iter.hasNext();) {
            Column column = (Column) iter.next();
            getHtmlBuilder().td(1).close();
            getHtmlBuilder().input("checkbox");
            id=model.getTableHandler().prefixWithTableId()+"col_"+i;
            getHtmlBuilder().onclick("EccnUtil.hideOrShowCol(this)");
            getHtmlBuilder().checked();
            getHtmlBuilder().id(id);
            getHtmlBuilder().xclose();
            
            colhtml.append("<COL style=\"display:block;\" id=\""+id+"_c\" ");
            colhtml.append(" width=\""+column.getWidth()+"\" ></COL>");
            i++;
            
            
            getHtmlBuilder().append(column.getTitle());
            getHtmlBuilder().tdEnd();
        }

        getHtmlBuilder().trEnd(1);
    	
        getHtmlBuilder().append("</table>");
        
        return colhtml;
    }
    protected void toolbar(TableModel model,String pos) {
    	if (!pos.equalsIgnoreCase(paginationLocation) 
    			&& !TDTableConstants.PAGINATION_LOCATION_BOTH.equalsIgnoreCase(paginationLocation)){
        	String extendTool=(String)(model.getTableHandler().getTable().getAttribute("ExtendTool"));
        	if (extendTool!=null && extendTool.length()>0 && pos.equalsIgnoreCase(TDTableConstants.PAGINATION_LOCATION_BOTTOM) ){
        		getHtmlBuilder().div().style("width:100%;border:0px;padding:2px;text-align:center;");
        		getHtmlBuilder().close();
        		getHtmlBuilder().append(extendTool);
        		getHtmlBuilder().divEnd();
        	}
        	
    		return;
    	}
    	
    	getHtmlBuilder().append("<table border=\"0\"  cellspacing=\"0\"  cellpadding=\"0\" ");
    	getHtmlBuilder().styleClass(model.getTableHandler().getTable().getStyleClass()+"Toolbar");
    	getHtmlBuilder().width(model.getTableHandler().getTable().getWidth());
    	getHtmlBuilder().style("margin:0px;");
    	getHtmlBuilder().close();
        new TDToolbar(getHtmlBuilder(), model).layout();
        getHtmlBuilder().append("</table>");
    }

    public void body(TableModel model, Column column) {
        if (column.isFirstColumn()) {
            rowBuilder.rowStart();
        }

        html.append(column.getCellDisplay());

        if (column.isLastColumn()) {
            rowBuilder.rowEnd();
        }
    }
    
    protected void statusBar(TableModel model) {
        new DefaultStatusBar(getHtmlBuilder(), model).layout();
    }
    
    protected void titleBar(TableModel model) {
        new DefaultStatusBar(getHtmlBuilder(), model).layout();
    }
}
