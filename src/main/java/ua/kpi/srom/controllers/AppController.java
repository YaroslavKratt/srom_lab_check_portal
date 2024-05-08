package ua.kpi.srom.controllers;

import static java.util.Objects.requireNonNull;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ua.kpi.srom.dto.FiniteFieldDto;
import ua.kpi.srom.dto.LongArithmeticDto;
import ua.kpi.srom.models.BASIS;
import ua.kpi.srom.models.FiniteFieldModel;
import ua.kpi.srom.models.LongArithmeticModel;
import ua.kpi.srom.services.FieldArithmetic;
import ua.kpi.srom.services.LongArithmeticService;

@Slf4j
@Controller
public class AppController {
  @Resource private LongArithmeticService longArithmeticService;

  @Resource private FieldArithmetic finiteFieldArithmeticPolynomial;

  @Resource
  @Qualifier("longArithmeticValidator")
  private Validator longArithmeticValidator;

  @Resource
  @Qualifier("finiteFieldValidator")
  private Validator finiteFieldValidator;

  @Resource private Converter<LongArithmeticDto, LongArithmeticModel> ladDtoToModel;

  @Resource private Converter<LongArithmeticModel, LongArithmeticDto> laModelToDto;

  @Resource private Converter<FiniteFieldDto, FiniteFieldModel> ffDtoToModel;

  @Resource private Converter<FiniteFieldModel, FiniteFieldDto> ffModelToDto;

  @GetMapping("/")
  public String getLongArithmeticPage(Model model) {
    model.addAttribute("longArithmeticDto", new LongArithmeticDto());
    return ControllerConstants.View.LONG_ARITHMETIC;
  }

  @GetMapping(value = "/calculate-finite-field")
  public String getFiniteFieldPage(Model model) {
    model.addAttribute("finiteFieldDto", new FiniteFieldDto());

    return ControllerConstants.View.FINITE_FIELD;
  }

  @PostMapping(value = "/calculate-finite-field")
  public String calculateFiniteField(
      @Valid @ModelAttribute("finiteFieldDto") FiniteFieldDto finiteFieldDto,
      BindingResult bindingResult,
      Model model) {

    finiteFieldValidator.validate(finiteFieldDto, bindingResult);
    if (bindingResult.hasErrors()) return ControllerConstants.View.FINITE_FIELD;
    FiniteFieldModel result = null;

    if (finiteFieldDto.getBasis().equals(BASIS.POLYNOMIAL.toString())) {
      result = finiteFieldArithmeticPolynomial.calculateAll(ffDtoToModel.convert(finiteFieldDto));
    }

    model.addAttribute("finiteFieldDto", ffModelToDto.convert(requireNonNull(result)));
    model.addAttribute("calculated", true);
    return ControllerConstants.View.FINITE_FIELD;
  }

  @PostMapping(value = "/calculate-long-arithmetic")
  public String calculateLongArithmetic(
      @Valid @ModelAttribute("longArithmeticDto") LongArithmeticDto longArithmeticDto,
      BindingResult bindingResult,
      Model model) {
    longArithmeticValidator.validate(longArithmeticDto, bindingResult);
    if (bindingResult.hasErrors()) return ControllerConstants.View.LONG_ARITHMETIC;

    LongArithmeticModel longArithmeticModel =
        longArithmeticService.calculateAll(
            requireNonNull(ladDtoToModel.convert(longArithmeticDto)));

    model.addAttribute("longArithmeticDto", laModelToDto.convert(longArithmeticModel));
    log.info(requireNonNull(laModelToDto.convert(longArithmeticModel)).toString());
    model.addAttribute("calculated", true);
    return ControllerConstants.View.LONG_ARITHMETIC;
  }
}
