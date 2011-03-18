package org.tdeccn.table.view;

import java.util.Iterator;

import org.extremecomponents.table.bean.Export;
import org.extremecomponents.table.core.TableModel;
import org.extremecomponents.table.view.html.BuilderConstants;
import org.extremecomponents.table.view.html.BuilderUtils;
import org.extremecomponents.table.view.html.StatusBarBuilder;
import org.extremecomponents.table.view.html.TwoColumnRowLayout;
import org.extremecomponents.util.HtmlBuilder;
import org.tdeccn.table.core.TDTableConstants;
import org.tdeccn.table.view.html.TDToolbarBuilder;


public class TDToolbar extends TwoColumnRowLayout {
    public TDToolbar(HtmlBuilder html, TableModel model) {
        super(html, model);
    }

    protected boolean showLayout(TableModel model) {
        boolean showStatusBar = BuilderUtils.showStatusBar(model);
        boolean filterable = BuilderUtils.filterable(model);
        boolean showExports = BuilderUtils.showExports(model);
        boolean showPagination = BuilderUtils.showPagination(model);
        boolean showTitle = BuilderUtils.showTitle(model);
        if (!showStatusBar && !filterable && !showExports && !showPagination && !showTitle) {
            return false;
        }

        return true;
    }

    public void separator(HtmlBuilder html,TableModel model) {
        html.img();
        html.src(BuilderUtils.getImage(model, BuilderConstants.TOOLBAR_SEPARATOR_IMAGE));
        html.styleClass(BuilderConstants.TOOLBAR_SEPARATOR_CSS);
        html.alt("Separator");
        html.xclose();
    }
    protected void columnRight(HtmlBuilder html, TableModel model) {
        boolean filterable = BuilderUtils.filterable(model);
        boolean showPagination = BuilderUtils.showPagination(model);
        boolean showExports = BuilderUtils.showExports(model);

        boolean showRowsDisplayed = Boolean.valueOf(""+model.getTableHandler().getTable().getAttribute(TDTableConstants.SHOW_ROWS_DISPLAYED)).booleanValue();
        boolean showGotoPage = Boolean.valueOf(""+model.getTableHandler().getTable().getAttribute(TDTableConstants.SHOW_GOTO_PAGE)).booleanValue();
        
//        String xlsFileName=(String)(model.getTableHandler().getTable().getAttribute(TDTableConstants.XLS_FILE));
        
        TDToolbarBuilder toolbarBuilder = new TDToolbarBuilder(html, model);

        html.td(4).styleClass(BuilderConstants.TOOLBAR_CSS).close();

        html.table(4).border("0").cellPadding("1").cellSpacing("1").close();
        html.tr(5).close();


        
        html.td(1).close();
        toolbarBuilder.extendTool();
        html.tdEnd();
        if (filterable) {
            if (showExports || showPagination) {
                html.td(5).close();
                html.append("&#160;");
                toolbarBuilder.separator();
                html.tdEnd();
            }

            html.td(5).close();
            html.append("&#160;");
            toolbarBuilder.filterItemAsImage();
            html.tdEnd();

            html.td(5).close();
            html.append("&#160;");
            toolbarBuilder.clearItemAsImage();
            html.tdEnd();
            
            
            
        }

        
//        boolean hasExportXLS=false;
        if (showExports) {
            Iterator iterator = model.getExportHandler().getExports().iterator();
            for (Iterator iter = iterator; iter.hasNext();) {
                
                Export export = (Export) iter.next();
//                if (export.getView().equalsIgnoreCase("xls")){
//                	hasExportXLS=true;
//                }
                html.td(5).close();
                html.append("&#160;");
                toolbarBuilder.exportItemAsImage(export);
                html.tdEnd();
            }
            
        }

        
        if (showPagination) {
            html.td(5).styleClass(BuilderConstants.STATUS_BAR_CSS).close();
            
			toolbarBuilder.firstPageItemAsButton();
			toolbarBuilder.prevPageItemAsButton();
			toolbarBuilder.nextPageItemAsButton();
			toolbarBuilder.lastPageItemAsButton();
          
            html.tdEnd();
            
            /*
            html.td(5).close();
           	separator(html, model);
            html.tdEnd();
			*/
            if (showGotoPage){
            toolbarBuilder.gotoPages();
            }
            
            html.tdEnd();
            
            if (showRowsDisplayed){
                html.td(5).styleClass(BuilderConstants.STATUS_BAR_CSS).close();
                html.append("&#160;");
                toolbarBuilder.rowsDisplayed();
                html.tdEnd();
            }

        }
        
//        if (!hasExportXLS && xlsFileName!=null && xlsFileName.length()>0){
//        	Export export=new Export(model);
//        	export.defaults();
//            if (StringUtils.isBlank(export.getView())) {
//                export.setView(TableConstants.VIEW_XLS);
//            }
//
//            if (StringUtils.isBlank(export.getViewResolver())){
//                export.setViewResolver(TableConstants.VIEW_XLS);
//            }
//
//            if (StringUtils.isBlank(export.getImageName())) {
//                export.setImageName(TableConstants.VIEW_XLS);
//            }
//            
//            if (StringUtils.isBlank(export.getText())) {
//                export.setText(BuilderConstants.TOOLBAR_XLS_TEXT);
//            }
//            export.setFileName(xlsFileName);
//            String interceptor = TableModelUtils.getInterceptPreference(model, export.getInterceptor(), PreferencesConstants.EXPORT_INTERCEPTOR);
//            export.setInterceptor(interceptor);
//            TableCache.getInstance().getExportInterceptor(interceptor).addExportAttributes(model, export);
//
//            html.td(5).close();
//            html.append("&#160;");
//            toolbarBuilder.exportItemAsImage(export);
//            html.tdEnd();
//        }
//        


        html.trEnd(5);

        html.tableEnd(4);

        html.tdEnd();
    }
    
    protected void columnLeft(HtmlBuilder html, TableModel model) {
        boolean showStatusBar = BuilderUtils.showStatusBar(model);
        if (!showStatusBar) {
            return;
        }

        html.td(4).styleClass(BuilderConstants.STATUS_BAR_CSS).close();

        new StatusBarBuilder(html, model).statusMessage();

        html.tdEnd();
    }
}
