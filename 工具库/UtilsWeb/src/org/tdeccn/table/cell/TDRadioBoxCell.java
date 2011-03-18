package org.tdeccn.table.cell;

import org.extremecomponents.table.bean.Column;
import org.extremecomponents.table.cell.AbstractCell;
import org.extremecomponents.table.core.TableModel;
import org.extremecomponents.util.HtmlBuilder;
import org.tdeccn.table.view.html.TDColumnBuilder;


public class TDRadioBoxCell extends AbstractCell {
	
    public String getHtmlDisplay(TableModel model, Column column) {
    	TDColumnBuilder columnBuilder = new TDColumnBuilder(column);
        columnBuilder.tdStart();
        columnBuilder.tdBody(getCellValue(model, column));
        columnBuilder.tdEnd();
        return columnBuilder.toString();
    }
    
    public String getExportDisplay(TableModel model, Column column) {
        return "";
    }
    protected String getCellValue(TableModel model, Column column) {
 
        String value = column.getValueAsString();
        String radioBoxName = column.getAlias();
        HtmlBuilder build = new HtmlBuilder();
        
        //if (StringUtils.isNotBlank(value)) {
        	build.input("radio").name(radioBoxName).value(value);
        	if (column.getStyleClass()!=null){
        		build.styleClass(column.getStyleClass());
        	}else{
        		build.styleClass("radio");
        	}
        	build.xclose();       	
        //}

        return build.toString();
    }
}
