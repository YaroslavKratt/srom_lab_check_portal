
package ua.kpi.ipt.srom.srom_check.validators;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import ua.kpi.ipt.srom.srom_check.dto.FiniteFieldDto;
import ua.kpi.ipt.srom.srom_check.dto.LongArithmeticDto;
import ua.kpi.ipt.srom.srom_check.models.NumberSystem;

import java.util.HashMap;
import java.util.Map;

import static ua.kpi.ipt.srom.srom_check.validators.ValidationConstants.*;

@Service
@Slf4j
public class FiniteFieldValidator implements org.springframework.validation.Validator {
    private static final Map<String, String> numberSystemMap = new HashMap<>();

    static {
        numberSystemMap.put(NumberSystem.HEX.toString(), HEX_REGEXP);
        numberSystemMap.put(NumberSystem.BIN.toString(), BIN_REGEXP);
        numberSystemMap.put(NumberSystem.DECIMAL.toString(), DEC_REGEXP);
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
            log.warn("Wrong input for first field, number system " + numberSystem);
        }

        if (isNotValidField(finiteFieldDto.getSecondNumber(), numberSystem)) {
            errors.rejectValue("secondNumber", "error.wrong.format");
            log.warn("Wrong input for second field, number system " + numberSystem);
        }

        if (isNotValidField(finiteFieldDto.getThirdNumber(), numberSystem)) {
            errors.rejectValue("thirdNumber", "error.wrong.format");
            log.warn("Wrong input for third field, number system " + numberSystem);
        }
    }

    private boolean isNotValidField(String field, String numberSystem) {
        return !field.matches(numberSystemMap.get(numberSystem));
    }
}