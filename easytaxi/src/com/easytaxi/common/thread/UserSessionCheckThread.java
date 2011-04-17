package com.easytaxi.common.thread;

import java.util.List;
import java.util.concurrent.ConcurrentMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.easytaxi.bo.Passenger;
import com.easytaxi.bo.Taxi;
import com.easytaxi.common.utils.BeanFactoryUtil;
import com.easytaxi.location.service.PassengerDataService;
import com.easytaxi.location.service.TaxiDataService;
import com.easytaxi.usermgr.bo.LoginRecord;
import com.easytaxi.usermgr.dao.LoginRecordDao;

public class UserSessionCheckThread implements Runnable{
	private Log log = LogFactory.getLog(UserSessionCheckThread.class);
	@Override
	public void run() {
		try {
			while( true ){
				if(BeanFactoryUtil.getBean("loginRecordDao")==null){
					Thread.sleep( 60 * 1000 );
				}
				LoginRecordDao dao = (LoginRecordDao)BeanFactoryUtil.getBean("loginRecordDao");
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
		} catch (Exception e) {
			log.error("检查账号会话线程运行失败：", e);
		}
		
	}

}
