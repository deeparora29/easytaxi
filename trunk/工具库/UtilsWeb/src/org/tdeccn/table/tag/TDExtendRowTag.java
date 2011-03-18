package org.tdeccn.table.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.extremecomponents.table.core.TableModel;
import org.extremecomponents.table.tag.TagUtils;
import org.extremecomponents.util.ExceptionUtils;


public class TDExtendRowTag extends BodyTagSupport   {

    /**
	 * 
	 */
	private static final long serialVersionUID = 3767467674048711091L;



	protected Object getBodyValue() throws JspException {
        Object result = getBodyContent().getString();

        return result;
    }


    public int doAfterBody() throws JspException {
        try {
            TableModel model = TagUtils.getModel(this);

            model.getTableHandler().getTable().addAttribute("ExtendRow", getBodyValue());

        } catch (Exception e) {
            throw new JspException("TDExtendTag.doAfterBody() Problem: " + ExceptionUtils.formatStackTrace(e));
        }

        return SKIP_BODY;
    }



    public void release() {

        super.release();
    }

}