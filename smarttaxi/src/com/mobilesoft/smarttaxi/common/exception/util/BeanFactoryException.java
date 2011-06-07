package com.mobilesoft.smarttaxi.common.exception.util;

import com.mobilesoft.smarttaxi.common.exception.ServiceException;

/**
 * spring beanfactory异常
 * @author renmian
 *
 */
public class BeanFactoryException extends ServiceException
{
	public BeanFactoryException(String message)
	{
		super(message);
	}

	public BeanFactoryException(String message, Throwable cause)
	{
		super(message, cause);
	}

}
