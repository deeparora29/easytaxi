package com.easytaxi.request.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.easytaxi.bo.Passenger;
import com.easytaxi.bo.Taxi;
import com.easytaxi.common.ErrorCode;
import com.easytaxi.common.SystemPara;
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
     * P003 sponsor a call-taxi request requestNo == "" 表示用车请求发起失败
     * 
     * @return
     */
    public String requestTaxi(RequestInfo requestInfo) {
        String requestNo = "";
        try {
            requestNo = getSerialNum("request_no", 12, "true");
            requestInfo.setRequestNo(requestNo);
            getCallTaxiDao().doDeleteRequestInfo(requestNo);
            getCallTaxiDao().doSaveRequestInfo(requestInfo);
        } catch (Exception ex) {
            logger.error("Passenger[" + requestInfo.getUserid() + "] request taxi error: ", ex);
        }
        return requestNo;
    }

    /**
     * P004 get confirm info 这个接口封装已经比较全了，包含了errorCode的信息，errorCode==ErrorCode.SUCCESS可以从中获取Taxi 的详细信息
     * 
     * @param userid
     * @param requestNo
     * @return
     */
    public RequestResult getConfirmedTaxiInfo(String userid, String requestNo) {
        RequestResult result = new RequestResult();
        try {
            RequestInfo info = getCallTaxiDao().getRequestInfo(requestNo);
            if (info == null) {
                result.setErrorCode(ErrorCode.REQUEST_NOTEXIST);
                result.setComments("Request[" + requestNo + "] does not exist.");
            } else {
                switch (info.getStatus()) {
                    case SystemPara.REQUESTINFO_STATUS_ISVALID:
                        result.setErrorCode(ErrorCode.REQUEST_ISVALID);
                        result.setComments("Request[" + requestNo + "] is not confirmed.");
                        break;
                    case SystemPara.REQUESTINFO_STATUS_CONFIRMED:
                        result.setErrorCode(ErrorCode.SUCCESS);
                        Taxi taxi = getTaxiDao().getTaxiByUserid(info.getOperatorid());
                        result.setTaxi(taxi);
                        break;
                    case SystemPara.REQUESTINFO_STATUS_CANCELED:
                        result.setErrorCode(ErrorCode.REQUEST_CONCELED);
                        result.setComments("Request[" + requestNo + "] is canceled.");
                        break;

                    default:
                        break;
                }
            }
        } catch (Exception ex) {
            logger.error("Get confirmed taxi info error: ", ex);
            result.setErrorCode(ErrorCode.OPERATE_SERVERERROR);
            result.setComments("Error:" + ex.toString());
        }
        return result;
    }

    /**
     * P005/T005 cancel request 取消用车请求
     * 
     * @param userid ：操作者
     * @param requestNo：序列号
     * @param comments
     */
    public void cancelRequest(String userid, String requestNo, String comments) {
        try {
            RequestInfo requestInfo = new RequestInfo(requestNo);
            requestInfo.setStatus(SystemPara.REQUESTINFO_STATUS_CANCELED);
            requestInfo.setOperatorid(userid);
            requestInfo.setOperatorType(SystemPara.getUserTypeByUserid(userid));
            if (StringUtils.isEmpty(comments))
                comments = SystemPara.getUserType(requestInfo.getOperatorType()) + " canceled the request";
            requestInfo.setOperatorComments(comments);
            requestInfo.setOperatorTime(new Date());                   
            getCallTaxiDao().doUpdateRequestInfoByOperator(requestInfo);
        } catch (Exception e) {
            logger.error("Cancel request[" + requestNo + "] error: ", e);
        }
    }

    /**
     * T004 confirm a request,这个接口封装已经比较全了，包含了errorCode的信息，errorCode==ErrorCode.SUCCESS，返回Passenger的具体信息
     * 
     * @param userid
     * @param requestNo
     */
    public RequestResult confirmRequest(String userid, String requestNo) {
        RequestResult result = new RequestResult();
        try {
            
            RequestInfo requestInfo = getCallTaxiDao().getRequestInfo(requestNo);
            if(requestInfo != null){
                if (requestInfo.getStatus() == SystemPara.REQUESTINFO_STATUS_ISVALID) { // valid
                    // update requestinfo
                    requestInfo.setStatus(SystemPara.REQUESTINFO_STATUS_CONFIRMED);
                    requestInfo.setOperatorid(userid);
                    requestInfo.setOperatorType(0);
                    requestInfo.setOperatorComments("Taxi confirmed");
                    requestInfo.setOperatorTime(new Date());                   
                    getCallTaxiDao().doUpdateRequestInfoByOperator(requestInfo);
                    // get passenger info
                    Passenger passenger = getPassengerDao().getPassengerByUserid(requestInfo.getUserid());
                    double startLong = requestInfo.getStartLong();
                    double startLat = requestInfo.getStartLat();
                    passenger.getGpsdata().setLng(startLong);
                    passenger.getGpsdata().setLng(startLat);
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
            result.setErrorCode(ErrorCode.OPERATE_SERVERERROR);
            result.setComments("Error:" + ex.toString());
        }
        return result;
    }

    /**
     * T012 Get valid passenger’s request，返回有效地用车请求：status=0 &&时常在５分中内
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
            validCalls = getCallTaxiDao().getRequestInfoBy(SystemPara.REQUESTINFO_STATUS_ISVALID,
                DateUtil.convertDateTimeToString(startDate),
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
