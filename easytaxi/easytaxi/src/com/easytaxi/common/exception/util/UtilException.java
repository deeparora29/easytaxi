/**
 * FileName: UtilException.java
 */
package com.easytaxi.common.exception.util;


/**
 * 
 * 用于工具类异常
 * @author renmian
 *
 */
public class UtilException extends RuntimeException
{
	public UtilException()
	{
		super();
	}
	
	public UtilException(String message)
	{
		super(message);
	}
	
	public UtilException(String message, Throwable cause)
	{
		super(message, cause);
	}
	
	public UtilException(Throwable cause)
	{
		super(cause);
	}
}
