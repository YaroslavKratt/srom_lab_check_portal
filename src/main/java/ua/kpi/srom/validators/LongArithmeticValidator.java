package ua.kpi.srom.validators;

import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import ua.kpi.srom.dto.LongArithmeticDto;
import ua.kpi.srom.models.NumberSystem;

@Service
@Slf4j
public class LongArithmeticValidator implements org.springframework.validation.Validator {
  private static final Map<String, String> numberSystemMap = new HashMap();

  static {
    numberSystemMap.put(NumberSystem.HEX.toString(), ValidationConstants.HEX_REGEXP);
    numberSystemMap.put(NumberSystem.BIN.toString(), ValidationConstants.BIN_REGEXP);
    numberSystemMap.put(NumberSystem.DECIMAL.toString(), ValidationConstants.DEC_REGEXP);
  }

  @Override
  public boolean supports(Class<?> aClass) {
    return LongArithmeticDto.class.equals(aClass);
  }

  @Override
  public void validate(Object obj, Errors errors) {
    LongArithmeticDto longArithmeticDto = (LongArithmeticDto) obj;
    String numberSystem = longArithmeticDto.getNumberSystem();

    if (isNotValidField(longArithmeticDto.getFirstNumber(), numberSystem)) {
      errors.rejectValue("firstNumber", "error.wrong.format");
      log.warn("Wrong input for first field, number system {}", numberSystem);
    }

    if (isNotValidField(longArithmeticDto.getSecondNumber(), numberSystem)) {
      errors.rejectValue("secondNumber", "error.wrong.format");
      log.warn("Wrong input for second field, number system {}", numberSystem);
    }

    if (isNotValidField(longArithmeticDto.getModule(), numberSystem)) {
      errors.rejectValue("module", "error.wrong.format");
      log.warn("Wrong input for module field, number system {}", numberSystem);
    }
  }

  private boolean isNotValidField(String field, String numberSystem) {
    return !field.matches(numberSystemMap.get(numberSystem));
  }
}
