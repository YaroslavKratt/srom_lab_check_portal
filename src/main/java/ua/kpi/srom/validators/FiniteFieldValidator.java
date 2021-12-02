
package ua.kpi.srom.validators;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import ua.kpi.srom.dto.FiniteFieldDto;
import ua.kpi.srom.dto.LongArithmeticDto;
import ua.kpi.srom.models.NumberSystem;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class FiniteFieldValidator implements org.springframework.validation.Validator {
    private static final Map<String, String> numberSystemMap = new HashMap<>();

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
        FiniteFieldDto finiteFieldDto = (FiniteFieldDto) obj;
        String numberSystem = finiteFieldDto.getNumberSystem();

        if (isNotValidField(finiteFieldDto.getFirstNumber(), numberSystem)) {
            errors.rejectValue("firstNumber", "error.wrong.format");
            log.warn("Wrong input for first field, number system {}", numberSystem);
        }

        if (isNotValidField(finiteFieldDto.getSecondNumber(), numberSystem)) {
            errors.rejectValue("secondNumber", "error.wrong.format");
            log.warn("Wrong input for second field, number system {}", numberSystem);
        }

        if (isNotValidField(finiteFieldDto.getThirdNumber(), numberSystem)) {
            errors.rejectValue("thirdNumber", "error.wrong.format");
            log.warn("Wrong input for third field, number system {}", numberSystem);
        }
    }

    private boolean isNotValidField(String field, String numberSystem) {
        return !field.matches(numberSystemMap.get(numberSystem));
    }
}
