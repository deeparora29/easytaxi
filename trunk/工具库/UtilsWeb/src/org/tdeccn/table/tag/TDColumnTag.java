package org.tdeccn.table.tag;


import java.util.ArrayList;
import java.util.Iterator;

import org.extremecomponents.table.bean.Column;
import org.extremecomponents.table.core.TableModel;
import org.extremecomponents.table.tag.ColumnTag;
import org.extremecomponents.table.tag.TagUtils;
import org.tdeccn.table.core.TDTableConstants;


public class TDColumnTag extends ColumnTag implements TDTag {


    /**
	 * 
	 */
	private static final long serialVersionUID = -712514566941683807L;
	
	private String onclick;
	private String ondblclick;
	private String headerspan;
	private String calcspan;
	
	private ArrayList attributs=new ArrayList();

	public String getAttributesAsString(){
		StringBuffer attributsRS=new StringBuffer();
		Iterator it=attributs.iterator();
		while (it.hasNext()){
			attributsRS.append(it.next()).append(" ");
		}
		
		return attributsRS.toString();
		
	}
	
	public void addAttribute(String value){
		attributs.add(value);
	}
	
	public void resetAttribute(){
		attributs=new ArrayList();
	}
	
    public void addColumnAttributes(TableModel model, Column column) {
    	
    	column.addAttribute(TDTableConstants.ON_CLICK, TagUtils
				.evaluateExpressionAsString(TDTableConstants.ON_CLICK,
						this.onclick, this, pageContext));
    	
    	column.addAttribute(TDTableConstants.ON_DOUBLE_CLICK, TagUtils
				.evaluateExpressionAsString(TDTableConstants.ON_DOUBLE_CLICK,
						this.ondblclick, this, pageContext));
    	
    	column.addAttribute(TDTableConstants.HEADER_SPAN, TagUtils
				.evaluateExpressionAsString(TDTableConstants.HEADER_SPAN,
						this.headerspan, this, pageContext));
    	
    	column.addAttribute(TDTableConstants.CALC_SPAN, TagUtils
				.evaluateExpressionAsString(TDTableConstants.CALC_SPAN,
						this.calcspan, this, pageContext));
    	column.addAttribute(TDTableConstants.OTHER_ATTRIBUTES,getAttributesAsString());
    	resetAttribute();
    }

    public void modifyColumnAttributes(TableModel model, Column column) {
    	column.addAttribute(TDTableConstants.ON_CLICK, TagUtils
				.evaluateExpressionAsString(TDTableConstants.ON_CLICK,
						this.onclick, this, pageContext));
    	
    	column.addAttribute(TDTableConstants.ON_DOUBLE_CLICK, TagUtils
				.evaluateExpressionAsString(TDTableConstants.ON_DOUBLE_CLICK,
						this.ondblclick, this, pageContext));
    	
    	column.addAttribute(TDTableConstants.HEADER_SPAN, TagUtils
				.evaluateExpressionAsString(TDTableConstants.HEADER_SPAN,
						this.headerspan, this, pageContext));
    	
    	column.addAttribute(TDTableConstants.CALC_SPAN, TagUtils
				.evaluateExpressionAsString(TDTableConstants.CALC_SPAN,
						this.calcspan, this, pageContext));
    	column.addAttribute(TDTableConstants.OTHER_ATTRIBUTES,getAttributesAsString());
    	resetAttribute();
    }

    public void release() {
    	onclick = null;
    	ondblclick = null;
    	headerspan=null;
    	calcspan=null;
    	attributs=new ArrayList();
        super.release();
    }

	public String getOndblclick() {
		return ondblclick;
	}

	public void setOndblclick(String ondblclick) {
		this.ondblclick = ondblclick;
	}

	public String getOnclick() {
		return onclick;
	}

	public void setOnclick(String onclick) {
		this.onclick = onclick;
	}

	public String getHeaderspan() {
		return headerspan;
	}

	public void setHeaderspan(String headerspan) {
		this.headerspan = headerspan;
	}

	public String getCalcspan() {
		return calcspan;
	}

	public void setCalcspan(String calcspan) {
		this.calcspan = calcspan;
	}

	public ArrayList getAttributs() {
		return attributs;
	}

	public void setAttributs(ArrayList attributs) {
		this.attributs = attributs;
	}
}
