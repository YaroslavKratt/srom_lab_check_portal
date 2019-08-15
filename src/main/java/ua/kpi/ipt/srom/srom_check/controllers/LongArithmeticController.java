package ua.kpi.ipt.srom.srom_check.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.kpi.ipt.srom.srom_check.models.LongArithmeticModel;
import ua.kpi.ipt.srom.srom_check.services.CalculationService;
import ua.kpi.ipt.srom.srom_check.utils.StringToEnumConverterFactory;

import javax.validation.Valid;

import org.springframework.validation.Validator;

@Controller
public class LongArithmeticController {

    private final CalculationService calculationService;
    @Autowired
    @Qualifier("longArithmeticValidator") // spring validator
    private Validator validator;
    private static final Logger LOG = LoggerFactory.getLogger(LongArithmeticController.class);

    public LongArithmeticController(CalculationService calculationService, StringToEnumConverterFactory stringToEnumConverterFactory) {
        this.calculationService = calculationService;
    }

    @GetMapping("/")
    public String getLongArithmeticPage(Model model) {
        model.addAttribute("longArithmeticModel", new LongArithmeticModel());
        return "long_arithmetic";
    }

    @RequestMapping(value = "/calculate", method = RequestMethod.POST)
    public String calculate(@Valid @ModelAttribute("longArithmeticModel") LongArithmeticModel longArithmeticModel, BindingResult bindingResult, Model model) {
        validator.validate(longArithmeticModel, bindingResult);
        model.addAttribute("longArithmetic", longArithmeticModel);
        calculationService.calculateAll();
        return "long_arithmetic";
    }
}