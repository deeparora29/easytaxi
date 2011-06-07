package com.mobilesoft.smarttaxi.common.exception;


/**
 * service的exception
 * @author renmian
 *
 */
public class DaoException extends BaseException
{

	public DaoException()
	{
		super();
	}

	public DaoException(String message)
	{
		super(message);
	}

	public DaoException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public DaoException(Throwable cause)
	{
		super(cause);
	}
}
