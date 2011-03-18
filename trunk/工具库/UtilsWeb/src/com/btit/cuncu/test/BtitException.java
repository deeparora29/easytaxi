/**
 * <p>Copyright ? 2008 BTIT International Group Co. Ltd. </p>
 * <p>Project            :JZDW</p>
 * <p>JDK version used  :jdk1.5.0</p>
 * <p>Comments         :STS根异常;</p>
 * <p>Version          :2.0</p>
 * <p>Modification history:2008.10.23</p>
 * <p>1.2008.10.23 Yuxd new</p>
 **/
package com.btit.cuncu.test;

/**
 * <p>Title: STS根异常;</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright ? 2008</p>
 * <p>Company: BTIT</p>
 * @author :Yuxd
 * @version 2.0
 */
public class BtitException extends RuntimeException {
	/**
	 * <p>异常信息</p>
	 */
	protected String message;


	/**
	 * <p>缺省构造器</p>
	 */
	public BtitException(){
		
	}
	/**
	 * <p>带异常信息参数构造器</p>
	 * @param message
	 */
	public BtitException(String message){
		this.message = message;
	}


	/**
	 * <p>得到异常信息</p>
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * <p>设置异常信息</p>
	 * @param message
	 */
	public void setMessage(String message) {
		this.message = message;
	}
}
