package com.mobilesoft.smarttaxi.common.exception;


/**
 * service的exception
 * @author renmian
 *
 */
public class ServiceException extends BaseException
{

	public ServiceException()
	{
		super();
	}

	public ServiceException(String message)
	{
		super(message);
	}

	public ServiceException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public ServiceException(Throwable cause)
	{
		super(cause);
	}
}
