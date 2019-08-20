package ua.kpi.ipt.srom.srom_check.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.kpi.ipt.srom.srom_check.dto.LongArithmeticDto;
import ua.kpi.ipt.srom.srom_check.models.LongArithmeticModel;
import ua.kpi.ipt.srom.srom_check.services.CalculationService;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.validation.Validator;

@Controller
public class LongArithmeticController {
    @Resource
    private CalculationService calculationService;

    @Resource
    @Qualifier("longArithmeticValidator")
    private Validator validator;
    @Resource
    private Converter<LongArithmeticDto,LongArithmeticModel> dtoToModel;
    @Resource
    private Converter<LongArithmeticModel,LongArithmeticDto> modelToDto;


    private static final Logger LOG = LoggerFactory.getLogger(LongArithmeticController.class);

    @GetMapping("/")
    public String getLongArithmeticPage(Model model) {
        model.addAttribute("longArithmeticDto", new LongArithmeticDto());
        return "long_arithmetic";
    }

    @GetMapping("/error")
    public String error(){
        return "error";
    }

    @PostMapping(value = "/calculate")
    public String calculate(@Valid @ModelAttribute("longArithmeticDto") LongArithmeticDto longArithmeticDto, BindingResult bindingResult, Model model) {
        validator.validate(longArithmeticDto, bindingResult);
        if(bindingResult.hasErrors())
            return "long_arithmetic";

        LongArithmeticModel longArithmeticModel = calculationService.calculateAll(dtoToModel.convert(longArithmeticDto));
        model.addAttribute("longArithmeticDto", modelToDto.convert(longArithmeticModel));

        return "long_arithmetic";
    }
}