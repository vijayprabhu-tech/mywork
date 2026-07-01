package com.pf.dto;


public class PortfolioRequest {
    private String portfolioId;
    private String valuationDate;
    private double beginMarketValue;
    private double endMarketValue;
    private double netCashFlow;
    private double benchmarkReturnPct;
    private String currency;
    private String requestedBy;

    public String getRequestedBy() {
        return requestedBy;
    }

    public void setRequestedBy(String requestedBy) {
        this.requestedBy = requestedBy;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getBenchmarkReturnPct() {
        return benchmarkReturnPct;
    }

    public void setBenchmarkReturnPct(double benchmarkReturnPct) {
        this.benchmarkReturnPct = benchmarkReturnPct;
    }

    public double getNetCashFlow() {
        return netCashFlow;
    }

    public void setNetCashFlow(double netCashFlow) {
        this.netCashFlow = netCashFlow;
    }

    public double getEndMarketValue() {
        return endMarketValue;
    }

    public void setEndMarketValue(double endMarketValue) {
        this.endMarketValue = endMarketValue;
    }

    public double getBeginMarketValue() {
        return beginMarketValue;
    }

    public void setBeginMarketValue(double beginMarketValue) {
        this.beginMarketValue = beginMarketValue;
    }

    public String getValuationDate() {
        return valuationDate;
    }

    public void setValuationDate(String valuationDate) {
        this.valuationDate = valuationDate;
    }

    public String getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

}