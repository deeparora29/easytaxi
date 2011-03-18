package org.tdeccn.table.tag;


import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.jsp.JspException;

import org.extremecomponents.table.bean.Row;
import org.extremecomponents.table.core.TableModel;
import org.extremecomponents.table.tag.RowTag;
import org.extremecomponents.table.tag.TagUtils;
import org.tdeccn.table.core.TDTableConstants;


public class TDRowTag extends RowTag implements TDTag {


    /**
	 * 
	 */
	private static final long serialVersionUID = -712514566941683807L;
	private String ondblclick;
	private Row row;

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
	
    public int doAfterBody() throws JspException {
    	row.addAttribute(TDTableConstants.OTHER_ATTRIBUTES,getAttributesAsString());
    	return SKIP_BODY;
    }

    
    
    public void addRowAttributes(TableModel model, Row row) {
    	this.row=row;
   	
    	row.addAttribute(TDTableConstants.ON_DOUBLE_CLICK, TagUtils
				.evaluateExpressionAsString(TDTableConstants.ON_DOUBLE_CLICK,
						this.ondblclick, this, pageContext));
    }

    public void modifyRowAttributes(TableModel model, Row row) {
    	this.row=row;
       	
    	row.addAttribute(TDTableConstants.ON_DOUBLE_CLICK, TagUtils
				.evaluateExpressionAsString(TDTableConstants.ON_DOUBLE_CLICK,
						this.ondblclick, this, pageContext));
    }

    public void release() {
    	ondblclick = null;
    	attributs=new ArrayList();
        super.release();
    }

	public String getOndblclick() {
		return ondblclick;
	}

	public void setOndblclick(String ondblclick) {
		this.ondblclick = ondblclick;
	}
}
