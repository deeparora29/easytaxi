package org.tdeccn.table.cell;

import org.apache.commons.lang.StringUtils;
import org.extremecomponents.table.bean.Column;
import org.extremecomponents.table.cell.Cell;
import org.extremecomponents.table.core.TableModel;
import org.extremecomponents.util.HtmlBuilder;


public class TDRadioBoxHeaderCell implements Cell {
	
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

        
        // String value = column.getValueAsString();
    	String value="";
        String radioBoxName = column.getAlias();

        
        //if (StringUtils.isNotBlank(value)) {
	        html.input("radio").name(radioBoxName).value(value);
	        html.title("全消");
        	if (column.getStyleClass()!=null){
        		html.styleClass(column.getStyleClass());
        	}else{
        		html.styleClass("radio");
        	}
        	html.xclose();       	
        //}


        html.tdEnd();

        return html.toString();
        



    }
}
