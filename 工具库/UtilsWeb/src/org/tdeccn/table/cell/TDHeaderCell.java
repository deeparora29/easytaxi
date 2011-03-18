package org.tdeccn.table.cell;

import org.apache.commons.lang.StringUtils;
import org.extremecomponents.table.bean.Column;
import org.extremecomponents.table.cell.HeaderCell;
import org.extremecomponents.table.core.MessagesConstants;
import org.extremecomponents.table.core.TableConstants;
import org.extremecomponents.table.core.TableModel;
import org.extremecomponents.table.view.html.BuilderConstants;
import org.extremecomponents.table.view.html.TableActions;
import org.extremecomponents.util.HtmlBuilder;
import org.tdeccn.table.core.TDTableConstants;


public class TDHeaderCell extends HeaderCell {


    protected void buildHeaderHtml(HtmlBuilder html, TableModel model, Column column, String headerClass, String sortImage, String sortOrder) {
        
    	String span=column.getAttributeAsString(TDTableConstants.HEADER_SPAN);
    	String display="";
        if (span!=null){
        	span=span.trim();
        	if ("0".equals(span)) {
        		//display=";display:none;";
        		return;
        	}
        	span=" colspan=\""+span+"\" ";
        }else{
        	span="";
        }
        
    	html.td(2);

        if (StringUtils.isNotEmpty(headerClass)) {
            html.styleClass(headerClass);
        }

        if (StringUtils.isNotEmpty(column.getHeaderStyle())) {
            html.style(column.getHeaderStyle()+display);
        }

        if (StringUtils.isNotEmpty(column.getWidth())) {
            html.width(column.getWidth());
        }

        if (column.isSortable()) {
            if (sortOrder.equals(TableConstants.SORT_ASC)) {
                html.onmouseover("this.className='" + BuilderConstants.TABLE_HEADER_SORT_CSS + "';this.style.cursor='pointer'");
                if (StringUtils.isNotEmpty(headerClass)) {
                    html.onmouseout("this.className='" + headerClass + "';this.style.cursor='default'");
                } else {
                    html.onmouseout("this.className='" + BuilderConstants.TABLE_HEADER_CSS + "';this.style.cursor='default'");
                }
            }

            if (sortOrder.equals(TableConstants.SORT_DEFAULT) || sortOrder.equals(TableConstants.SORT_DESC)) {
                html.onmouseover("this.style.cursor='pointer'");
                html.onmouseout("this.style.cursor='default'");
            }

            html.onclick(new TableActions(model).getSortAction(column, sortOrder));

            boolean showTooltips = model.getTableHandler().getTable().isShowTooltips();
            if (showTooltips) {
                String headercellTooltip = model.getMessages().getMessage(MessagesConstants.COLUMN_HEADERCELL_TOOLTIP_SORT);
                html.title(headercellTooltip + " " + column.getTitle());
            }
        }
        
        
        

        
        html.append(span);
        
        html.close();

        html.append(column.getTitle());

        if (column.isSortable()) {
            if (StringUtils.isNotEmpty(sortImage)) {
                html.nbsp();
                html.img();
                html.src(sortImage);
                html.style("border:0");
                html.alt("Arrow");
                html.xclose();
            }
        }

        html.tdEnd();
    }
}
