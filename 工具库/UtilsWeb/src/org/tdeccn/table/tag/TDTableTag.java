package org.tdeccn.table.tag;

import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;

import org.extremecomponents.table.bean.Table;
import org.extremecomponents.table.core.TableModel;
import org.extremecomponents.table.tag.TableTag;
import org.extremecomponents.table.tag.TagUtils;
import org.extremecomponents.util.ExceptionUtils;
import org.tdeccn.table.bean.TDBeanDefaults;
import org.tdeccn.table.core.TDTableConstants;

public class TDTableTag extends TableTag implements TDTag{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6174585537513197036L;

	private String paginationLocation;

	private String showRowsDisplayed;

	private String showGotoPage;

	private String useCustomCol;

	private String xlsFileName;

	private String cvsFileName;

	private String pdfFileName;

	private Iterator iterator;

	private ArrayList attributs = new ArrayList();

	public String getAttributesAsString() {

		StringBuffer attributsRS = new StringBuffer(" ");
		Iterator it = attributs.iterator();
		while (it.hasNext()) {
			attributsRS.append(it.next()).append(" ");
		}
		return attributsRS.toString();
	}

	public void addAttribute(String value) {
		attributs.add(value);
	}

	public void resetAttribute() {
		attributs = new ArrayList();
	}

	public int doEndTag() throws JspException {
		model.getTableHandler().getTable().addAttribute(
				TDTableConstants.OTHER_ATTRIBUTES, getAttributesAsString());
		try {
			pageContext.getOut().println(model.getViewData());
		} catch (Exception e) {
			throw new JspException("TableTag.doEndTag() Problem: "
					+ ExceptionUtils.formatStackTrace(e));
		}

		return EVAL_PAGE;
	}

	public String getPaginationLocation() {
		return paginationLocation;
	}

	public void setPaginationLocation(String paginationLocation) {
		this.paginationLocation = paginationLocation;
	}

	public String getShowGotoPage() {
		return showGotoPage;
	}

	public void setShowGotoPage(String showGotoPage) {
		this.showGotoPage = showGotoPage;
	}

	public String getShowRowsDisplayed() {
		return showRowsDisplayed;
	}

	public void setShowRowsDisplayed(String showRowsDisplayed) {
		this.showRowsDisplayed = showRowsDisplayed;
	}

	public int doStartTag() throws JspException {
		try {
			iterator = null;
			return super.doStartTag();
		} catch (Exception e) {
			throw new JspException("TableTag.doStartTag() Problem: "
					+ ExceptionUtils.formatStackTrace(e));
		}
	}

	public int doAfterBody() throws JspException {

		Table table = model.getTableHandler().getTable();

		try {

			if (iterator == null) {
				int defaultRowsDisplayed = table.getRowsDisplayed();
				if (defaultRowsDisplayed < 0) {
					Integer totalRows = model.getTableHandler().getTotalRows();
					int allrows = 0;
					if (totalRows == null) {
						allrows = Integer.MAX_VALUE;
					} else {
						allrows = totalRows.intValue();
					}
					table.setRowsDisplayed(allrows);
				}
				iterator = model.execute().iterator();
			}

			if (iterator != null && iterator.hasNext()) {
				Object bean = iterator.next();
				model.setCurrentRowBean(bean);
				return EVAL_BODY_AGAIN;
			}
		} catch (Exception e) {
			throw new JspException("TableTag.doAfterBody() Problem: "
					+ ExceptionUtils.formatStackTrace(e));
		}

		return SKIP_BODY;
	}

	public void addTableAttributes(TableModel model, Table table) {

		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
		
		if (table.getAction() == null || "".equals(table.getAction())) {

			table.setAction(request.getRequestURL().toString());
		}

		table.addAttribute(TDTableConstants.XLS_FILE, TagUtils
				.evaluateExpressionAsString(TDTableConstants.XLS_FILE,
						this.xlsFileName, this, pageContext));

		table.addAttribute(TDTableConstants.CVS_FILE, TagUtils
				.evaluateExpressionAsString(TDTableConstants.CVS_FILE,
						this.cvsFileName, this, pageContext));

		table.addAttribute(TDTableConstants.PDF_FILE, TagUtils
				.evaluateExpressionAsString(TDTableConstants.PDF_FILE,
						this.pdfFileName, this, pageContext));

		String stringTemp = null;
		Boolean booleanTemp = null;

		stringTemp = TagUtils.evaluateExpressionAsString(
				TDTableConstants.PAGINATION_LOCATION, this.paginationLocation,
				this, pageContext);
		table.addAttribute(TDTableConstants.PAGINATION_LOCATION, TDBeanDefaults
				.setPaginationLocation(model, stringTemp));

		booleanTemp = TagUtils.evaluateExpressionAsBoolean(
				TDTableConstants.USE_GOTO_PAGE, this.useCustomCol, this,
				pageContext);
		table.addAttribute(TDTableConstants.USE_GOTO_PAGE, booleanTemp);

		booleanTemp = TagUtils.evaluateExpressionAsBoolean(
				TDTableConstants.SHOW_GOTO_PAGE, this.showGotoPage, this,
				pageContext);
		table.addAttribute(TDTableConstants.SHOW_GOTO_PAGE, TDBeanDefaults
				.setShowGotoPage(model, booleanTemp));

		booleanTemp = TagUtils.evaluateExpressionAsBoolean(
				TDTableConstants.SHOW_ROWS_DISPLAYED, this.showRowsDisplayed,
				this, pageContext);
		table.addAttribute(TDTableConstants.SHOW_ROWS_DISPLAYED, TDBeanDefaults
				.setShowRowsDisplayed(model, booleanTemp));
	}

	public void release() {
		paginationLocation = null;
		showGotoPage = null;
		showRowsDisplayed = null;
		iterator = null;
		attributs = new ArrayList();

		super.release();
	}

	public String getCvsFileName() {
		return cvsFileName;
	}

	public void setCvsFileName(String cvsFileName) {
		this.cvsFileName = cvsFileName;
	}

	public String getPdfFileName() {
		return pdfFileName;
	}

	public void setPdfFileName(String pdfFileName) {
		this.pdfFileName = pdfFileName;
	}

	public String getXlsFileName() {
		return xlsFileName;
	}

	public void setXlsFileName(String xlsFileName) {
		this.xlsFileName = xlsFileName;
	}

	public String getUseCustomCol() {
		return useCustomCol;
	}

	public void setUseCustomCol(String useCustomCol) {
		this.useCustomCol = useCustomCol;
	}



}
