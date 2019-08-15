package ua.kpi.ipt.srom.srom_check.validators;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import ua.kpi.ipt.srom.srom_check.models.LongArithmeticModel;
import ua.kpi.ipt.srom.srom_check.converters.NumberSystem;

import javax.validation.Validator;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class LongArithmeticValidator implements org.springframework.validation.Validator {
    private static final String HEX = "(^$)|(^[0-9A-Fa-f]+$)";
    private static final String BIN = "(^$)|(^[01]+$)";
    private static final String DEC = "(^$)|(^\\\\d+$)";

    private static final Map<String, String> numberSystemMap = new HashMap();
    @Autowired
    private Validator validator;

    static {
        numberSystemMap.put(NumberSystem.HEX.toString(), HEX);
        numberSystemMap.put(NumberSystem.BIN.toString(), BIN);
        numberSystemMap.put(NumberSystem.DECIMAL.toString(), DEC);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return LongArithmeticModel.class.equals(aClass);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        LongArithmeticModel longArithmeticModel = (LongArithmeticModel) obj;
        String numberSystem = longArithmeticModel.getNumberSystem();

        if (isNotValidField(longArithmeticModel.getFirstNumber(), numberSystem)) {
            errors.rejectValue("firstNumber", "error.wrong.format");
            log.warn("Wrong input for first field, number system " + numberSystem);
        }

        if (isNotValidField(longArithmeticModel.getSecondNumber(), numberSystem)) {
            errors.rejectValue("secondNumber", "error.wrong.format");
            log.warn("Wrong input for second field, number system " + numberSystem);
        }

        if (isNotValidField(longArithmeticModel.getModule(), numberSystem)) {
            errors.rejectValue("module", "error.wrong.format");
            log.warn("Wrong input for module field, number system " + numberSystem);
        }
    }

    private boolean isNotValidField(String field, String numberSystem) {
        return !field.matches(numberSystemMap.get(numberSystem));
    }
}