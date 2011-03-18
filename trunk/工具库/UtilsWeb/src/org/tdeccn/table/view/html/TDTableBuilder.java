package org.tdeccn.table.view.html;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.extremecomponents.table.bean.Column;
import org.extremecomponents.table.bean.Table;
import org.extremecomponents.table.core.TableModel;
import org.extremecomponents.table.view.html.BuilderConstants;
import org.extremecomponents.table.view.html.BuilderUtils;
import org.extremecomponents.util.HtmlBuilder;
import org.tdeccn.table.core.TDTableConstants;


public class TDTableBuilder {
    private HtmlBuilder html;
    private TableModel model;
    private Table table;

    public TDTableBuilder(TableModel model) {
        this(new HtmlBuilder(), model);
    }
    
    public TDTableBuilder(HtmlBuilder html, TableModel model) {
        this.html = html;
        this.model = model;
        this.table = model.getTableHandler().getTable();
    }

    public HtmlBuilder getHtmlBuilder() {
        return html;
    }

    protected TableModel getTableModel() {
        return model;
    }

    protected Table getTable() {
        return table;
    }

    public void tableStart() {
    	
        html.table(0);
        html.cellSpacing("0");
        html.cellPadding("0");
        html.styleClass("tableBox");
        width();
        
        html.append(table.getAttribute(TDTableConstants.OTHER_ATTRIBUTES));

        
        html.close();
        html.tr(1).close();
        html.td(1).width("100%").close();
        
    	
        html.table(0);
        id();
        //border();
        //cellSpacing();
        html.border("0");
        html.cellSpacing("1");
        cellPadding();
        styleClass();
        style();
        width();
        html.close();
    }

    public void tableEnd() {
        html.tableEnd(0);
        
        html.tdEnd();
        html.trEnd(0);
        html.tableEnd(0);
        
        int currentRowsDisplayed = getTableModel().getLimit().getCurrentRowsDisplayed();
 		int totalPages = 0;
		int totalRows = getTableModel().getLimit().getTotalRows();
		if (currentRowsDisplayed > 0) {
			totalPages =(int)Math.ceil((double)totalRows / currentRowsDisplayed);
		} else {
			totalPages = 1;
		}
        getHtmlBuilder().input("hidden").name(getPrefixWithTableId() +TDTableConstants.HIDDEN_TOTAL_PAGES).value(""+totalPages).xclose();
        getHtmlBuilder().input("hidden").name(getPrefixWithTableId() +TDTableConstants.HIDDEN_TOTAL_ROWS).value(""+totalRows).xclose();
    }
    public String getPrefixWithTableId(){
    	return getTableModel().getTableHandler().prefixWithTableId();
    }
    public void id() {
        html.id(model.getTableHandler().prefixWithTableId() + BuilderConstants.TABLE);
    }

    public void border() {
        String border = table.getBorder();
        html.border(border);
    }

    public void cellSpacing() {
        String cellSpacing = table.getCellspacing();
        html.cellSpacing(cellSpacing);
    }

    public void cellPadding() {
        String cellPadding = table.getCellpadding();
        html.cellPadding(cellPadding);
    }

    public void styleClass() {
        String styleClass = table.getStyleClass();
        html.styleClass(styleClass);
    }

    public void style() {
        String style = table.getStyle();
        html.style(style);
    }

    public void width() {
        String width = table.getWidth();
        html.width(width);
    }

    public void title() {
        boolean showTitle = BuilderUtils.showTitle(model);
        if (showTitle) {
            String title = model.getTableHandler().getTable().getTitle();
            if (StringUtils.isNotBlank(title)) {
                html.span().width(model.getTableHandler().getTable().getWidth()).styleClass(BuilderConstants.TITLE_CSS).close().append(title).spanEnd();
            }
        }
    }

    public void titleRowSpanColumns() {
        boolean showTitle = BuilderUtils.showTitle(model);
        if (showTitle) {
            String title = model.getTableHandler().getTable().getTitle();
            if (StringUtils.isNotBlank(title)) {
                int columnCount = model.getColumnHandler().columnCount();
                html.tr(1).styleClass(BuilderConstants.TITLE_ROW_CSS).close();
                html.td(2).colSpan("" + columnCount).close();
                html.span().close().append(title).spanEnd();
                html.tdEnd();
                html.trEnd(1);
            }
        }
    }

    public void headerRow() {
        html.tr(1).close();

        List columns = model.getColumnHandler().getHeaderColumns();
        for (Iterator iter = columns.iterator(); iter.hasNext();) {
            Column column = (Column) iter.next();
            html.append(column.getCellDisplay());
        }

        html.trEnd(1);
    }

    public void filterRow() {
        if (!model.getTableHandler().getTable().isFilterable()) {
            return;
        }

        html.tr(1).styleClass(BuilderConstants.FILTER_CSS).close();

        List columns = model.getColumnHandler().getFilterColumns();
        for (Iterator iter = columns.iterator(); iter.hasNext();) {
            Column column = (Column) iter.next();
            html.append(column.getCellDisplay());
        }

        html.trEnd(1);
    }

    public void theadStart() {
        html.thead(1).close();
    }

    public void theadEnd() {
        html.theadEnd(1);
    }

    public void tbodyStart() {
        //html.tbody(1).styleClass(BuilderConstants.TABLE_BODY_CSS).close();
        html.tbody(1).close();
    }

    public void tbodyEnd() {
        html.tbodyEnd(1);
    }

    public void themeStart() {
        html.newline();
        String theme = model.getTableHandler().getTable().getTheme();
        html.div().styleClass(theme);
        html.close();
    }

    public void themeEnd() {
        html.newline();
        html.divEnd();
    }
    
    public String toString() {
        return html.toString();
    }
}
