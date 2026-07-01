package com.pf.service;

import com.pf.constants.ApplicationConstants;
import com.pf.dto.PortfolioRequest;
import com.pf.dto.PortfolioResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class PortfolioPerformanceService {

    public PortfolioResponse pfValidateAndCalculate(PortfolioRequest pfObject) {

        double beginValue = pfObject.getBeginMarketValue();
        double endValue = pfObject.getEndMarketValue();
        double netFlow = pfObject.getNetCashFlow();
        double pfReturns = 0.0;
        String currency = pfObject.getCurrency();
        double benchMarkPct = pfObject.getBenchmarkReturnPct();
        String status = "";
        ArrayList<String> reasonsList;

        PortfolioResponse pfResponseObj = new PortfolioResponse();

        if (currency.equalsIgnoreCase("USD") && beginValue >= 0 && endValue >= 0) {

            if (beginValue > 0) {
                pfReturns = ((endValue - beginValue - netFlow) / beginValue) * 100;
            } else {
                status = ApplicationConstants.PORTFOLIO_STATUS_INVALID_INPUT;
            }
            if (beginValue == 0 && endValue != 0) {
                status = ApplicationConstants.PORTFOLIO_STATUS_INVALID_INPUT;
            }
        } else {
            status = ApplicationConstants.PORTFOLIO_STATUS_INVALID_INPUT;
        }

        if (Math.abs(pfReturns - benchMarkPct) > 5) {
            status = ApplicationConstants.PORTFOLIO_STATUS_REVIEW_REQUIRED;
        } else if (netFlow > beginValue * 0.20) {
            status = ApplicationConstants.PORTFOLIO_STATUS_REVIEW_REQUIRED;
        } else {
            status = ApplicationConstants.PORTFOLIO_STATUS_VALID;
        }

        pfResponseObj.setPortfolioId(pfObject.getPortfolioId());
        pfResponseObj.setValuationDate(pfObject.getValuationDate());
        pfResponseObj.setPortfolioReturnPct(pfReturns);
        pfResponseObj.setBenchmarkReturnPct(pfObject.getBenchmarkReturnPct());
        pfResponseObj.setExessReturnPct(pfObject.getEndMarketValue());
        pfResponseObj.setStatus(status);

        reasonsList = new ArrayList<>();
        reasonsList.add(pfObject.getRequestedBy());
        reasonsList.add(status);
        pfResponseObj.setReasons(reasonsList);

        Date currentDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String formattedDate = formatter.format(currentDate);
        pfResponseObj.setProcessedAt(formattedDate);

        return pfResponseObj;
    }

}
