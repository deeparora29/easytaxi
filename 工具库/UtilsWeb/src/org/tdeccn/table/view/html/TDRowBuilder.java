package org.tdeccn.table.view.html;

import org.extremecomponents.table.bean.Row;
import org.extremecomponents.table.core.TableModel;
import org.extremecomponents.table.view.html.RowBuilder;
import org.extremecomponents.util.HtmlBuilder;
import org.tdeccn.table.core.TDTableConstants;


/**
 * @author Jeff Johnston
 */
public class TDRowBuilder extends RowBuilder {
    private HtmlBuilder html;
    private Row row;
    
    public TDRowBuilder(TableModel model) {
        this(new HtmlBuilder(), model);
    }

    public TDRowBuilder(HtmlBuilder html, TableModel model) {
    	super(html,model);
    	this.html=html;
    	this.row = model.getRowHandler().getRow();
    }


    public void rowStart() {
        html.tr(1);
        styleClass();
        style();
        onclick();
        ondblclick();
        onmouseover();
        onmouseout();
        
        html.append(row.getAttribute(TDTableConstants.OTHER_ATTRIBUTES));
        
        html.close();
    }

    public void ondblclick() {
        String ondblclick = row.getAttributeAsString(TDTableConstants.ON_DOUBLE_CLICK);
        if (ondblclick==null || ondblclick.length()<1) return;
        html.append(" "+TDTableConstants.ON_DOUBLE_CLICK+"=\""+ondblclick+"\" ");
    }
}
