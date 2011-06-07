package com.mobilesoft.smarttaxi.common.exception;

/**
 * session过期
 * @author renmian
 *
 */
public class OutOfSessionException extends BaseException {
	
	public OutOfSessionException()
	{
		super();
	}

	public OutOfSessionException(String message)
	{
		super(message);
	}

	public OutOfSessionException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public OutOfSessionException(Throwable cause)
	{
		super(cause);
	}
}
