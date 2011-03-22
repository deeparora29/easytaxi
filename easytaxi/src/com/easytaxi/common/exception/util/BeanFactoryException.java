package com.easytaxi.common.exception.util;

import com.easytaxi.common.exception.ServiceException;

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
