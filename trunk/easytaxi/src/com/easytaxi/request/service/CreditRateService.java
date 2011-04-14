package com.easytaxi.request.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.easytaxi.bo.CreditRecord;
import com.easytaxi.common.SystemPara;
import com.easytaxi.common.service.BaseService;
import com.easytaxi.request.bo.RequestInfo;
import com.easytaxi.request.dao.CallTaxiDao;
import com.easytaxi.request.dao.CreditRecordDao;
import com.easytaxi.usermgr.dao.PassengerDao;
import com.easytaxi.usermgr.dao.TaxiDao;

/**
 * 用于信用评价的接口
 * 
 * @author renmian
 */
public class CreditRateService extends BaseService {

    private CreditRecordDao creditRecordDao;

    private CallTaxiDao callTaxiDao;
    private TaxiDao taxiDao;
    private PassengerDao passengerDao;

    public void setCreditRecordDao(CreditRecordDao creditRecordDao) {
        this.creditRecordDao = creditRecordDao;
    }

    public CreditRecordDao getCreditRecordDao() {
        return creditRecordDao;
    }
    
    private float getAverageCredit(String userid) {
        List<CreditRecord> list = getCreditRecordDao().getCreditRecordListByUserid(userid, -1);
        float averageCredit = 0;
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                averageCredit += list.get(i).getCredit();
            }
            averageCredit = averageCredit / list.size();
        }
        return averageCredit == 0 ? 3.0f : averageCredit;
    }

    private void doUpdateCredit(String userid, float credit) {
        if (SystemPara.getUserTypeByUserid(userid) == 0) {// taxi
            getTaxiDao().doUpdateTaxiCredit(userid, credit);
        } else {
            getPassengerDao().doUpdatePassengerCredit(userid, credit);
        }
    }

    /**
     * P006/T006 Credit Rating信用评价
     * 
     * @param userid 用户id
     * @param requestNo 叫车编号
     * @param credit 信用评价
     * @param comments
     */
    public void doCreditRating(String userid, String requestNo, float credit, String comments){
        try {
            RequestInfo requestInfo = getCallTaxiDao().getRequestInfo(requestNo);
            CreditRecord record = new CreditRecord();
            record.setCreditUserid(userid);
            if (SystemPara.getUserTypeByUserid(userid) == 0) {// 出租车
                record.setUserid(requestInfo.getUserid());
            } else {// 乘客
                //record.setUserid(requestInfo.getOperatorid());
                record.setUserid(requestInfo.getUserid());
            }
            record.setRequestNo(requestNo);
            record.setCredit(credit);
            record.setComments(comments);
            record.setCreditTime(new Date());
            getCreditRecordDao().doSaveCreditRecord(record);

            // update Taxi/Passenger table credit
            float avgCredit = getAverageCredit(record.getUserid());
            doUpdateCredit(record.getUserid(), avgCredit);
        } catch (Exception e) {
            logger.error("User[" + userid + "] credit rate requestNo[" + requestNo + "] error: ", e);
        }
    }

    /**
     * P007/T007 query taxi/passenger credit detail list
     * 
     * @param userid
     * @param creditee : P007---taxi's cab(plate number); T007---passenger's userid
     * @param limits
     * @return
     */
    public List<CreditRecord> getCreditDetail(String userid, String creditee, int limit) {
        List<CreditRecord> list = new ArrayList<CreditRecord>();
        try {
            if(SystemPara.getUserTypeByUserid(userid) == 0){//taxi
                list = getCreditRecordDao().getCreditRecordListByUserid(creditee, limit);
            }else{//passenger
                list = getCreditRecordDao().getCreditRecordListByCab(creditee, limit);
            }
            
        } catch (Exception e) {
            logger.error("User[" + userid + "] query creditee[" + creditee + "] list error: ", e);
        }
        return list;
    }


    public CallTaxiDao getCallTaxiDao() {
        return callTaxiDao;
    }

    public void setCallTaxiDao(CallTaxiDao callTaxiDao) {
        this.callTaxiDao = callTaxiDao;
    }

    public TaxiDao getTaxiDao() {
        return taxiDao;
    }

    public void setTaxiDao(TaxiDao taxiDao) {
        this.taxiDao = taxiDao;
    }

    public PassengerDao getPassengerDao() {
        return passengerDao;
    }

    public void setPassengerDao(PassengerDao passengerDao) {
        this.passengerDao = passengerDao;
    }

}
