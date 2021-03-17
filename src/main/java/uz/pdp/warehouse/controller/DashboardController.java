package uz.pdp.warehouse.controller;

import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouse.payload.DailyTotal;
import uz.pdp.warehouse.service.DashboardService;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/input")
    DailyTotal getDashboard(){
       return dashboardService.getDashboard1();
   }

}
