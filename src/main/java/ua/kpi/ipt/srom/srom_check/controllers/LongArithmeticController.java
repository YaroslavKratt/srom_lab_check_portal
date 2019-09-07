package ua.kpi.ipt.srom.srom_check.controllers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.kpi.ipt.srom.srom_check.dto.FiniteFieldDto;
import ua.kpi.ipt.srom.srom_check.dto.LongArithmeticDto;
import ua.kpi.ipt.srom.srom_check.models.LongArithmeticModel;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.validation.Validator;
import ua.kpi.ipt.srom.srom_check.services.FieldArithmetic;
import ua.kpi.ipt.srom.srom_check.services.LongArithmeticService;

import static java.util.Objects.*;

@Controller
public class LongArithmeticController {
    @Resource
    private LongArithmeticService longArithmeticService;
    @Resource
    private FieldArithmetic finiteFieldArithmeticPolynomial;
    @Resource
    @Qualifier("longArithmeticValidator")
    private Validator validator;

    @Resource
    private Converter<LongArithmeticDto, LongArithmeticModel> dtoToModel;

    @Resource
    private Converter<LongArithmeticModel, LongArithmeticDto> modelToDto;


    @GetMapping("/")
    public String getLongArithmeticPage(Model model) {
        model.addAttribute("longArithmeticDto", new LongArithmeticDto());
        return "long_arithmetic";
    }

    @PostMapping(value = "/calculate-finite-field")
    public String calculateFiniteField(@Valid @ModelAttribute("finiteFieldDto") FiniteFieldDto finiteFieldDto,
                                       BindingResult bindingResult, Model model) {

        validator.validate(finiteFieldDto, bindingResult);
        if (bindingResult.hasErrors())
            return ControllerConstants.View.FINITE_FIELD;

finiteFieldArithmeticPolynomial.calculateAll();
        return ControllerConstants.View.FINITE_FIELD;
    }

    @PostMapping(value = "/calculate-long-arithmetic")
    public String calculateLongArithmetic(@Valid @ModelAttribute("longArithmeticDto") LongArithmeticDto longArithmeticDto,
                                          BindingResult bindingResult, Model model) {
        validator.validate(longArithmeticDto, bindingResult);
        if (bindingResult.hasErrors())
            return ControllerConstants.View.LONG_ARITHMETIC;


        LongArithmeticModel longArithmeticModel = longArithmeticService.calculateAll(requireNonNull(dtoToModel.convert(longArithmeticDto)));
        model.addAttribute("longArithmeticDto", modelToDto.convert(longArithmeticModel));
        model.addAttribute("calculated", true);
        return ControllerConstants.View.LONG_ARITHMETIC;
    }
}