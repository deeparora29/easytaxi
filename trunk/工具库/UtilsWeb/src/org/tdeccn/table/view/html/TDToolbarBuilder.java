package org.tdeccn.table.view.html;

import org.extremecomponents.table.bean.Export;
import org.extremecomponents.table.core.TableConstants;
import org.extremecomponents.table.core.TableModel;
import org.extremecomponents.table.view.html.BuilderConstants;
import org.extremecomponents.table.view.html.BuilderUtils;
import org.extremecomponents.table.view.html.ToolbarBuilder;
import org.extremecomponents.table.view.html.toolbar.ImageItem;
import org.extremecomponents.util.HtmlBuilder;
import org.tdeccn.table.core.TDTableConstants;


public class TDToolbarBuilder extends ToolbarBuilder {
	

	private TDTableActions tableActions;
	

    public TDToolbarBuilder(TableModel model) {
        super( model);
        tableActions=new TDTableActions(getTableModel());
    }
    
    public TDToolbarBuilder(HtmlBuilder html, TableModel model) {
        super(html,model);
        tableActions=new TDTableActions(getTableModel());
    }
    
    public void extendTool(){
    	String extendTool=(String)(getTableModel().getTableHandler().getTable().getAttribute("ExtendTool"));
    	if (extendTool==null || extendTool.length()<1) return;
    	getHtmlBuilder().append(extendTool);
    }
    
    public void rowsDisplayed(){

        getHtmlBuilder().span();
        
        getHtmlBuilder().styleClass(TDTableConstants.ROWSDISPLAYED_CSS);
        getHtmlBuilder().close();
        
        getHtmlBuilder().append("每页");
        rowsDisplayedDroplist();
        getHtmlBuilder().append("条");
        
        getHtmlBuilder().spanEnd();
        
        /*
        getHtmlBuilder().img();
        getHtmlBuilder().src(BuilderUtils.getImage(getTableModel(), BuilderConstants.TOOLBAR_ROWS_DISPLAYED_IMAGE));
        getHtmlBuilder().style("border:0");
        getHtmlBuilder().alt(getMessages().getMessage(TDBuilderConstants.TOOLBAR_ROW_DISPLAYED_TOOLTIP));        
        getHtmlBuilder().xclose();
        */
    }
    
    public void gotoPages(){
    	getHtmlBuilder().newline();
        getHtmlBuilder().tabs(4);
        gotoPagesInput();
        /*
        getHtmlBuilder().img();
        getHtmlBuilder().src(BuilderUtils.getImage(getTableModel(), TDBuilderConstants.TOOLBAR_GOTO_PAGE_IMAGE));
        getHtmlBuilder().style("border:0");
        getHtmlBuilder().alt(getMessages().getMessage(TDBuilderConstants.TOOLBAR_GOTO_PAGE_TOOLTIP));        
        getHtmlBuilder().xclose();
        */
    }
    
    public void gotoPagesInput() {
        int currentRowsDisplayed = getTableModel().getLimit().getCurrentRowsDisplayed();
        int currentPage = getTableModel().getLimit().getPage();
		int totalPages = 0;
		int totalRows = getTableModel().getLimit().getTotalRows();
		if (currentRowsDisplayed > 0) {
			totalPages =(int)Math.ceil((double)totalRows / currentRowsDisplayed);
		} else {
			totalPages = 1;
			currentPage = 1;
		}
		
		boolean showTooltips = getTableModel().getTableHandler().getTable().isShowTooltips();
    	
		String action=tableActions.getGotoPagesAction();
		getHtmlBuilder().td(4).styleClass(BuilderConstants.STATUS_BAR_CSS).close();

		
		getHtmlBuilder().input().type("button");
		getHtmlBuilder().styleClass(TDTableConstants.JUMP_PAGE_CSS);
		getHtmlBuilder().onclick(action);
		if (showTooltips) {
			getHtmlBuilder().title("页面跳转");
		}
		getHtmlBuilder().xclose();
		

        getHtmlBuilder().tdEnd();
        
        getHtmlBuilder().td(4).styleClass(BuilderConstants.STATUS_BAR_CSS).close();
        getHtmlBuilder().input("text").name(getPrefixWithTableId() + TDTableConstants.PAGES);
        getHtmlBuilder().value(""+currentPage);
        getHtmlBuilder().styleClass(TDTableConstants.JUMP_PAGE_INPUT_CSS);
        getHtmlBuilder().append(" onkeydown=\"if (event.keyCode && event.keyCode==13 ) {"+action+";return false; } \" ");
        getHtmlBuilder().xclose();
    	
        getHtmlBuilder().append("/").append(""+totalPages).append(TDTableConstants.TOOLBAR_GOTO_PAGE_LABEL);
        getHtmlBuilder().tdEnd();

    }
    
    public String getPrefixWithTableId(){
    	return getTableModel().getTableHandler().prefixWithTableId();
    }

    

    
    
    
    public void firstPageItemAsButton() {
    	
        boolean showTooltips = getTableModel().getTableHandler().getTable().isShowTooltips();
    	boolean isEnabled=BuilderUtils.isFirstPageEnabled(getTableModel().getLimit().getPage());
	
    	
		getHtmlBuilder().input().type("button");
		
        String disabled=null;
        if (!isEnabled){
        	disabled="D";
            getHtmlBuilder().disabled();
        }else{
        	disabled="";
        }
        
		getHtmlBuilder().styleClass(TDTableConstants.FIRST_PAGE_CSS+disabled);
		getHtmlBuilder().onclick(tableActions.getPageNavAction(1));
        if (showTooltips) {
        	getHtmlBuilder().title(getMessages().getMessage(BuilderConstants.TOOLBAR_FIRST_PAGE_TOOLTIP));
        } 
        

		getHtmlBuilder().xclose();
		


    }
    
    public void prevPageItemAsButton() {
    	int prevPage = getTableModel().getLimit().getPage()-1;
    	
    	
        boolean showTooltips = getTableModel().getTableHandler().getTable().isShowTooltips();
    	boolean isEnabled=BuilderUtils.isPrevPageEnabled(getTableModel().getLimit().getPage());
    	
    	
    	
		getHtmlBuilder().input().type("button");
		
        String disabled=null;
        if (!isEnabled){
        	disabled="D";
            getHtmlBuilder().disabled();
        }else{
        	disabled="";
        }
        
		getHtmlBuilder().styleClass(TDTableConstants.PREV_PAGE_CSS+disabled);
		getHtmlBuilder().onclick(tableActions.getPageNavAction(prevPage));
        if (showTooltips) {
        	getHtmlBuilder().title(getMessages().getMessage(BuilderConstants.TOOLBAR_PREV_PAGE_TOOLTIP));
        } 
        if (!isEnabled){
            getHtmlBuilder().disabled();
        }
		getHtmlBuilder().xclose();
		
		


    }
    
    public void nextPageItemAsButton() {
    	int nextPage = getTableModel().getLimit().getPage()+1;
    	int lastPage = BuilderUtils.getTotalPages(getTableModel());
    	
    	
        boolean showTooltips = getTableModel().getTableHandler().getTable().isShowTooltips();
    	boolean isEnabled=BuilderUtils.isNextPageEnabled(getTableModel().getLimit().getPage(),lastPage);
    	
    	
		getHtmlBuilder().input().type("button");
		
        String disabled=null;
        if (!isEnabled){
        	disabled="D";
            getHtmlBuilder().disabled();
        }else{
        	disabled="";
        }
        
		getHtmlBuilder().styleClass(TDTableConstants.NEXT_PAGE_CSS+disabled);
		getHtmlBuilder().onclick(tableActions.getPageNavAction(nextPage));
        if (showTooltips) {
        	getHtmlBuilder().title(getMessages().getMessage(BuilderConstants.TOOLBAR_NEXT_PAGE_TOOLTIP));
        } 
        if (!isEnabled){
            getHtmlBuilder().disabled();
        }
		getHtmlBuilder().xclose();
    	
       

    }
    
    public void lastPageItemAsButton() {
    	int lastPage = BuilderUtils.getTotalPages(getTableModel());
    	
   	
        boolean showTooltips = getTableModel().getTableHandler().getTable().isShowTooltips();
    	boolean isEnabled=BuilderUtils.isLastPageEnabled(getTableModel().getLimit().getPage(),lastPage);
    	
    	
		getHtmlBuilder().input().type("button");
		
        String disabled=null;
        if (!isEnabled){
        	disabled="D";
            getHtmlBuilder().disabled();
        }else{
        	disabled="";
        }
        
		getHtmlBuilder().styleClass(TDTableConstants.LAST_PAGE_CSS+disabled);
		getHtmlBuilder().onclick(tableActions.getPageNavAction(lastPage));
        if (showTooltips) {
        	getHtmlBuilder().title(getMessages().getMessage(BuilderConstants.TOOLBAR_LAST_PAGE_TOOLTIP));
        } 
        if (!isEnabled){
            getHtmlBuilder().disabled();
        }
		getHtmlBuilder().xclose();
      

    }

    
    
    public void exportItemAsImage(Export export) {
        ImageItem item = new ImageItem();
        item.setTooltip(export.getTooltip());
        item.setImage(BuilderUtils.getImage(getTableModel(), export.getImageName()));
        item.setAlt(export.getText());
        item.setStyle("border:0");
        
        String action = new TDTableActions(getTableModel()).getExportAction(export.getView(), export.getFileName());
        item.setAction(action);
        item.enabled(getHtmlBuilder(), getTableModel());

    }
    
    
    public void rowsDisplayedDroplist() {
        int rowsDisplayed = getTableModel().getTableHandler().getTable().getRowsDisplayed();
        int medianRowsDisplayed = getTableModel().getTableHandler().getTable().getMedianRowsDisplayed();
        int maxRowsDisplayed = getTableModel().getTableHandler().getTable().getMaxRowsDisplayed();
        int currentRowsDisplayed = getTableModel().getLimit().getCurrentRowsDisplayed();

        int allRowsDisplayed = getTableModel().getLimit().getTotalRows();
        
        getHtmlBuilder().select().name(getTableModel().getTableHandler().prefixWithTableId() + TableConstants.ROWS_DISPLAYED);

        
        String formId=getTableModel().getTableHandler().getTable().getTableId();
        String formAction=getTableModel().getTableHandler().getTable().getAction();
        String formMethod=getTableModel().getTableHandler().getTable().getMethod();
        String pageNoId=getTableModel().getTableHandler().prefixWithTableId()+TableConstants.PAGE;
        
        StringBuffer onchange = new StringBuffer();
        
        onchange.append("EccnUtil.changeRowsDisplayed(");
        
        onchange.append("'"+formId+"','"+formAction+"','"+formMethod+"',");
        onchange.append("'"+pageNoId+"','"+TableConstants.EXPORT_TABLE_ID+"'");
        onchange.append(",this);");
        
        
//        +getTableModel().getTableHandler().getTable().getTableId()+"',this)");
        getHtmlBuilder().onchange(onchange.toString());

        getHtmlBuilder().close();

        getHtmlBuilder().newline();
        getHtmlBuilder().tabs(4);

        // default rows
        if (rowsDisplayed>allRowsDisplayed){
        	rowsDisplayed=allRowsDisplayed;
        }
        getHtmlBuilder().option().value(String.valueOf(rowsDisplayed));
        if (currentRowsDisplayed == rowsDisplayed) {
        	getHtmlBuilder().selected();
        }
        getHtmlBuilder().close();
        getHtmlBuilder().append(String.valueOf(rowsDisplayed));
        getHtmlBuilder().optionEnd();

        // median rows
        getHtmlBuilder().option().value(String.valueOf(medianRowsDisplayed));
        if (currentRowsDisplayed == medianRowsDisplayed) {
        	getHtmlBuilder().selected();
        }
        getHtmlBuilder().close();
        getHtmlBuilder().append(String.valueOf(medianRowsDisplayed));
        getHtmlBuilder().optionEnd();

        // max rows
        getHtmlBuilder().option().value(String.valueOf(maxRowsDisplayed));
        if (currentRowsDisplayed == maxRowsDisplayed) {
        	getHtmlBuilder().selected();
        }
        getHtmlBuilder().close();
        getHtmlBuilder().append(String.valueOf(maxRowsDisplayed));
        getHtmlBuilder().optionEnd();
        
        
        // all rows
        getHtmlBuilder().option().value(String.valueOf(allRowsDisplayed));
        if (currentRowsDisplayed == allRowsDisplayed) {
        	getHtmlBuilder().selected();
        }
        getHtmlBuilder().close();
        getHtmlBuilder().append("全部");
        getHtmlBuilder().optionEnd();

        
        getHtmlBuilder().newline();
        getHtmlBuilder().tabs(4);
        getHtmlBuilder().selectEnd();
    }

//    public void gotoPagesDroplist() {
//        int currentRowsDisplayed = getTableModel().getLimit().getCurrentRowsDisplayed();
//        int currentPage = getTableModel().getLimit().getPage();
//		int totalPages = 0;
//		int totalRows = getTableModel().getLimit().getTotalRows();
//		if (currentRowsDisplayed > 0) {
//			totalPages =(int)Math.ceil((double)totalRows / currentRowsDisplayed);
//		} else {
//			totalPages = 1;
//			currentPage = 1;
//		}
//		
//        getHtmlBuilder().span();
//        getHtmlBuilder().styleClass("");
//        getHtmlBuilder().close();
//        
//        getHtmlBuilder().select().name(getTableModel().getTableHandler().prefixWithTableId() + TDTableConstants.PAGES);
//
//        StringBuffer onchange = new StringBuffer();
//        onchange.append(new EccnTableActions(getTableModel()).getGotoPagesAction());
//        getHtmlBuilder().onchange(onchange.toString());
//        getHtmlBuilder().close();
//
//        getHtmlBuilder().newline();
//        getHtmlBuilder().tabs(4);
//
//        // all pages
//        int count = 1;
//		if (totalPages > 1) {
//			for (; count <= totalPages; ++count) {
//				getHtmlBuilder().option().value(String.valueOf(count));
//				if (count == currentPage) {
//					getHtmlBuilder().selected();
//				}
//				getHtmlBuilder().close();
//				getHtmlBuilder().append(String.valueOf(count));
//				getHtmlBuilder().optionEnd();
//			}
//		}
//
//        getHtmlBuilder().newline();
//        getHtmlBuilder().tabs(4);
//        getHtmlBuilder().selectEnd();
//        getHtmlBuilder().append("/").append(totalPages).append(TDBuilderConstants.TOOLBAR_GOTO_PAGE_LABEL);
//
//        getHtmlBuilder().spanEnd();
//    }

}
