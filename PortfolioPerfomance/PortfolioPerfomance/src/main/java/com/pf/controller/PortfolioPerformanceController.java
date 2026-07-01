package com.pf.controller;


import com.pf.dto.PortfolioRequest;
import com.pf.dto.PortfolioResponse;
import com.pf.service.PortfolioPerformanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/portfolio")
public class PortfolioPerformanceController {

    @PostMapping("/performance")
    public ResponseEntity<PortfolioResponse> calculatePerformance(@RequestBody PortfolioRequest request) {

        PortfolioPerformanceService pfService = new PortfolioPerformanceService();
        PortfolioResponse response = new PortfolioResponse();
        response = pfService.pfValidateAndCalculate(request);

        return ResponseEntity.ok(response);
    }
}
