package org.tdeccn.table.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.extremecomponents.util.ExceptionUtils;


public class TDAttributeTag extends BodyTagSupport  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8787546459578181313L;



	protected Object getBodyValue() throws JspException {
        Object result = getBodyContent().getString();

        return result;
    }


    public int doAfterBody() throws JspException {
        try {
            TDTag tag = (TDTag)this.getParent();
            tag.addAttribute(" "+(String)getBodyValue());
//	        if (getBodyContent()!=null){
//	        	getBodyContent().clear();
//	        }
        } catch (Exception e) {
            throw new JspException("TDAttributeTag.doAfterBody() Problem: " + ExceptionUtils.formatStackTrace(e));
        }

        return SKIP_BODY;
    }



    public void release() {

        super.release();
    }

}