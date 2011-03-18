package org.tdeccn.table.cell;

import org.apache.commons.lang.StringUtils;
import org.extremecomponents.table.bean.Column;
import org.extremecomponents.table.cell.Cell;
import org.extremecomponents.table.core.TableModel;
import org.extremecomponents.util.HtmlBuilder;


public class TDCheckBoxHeaderCell implements Cell {
    public String getExportDisplay(TableModel model, Column column) {
        //return column.getTitle();
    	return "";
    }

    public String getHtmlDisplay(TableModel model, Column column) {
        HtmlBuilder html = new HtmlBuilder();
        
        html.td(2);

        if (StringUtils.isNotEmpty(column.getHeaderClass())) {
            html.styleClass(column.getHeaderClass());
        }

        if (StringUtils.isNotEmpty(column.getHeaderStyle())) {
            html.style(column.getHeaderStyle());
        }

        if (StringUtils.isNotEmpty(column.getWidth())) {
            html.width(column.getWidth());
        }

        html.close();

        String selectableControlName = column.getAlias();
        String controlName = selectableControlName + "_selector";
        
        String tableId=model.getTableHandler().getTable().getTableId();
        
        html.input("checkbox");
        html.id(controlName);
        html.name(controlName);
        html.title("全选/全消");
    	if (column.getStyleClass()!=null){
    		html.styleClass(column.getStyleClass());
    	}else{
    		html.styleClass("checkbox");
    	}
    	
        html.onclick("EccnUtil.checkAll('"+tableId+"','"+selectableControlName+"','"+controlName+"')");
        html.close();

        html.tdEnd();

        return html.toString();
    }
}
