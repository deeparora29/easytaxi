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

/**
 * 用于信用评价的接口
 * 
 * @author renmian
 */
public class CreditRateService extends BaseService {

    private CreditRecordDao creditRecordDao;

    private CallTaxiDao callTaxiDao;

    public void setCreditRecordDao(CreditRecordDao creditRecordDao) {
        this.creditRecordDao = creditRecordDao;
    }

    public CreditRecordDao getCreditRecordDao() {
        return creditRecordDao;
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
            record.setUserid(userid);
            record.setType(SystemPara.getUserTypeByUserid(userid));
            if (record.getType() == 0) {// 出租车
                record.setCreditee(requestInfo.getUserid());
            } else {// 乘客
                record.setCreditee(requestInfo.getOperatorid());
            }
            record.setRequestNo(requestNo);
            record.setCredit(credit);
            record.setComments(comments);
            record.setCreditTime(new Date());
            getCreditRecordDao().doSaveCreditRecord(record);
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
            list = getCreditRecordDao().getCreditRecordListByCreditee(creditee, limit);
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

}
