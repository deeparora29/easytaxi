package org.tdeccn.table.view.html;

import org.extremecomponents.table.bean.Column;
import org.extremecomponents.table.view.html.ColumnBuilder;
import org.extremecomponents.util.HtmlBuilder;
import org.tdeccn.table.core.TDTableConstants;



public class TDColumnBuilder extends ColumnBuilder {
    private HtmlBuilder html;
    private Column column;
    
    public TDColumnBuilder(Column column) {
        this(new HtmlBuilder(), column);
    }

    public TDColumnBuilder(HtmlBuilder html, Column column) {
    	super(html,column);
    	this.html=html;
    	this.column = column;
    }


    public void tdStart() {
    	
        html.td(2);
        styleClass();
        style();
        width();
        onclick();
        ondblclick();
        
        html.append(column.getAttribute(TDTableConstants.OTHER_ATTRIBUTES));
        
        html.close();

    }

    public void onclick() {
        String onclick = column.getAttributeAsString(TDTableConstants.ON_CLICK);
        if (onclick==null || onclick.length()<1) return;
        html.append(" "+TDTableConstants.ON_CLICK+"=\""+onclick+"\" ");
    }
    
    public void ondblclick() {
        String ondblclick = column.getAttributeAsString(TDTableConstants.ON_DOUBLE_CLICK);
        if (ondblclick==null || ondblclick.length()<1) return;
        html.append(" "+TDTableConstants.ON_DOUBLE_CLICK+"=\""+ondblclick+"\" ");
    }
}
