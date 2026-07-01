package main.java.com.pf.controller;


import main.java.com.pf.dto.PortfolioRequest;
import main.java.com.pf.dto.PortfolioResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/portfolio")
public class PortfolioPerformanceController {

    @PostMapping("/performance")
    public ResponseEntity<PortfolioResponse> calculatePerformance(@RequestBody PortfolioRequest request) {

        PortfolioResponse response = new PortfolioResponse();

        return ResponseEntity.ok(response);
    }
}
