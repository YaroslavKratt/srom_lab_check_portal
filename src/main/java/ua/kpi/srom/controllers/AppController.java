package ua.kpi.srom.controllers;

import static java.util.Objects.requireNonNull;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class AppController {
  private final LongArithmeticService longArithmeticService;
  private final FieldArithmetic finiteFieldArithmeticPolynomial;

  @Qualifier("longArithmeticValidator")
  private final Validator longArithmeticValidator;

  @Qualifier("finiteFieldValidator")
  private final Validator finiteFieldValidator;

  private final Converter<LongArithmeticDto, LongArithmeticModel> ladDtoToModel;
  private final Converter<LongArithmeticModel, LongArithmeticDto> laModelToDto;
  private final Converter<FiniteFieldDto, FiniteFieldModel> ffDtoToModel;
  private final Converter<FiniteFieldModel, FiniteFieldDto> ffModelToDto;

  @GetMapping("/")
  public String getLongArithmeticPage(Model model) {
    model.addAttribute("longArithmeticDto", new LongArithmeticDto());
    model.addAttribute("currentPage", "long-arithmetic");
    return ControllerConstants.View.LONG_ARITHMETIC;
  }

  @GetMapping(value = "/calculate-finite-field")
  public String getFiniteFieldPage(Model model) {
    model.addAttribute("finiteFieldDto", new FiniteFieldDto());
    model.addAttribute("currentPage", "field-arithmetic");
    return ControllerConstants.View.FINITE_FIELD;
  }

  @PostMapping(value = "/calculate-finite-field")
  public String calculateFiniteField(
      @Valid @ModelAttribute("finiteFieldDto") FiniteFieldDto finiteFieldDto,
      BindingResult bindingResult,
      Model model) {

    finiteFieldValidator.validate(finiteFieldDto, bindingResult);
    model.addAttribute("currentPage", "field-arithmetic");
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
    model.addAttribute("currentPage", "long-arithmetic");
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
