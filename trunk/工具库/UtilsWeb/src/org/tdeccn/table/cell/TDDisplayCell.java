package org.tdeccn.table.cell;

import org.extremecomponents.table.bean.Column;
import org.extremecomponents.table.cell.AbstractCell;
import org.extremecomponents.table.core.TableModel;
import org.tdeccn.table.view.html.TDColumnBuilder;


public class TDDisplayCell extends AbstractCell {
	
    public String getHtmlDisplay(TableModel model, Column column) {
    	TDColumnBuilder columnBuilder = new TDColumnBuilder(column);
        columnBuilder.tdStart();
        columnBuilder.tdBody(getCellValue(model, column));
        columnBuilder.tdEnd();
        return columnBuilder.toString();
    }
    
    public String getExportDisplay(TableModel model, Column column) {
        return column.getPropertyValueAsString();
    }

    protected String getCellValue(TableModel model, Column column) {
        return column.getValueAsString();
    }
}
