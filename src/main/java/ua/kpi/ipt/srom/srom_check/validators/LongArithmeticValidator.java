package ua.kpi.ipt.srom.srom_check.validators;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import ua.kpi.ipt.srom.srom_check.dto.LongArithmeticDto;
import ua.kpi.ipt.srom.srom_check.models.NumberSystem;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class LongArithmeticValidator implements org.springframework.validation.Validator {
    private static final String HEX = "(^$)|(^[0-9A-Fa-f]+$)";
    private static final String BIN = "(^$)|(^[01]+$)";
    private static final String DEC = "(^$)|(^[0-9]+$)";

    private static final Map<String, String> numberSystemMap = new HashMap();

    static {
        numberSystemMap.put(NumberSystem.HEX.toString(), HEX);
        numberSystemMap.put(NumberSystem.BIN.toString(), BIN);
        numberSystemMap.put(NumberSystem.DECIMAL.toString(), DEC);
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
            log.warn("Wrong input for first field, number system " + numberSystem);
        }

        if (isNotValidField(longArithmeticDto.getSecondNumber(), numberSystem)) {
            errors.rejectValue("secondNumber", "error.wrong.format");
            log.warn("Wrong input for second field, number system " + numberSystem);
        }

        if (isNotValidField(longArithmeticDto.getModule(), numberSystem)) {
            errors.rejectValue("module", "error.wrong.format");
            log.warn("Wrong input for module field, number system " + numberSystem);
        }
    }

    private boolean isNotValidField(String field, String numberSystem) {
        return !field.matches(numberSystemMap.get(numberSystem));
    }
}