package com.mobilesoft.smarttaxi.common.thread;

import java.util.List;
import java.util.concurrent.ConcurrentMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.mobilesoft.smarttaxi.bo.Passenger;
import com.mobilesoft.smarttaxi.bo.Taxi;
import com.mobilesoft.smarttaxi.common.utils.BeanFactoryUtil;
import com.mobilesoft.smarttaxi.location.service.PassengerDataService;
import com.mobilesoft.smarttaxi.location.service.TaxiDataService;
import com.mobilesoft.smarttaxi.usermgr.bo.LoginRecord;
import com.mobilesoft.smarttaxi.usermgr.dao.LoginRecordDao;

public class UserSessionCheckThread implements Runnable{
	private Log log = LogFactory.getLog(UserSessionCheckThread.class);
	@Override
	public void run() {
		try {
			while( true ){
				LoginRecordDao dao = null;
				try {
					dao = (LoginRecordDao)BeanFactoryUtil.getBean("loginRecordDao");
				} catch (Exception e) {
					Thread.sleep( 60 * 1000 );
				}
				if(dao!=null){
					List<LoginRecord> accountList = dao.getModifiedInner24HoursData();
					if(accountList!=null&&accountList.size()>0){
						log.info("系统中会话存在超过24小时的账号数量为：" + accountList.size());
						for (LoginRecord account : accountList) {
							if(account!=null){
								PassengerDataService service = (PassengerDataService)BeanFactoryUtil.getBean("passengerDataService");
								ConcurrentMap<String, Passenger>  temp = service.getPassengerLoginInfoMap() ;
								TaxiDataService taxiService = (TaxiDataService)BeanFactoryUtil.getBean("taxiDataService");
								ConcurrentMap<String, Taxi> taxiLoginMap = taxiService.getTaxiLoginInfoMap();
								if(temp!=null&&temp.containsKey(account.getUserid())){
									temp.remove(account.getUserid());
								}else if(taxiLoginMap!=null&&taxiLoginMap.containsKey(account.getUserid())){
									taxiLoginMap.remove(account.getUserid());
								}
								log.info("系统检测到账号： " + account.getUserid() +"登录时间超过24小时，需要重新登录" );
								dao.deleteUserLoginLog(account.getUserid());
								log.info("删除用户: "+account.getUserid()+" 登录日志成功................");
							}
						}
					}else{
						Thread.sleep( 60 * 1000 );
					}
				}
			}
		} catch (Exception e) {
			log.error("检查账号会话线程运行失败：", e);
			try {
				Thread.sleep( 3 * 60 * 1000 );
			} catch (InterruptedException e1) {
			}
		}
		
	}

}
