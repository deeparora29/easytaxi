package org.tdeccn.table.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.extremecomponents.table.context.Context;
import org.extremecomponents.table.context.HttpServletRequestContext;
import org.extremecomponents.table.core.Preferences;
import org.extremecomponents.table.core.TableConstants;
import org.extremecomponents.table.core.TableModelUtils;
import org.extremecomponents.table.core.TableProperties;
import org.extremecomponents.table.filter.ExportFilterUtils;
import org.extremecomponents.table.filter.ExportResponseWrapper;
import org.extremecomponents.table.filter.ViewResolver;
import org.extremecomponents.util.MimeUtils;
import org.tdeccn.table.core.TDTableConstants;


public class TDExportFilter implements Filter {
	
	
	protected FilterConfig filterConfig = null;
	
	protected String encoding = "UTF-8";
	protected boolean ignore = true;
	
	
	public void destroy() {

		this.encoding = null;
		this.filterConfig = null;

	}
	
    private boolean responseHeadersSetBeforeDoFilter;
	private static Log logger = LogFactory.getLog(TDExportFilter.class);

    public void init(FilterConfig filterConfig) throws ServletException {
    	
    	initEncoding(filterConfig);
    	
        String responseHeadersSetBeforeDoFilter = filterConfig.getInitParameter("responseHeadersSetBeforeDoFilter");
        if (StringUtils.isNotBlank(responseHeadersSetBeforeDoFilter)) {
            this.responseHeadersSetBeforeDoFilter = new Boolean(responseHeadersSetBeforeDoFilter).booleanValue();
        }
    }
    
	public void initEncoding(FilterConfig filterConfig) throws ServletException {

		this.filterConfig = filterConfig;
		String encodingValue = filterConfig.getInitParameter("encoding");
		String value = filterConfig.getInitParameter("ignore");

			
		if (encodingValue!=null && encodingValue.length()>0){
			this.encoding=encodingValue; 
		}
		if (value == null)
			this.ignore = true;
		else if (value.equalsIgnoreCase("true"))
			this.ignore = true;
		else if (value.equalsIgnoreCase("yes"))
			this.ignore = true;
		else
			this.ignore = false;

	}
	public void doEncoding(ServletRequest request, ServletResponse response) throws IOException, ServletException {

		if (ignore || (request.getCharacterEncoding() == null)) {
			String encoding = selectEncoding(request);
			if (encoding != null)
				//Set the same character encoding for the request and the response  
				request.setCharacterEncoding(encoding);
				//response.setCharacterEncoding(encoding);
		}
	}
	
	protected String selectEncoding(ServletRequest request) {
		return (this.encoding);
	}
  
    private boolean isUseAJAXPrep(ServletRequest servletRequest) {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        return Boolean.TRUE.toString().equalsIgnoreCase(request.getHeader(TDTableConstants.USE_AJAX_PREP));
	}
    
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        
        
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
 
        if (isUseAJAXPrep(request)) {
			// TDAjaxPrepResponseWrapper ajaxPrepResponseWrapper = new
			// TDAjaxPrepResponseWrapper(response);
			request.setCharacterEncoding("UTF-8");
		} else {
			doEncoding(servletRequest, servletResponse);
		}
		Context context = new HttpServletRequestContext((HttpServletRequest) servletRequest);
		boolean isExported = ExportFilterUtils.isExported(context);

		if (isExported) {
			String exportFileName = ExportFilterUtils.getExportFileName(context);
			doFilterInternal(request, response, chain, exportFileName);
			handleExport((HttpServletRequest) request, (HttpServletResponse) response, context);
			return;
		}
        	

        chain.doFilter(request, response);

    }
    
  
    


    protected void doFilterInternal(ServletRequest request, ServletResponse response, FilterChain chain, String exportFileName) throws IOException, ServletException {
        if (responseHeadersSetBeforeDoFilter) {
            setResponseHeaders((HttpServletResponse) response, exportFileName);
        }
        chain.doFilter(request, new ExportResponseWrapper((HttpServletResponse) response));
        if (!responseHeadersSetBeforeDoFilter) {
            setResponseHeaders((HttpServletResponse) response, exportFileName);
        }
    }
    
    protected void handleExport(HttpServletRequest request, HttpServletResponse response, Context context) {
        try {
            Object viewData = request.getAttribute(TableConstants.VIEW_DATA);
            if (viewData != null) {
                Preferences preferences = new TableProperties();
                preferences.init(null, TableModelUtils.getPreferencesLocation(context));
                String viewResolver = (String) request.getAttribute(TableConstants.VIEW_RESOLVER);
                Class classDefinition = Class.forName(viewResolver);
                ViewResolver handleExportViewResolver = (ViewResolver) classDefinition.newInstance();
                handleExportViewResolver.resolveView(request, response, preferences, viewData);
                response.getOutputStream().flush();
                response.getOutputStream().close();
            }

        } catch (Exception e) {
            //e.printStackTrace();
        }
    }
    

    protected void setResponseHeaders(HttpServletResponse response, String exportFileName) {
        String mimeType = MimeUtils.getFileMimeType(exportFileName);
        if (StringUtils.isNotBlank(mimeType)) {
            response.setContentType(mimeType);
        }
        
        //Encode the export file name by the character encoding of response.
//        String encoding = response.getCharacterEncoding();
        if (encoding != null){
	        try {
	        	//exportFileName = URLEncoder.encode(exportFileName,response.getCharacterEncoding());
	        	exportFileName = URLEncoder.encode(exportFileName,"UTF-8");
	        } catch (UnsupportedEncodingException e) {
				String errorMessage = "Unsupported response.getCharacterEncoding() [" + encoding + "].";
	            logger.error("TDExportFilter.setResponseHeaders() - " + errorMessage);
			}
        }
        response.setHeader("Content-Disposition", "attachment;filename=\"" + exportFileName + "\"");
        response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
        response.setHeader("Pragma", "public");
        response.setDateHeader("Expires", (System.currentTimeMillis() + 1000));
    }
    
    
}
