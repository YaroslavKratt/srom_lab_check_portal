package ua.kpi.ipt.srom.srom_check.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.kpi.ipt.srom.srom_check.services.CalculationService;

@RestController
public class LongArithmeticController {

    private final   CalculationService calculationService;

    @Value("${spring.application.name}")
    String appName;

    public LongArithmeticController(CalculationService calculationService) {
        this.calculationService = calculationService;
    }

    @GetMapping("/")
    public String getLongArithmeticPage() {
        return "long_arithmetic";
    }
    @PostMapping("/calculate")
    public String calculate(Model model){
        calculationService.calculateAll();
        return "long_arithmetic";
    }
}