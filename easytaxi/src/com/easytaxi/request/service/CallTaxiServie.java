package com.easytaxi.request.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.easytaxi.bo.Passenger;
import com.easytaxi.bo.Taxi;
import com.easytaxi.common.ErrorCode;
import com.easytaxi.common.service.BaseService;
import com.easytaxi.common.utils.DateUtil;
import com.easytaxi.request.bo.RequestInfo;
import com.easytaxi.request.bo.RequestResult;
import com.easytaxi.request.dao.CallTaxiDao;
import com.easytaxi.usermgr.dao.PassengerDao;
import com.easytaxi.usermgr.dao.TaxiDao;

/**
 * @description: passenger sponsor a taxi
 * @author rennnmia
 */
public class CallTaxiServie extends BaseService {

    private CallTaxiDao callTaxiDao;
    private PassengerDao passengerDao;
    private TaxiDao taxiDao;

    public void setCallTaxiDao(CallTaxiDao callTaxiDao) {
        this.callTaxiDao = callTaxiDao;
    }

    public CallTaxiDao getCallTaxiDao() {
        return callTaxiDao;
    }

    /**
     * P003 sponsor a call-taxi request
     * 
     * @return
     */
    public String requestTaxi(RequestInfo requestInfo) {
        String requestNo = "";
        try {
            requestNo = getSerialNum("p_user_id", 5 , "false");
            requestInfo.setRequestNo(requestNo);
            getCallTaxiDao().doDeleteRequestInfo(requestNo);
            getCallTaxiDao().doSaveRequestInfo(requestInfo);
        } catch (Exception ex) {
            logger.error("Passenger[" + requestInfo.getUserid() + "] request taxi error: ", ex);
        }
        return requestNo;
    }

    /**
     * P004 get confirm info
     * @param userid
     * @param requestNo
     * @return
     */
    public Taxi getConfirmedTaxiInfo(String userid, String requestNo) {
        Taxi taxi = null;
        // TODO: query db to get confirm info
        return taxi;
    }

    /**
     * P005/T005 cancel request
     * @param userid
     * @param requestNo
     * @param comments
     */
    public void cancelRequest(String userid, String requestNo, String comments) {
        // TODO: update requestinfo table
    }
    
    /**
     * T004 confirm a request
     * 
     * @param userid
     * @param requestNo
     */
    public RequestResult confirmRequest(String userid, String requestNo) {
        RequestResult result = new RequestResult();
        try {
            
            RequestInfo requestInfo = getCallTaxiDao().queryRequestInfo(requestNo);
            if(requestInfo != null){
                if(requestInfo.getStatus() == 0){ //valid
                    // update requestinfo
                    requestInfo.setStatus(1);
                    requestInfo.setOperatorid(userid);
                    requestInfo.setOperatorType(0);
                    requestInfo.setOperatorComments("Taxi confirmed");
                    requestInfo.setOperatorTime(new Date());                   
                    getCallTaxiDao().doUpdateRequestInfoByOperator(requestInfo);
                    // get passenger info
                    Passenger passenger = getPassengerDao().getPassengerByUserid(requestInfo.getUserid());
                    result.setPassenger(passenger);
                    result.setErrorCode(ErrorCode.SUCCESS);
                } else if (requestInfo.getStatus() != 0) {
                    result.setErrorCode(ErrorCode.REQEST_OUTOFDATE);
                    result.setComments("Request[" + requestNo + "] status=" + requestInfo.getStatus());
            }
            }else{
                result.setErrorCode(ErrorCode.REQUEST_NOTEXIST);
                result.setComments("Request[" + requestNo + "] not exist");
            }
            

        } catch (Exception ex) {
            logger.error("Taxi confirm request error: ", ex);
        }
        return result;
    }

    /**
     * T012 Get valid passenger’s request
     * 
     * @return
     */
    public List<RequestInfo> getValidRequest() {
        List<RequestInfo> validCalls = new ArrayList<RequestInfo>();
        // default query valid request in 5mins
        long timeDiff = 5 * 60 * 1000;
        try {
            Calendar cal = Calendar.getInstance();
            Date startDate = new Date(cal.getTimeInMillis() - timeDiff);
            Date endDate = new Date(cal.getTimeInMillis() + timeDiff);
            validCalls = getCallTaxiDao().getRequestInfoBy(0, DateUtil.convertDateTimeToString(startDate),
                DateUtil.convertDateTimeToString(endDate));
        } catch (Exception ex) {
            logger.error("Query valid(status=0) request error: ", ex);
        }
        return validCalls;
    }

    public PassengerDao getPassengerDao() {
        return passengerDao;
    }

    public void setPassengerDao(PassengerDao passengerDao) {
        this.passengerDao = passengerDao;
    }

    public TaxiDao getTaxiDao() {
        return taxiDao;
    }

    public void setTaxiDao(TaxiDao taxiDao) {
        this.taxiDao = taxiDao;
    }
    
}
