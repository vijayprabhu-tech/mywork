package com.pf.dto;

import java.util.ArrayList;

public class PortfolioResponse {

    private String portfolioId;
    private String valuationDate;
    private double portfolioReturnPct;
    private double benchmarkReturnPct;
    private double exessReturnPct;
    private String status;
    private ArrayList<String> reasons;
    private String processedAt;

    public String getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    public String getValuationDate() {
        return valuationDate;
    }

    public void setValuationDate(String valuationDate) {
        this.valuationDate = valuationDate;
    }

    public double getPortfolioReturnPct() {
        return portfolioReturnPct;
    }

    public void setPortfolioReturnPct(double portfolioReturnPct) {
        this.portfolioReturnPct = portfolioReturnPct;
    }

    public double getBenchmarkReturnPct() {
        return benchmarkReturnPct;
    }

    public void setBenchmarkReturnPct(double benchmarkReturnPct) {
        this.benchmarkReturnPct = benchmarkReturnPct;
    }

    public double getExessReturnPct() {
        return exessReturnPct;
    }

    public void setExessReturnPct(double exessReturnPct) {
        this.exessReturnPct = exessReturnPct;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<String> getReasons() {
        return reasons;
    }

    public void setReasons(ArrayList<String> reasons) {
        this.reasons = reasons;
    }

    public String getProcessedAt() {
        return processedAt;
    }

    public void setProcessedAt(String processedAt) {
        this.processedAt = processedAt;
    }




}
