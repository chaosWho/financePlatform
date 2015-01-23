package com.sunlights.customer.service.impl;

import com.sunlights.common.MsgCode;
import com.sunlights.common.utils.CommonUtil;
import com.sunlights.customer.ActivityConstant;
import com.sunlights.customer.dal.CustomerDao;
import com.sunlights.customer.dal.impl.CustomerDaoImpl;
import com.sunlights.customer.service.AbstractShareInfoService;
import com.sunlights.customer.vo.ShareInfoContext;
import models.Activity;
import models.Customer;
import models.ShareInfo;
import org.apache.commons.lang3.StringUtils;
import play.Logger;

/**
 * Created by Administrator on 2014/12/17.
 */
public class InviteShareInfoServiceImpl extends AbstractShareInfoService {
    private CustomerDao customerDao = new CustomerDaoImpl();

    @Override
    public void prepareShareInfo(ShareInfoContext context) {

        /*if(StringUtils.isEmpty(context.getCustNo())) {
            throw CommonUtil.getInstance().errorBusinessException(MsgCode.LOGIN_TIMEOUT);
        }*/
        if(StringUtils.isEmpty(context.getCustNo())) {
            context.setRefId(ActivityConstant.NOT_LOGIN_CUSTOMER_NO);
        } else {
            context.setRefId(context.getCustNo());
        }

    }

    @Override
    public String getLongUrl(ShareInfoContext context) {
        ShareInfo shareInfo = context.getShareInfo();
        StringBuilder sb = new StringBuilder();
        String mobile = getMobile(context.getCustNo());
        sb.append(shareInfo.getBaseUrl());
        if(Integer.valueOf(ActivityConstant.ACCOUNT_COMMON_ONE).equals(shareInfo.getRelateRefId())) {
            //sb.append(context.getCommonParamter());
            sb.append("?mobile=" + mobile + "&scene=ASC002");
        }
        return sb.toString();
    }

    /**
     * 获得手机号
     * @return
     */
    private String getMobile(String custNo){
        if(StringUtils.isEmpty(custNo)) {
            return "";
        }
        Customer customer = customerDao.getCustomerByCustomerId(custNo);
        String mobile = customer.getMobile();//获得手机号
        Logger.debug("获得的手机号为:" + mobile);
        return mobile;
    }
}
