package ua.kpi.ipt.srom.srom_check.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ua.kpi.ipt.srom.srom_check.dto.LongArithmeticDto;
import ua.kpi.ipt.srom.srom_check.models.LongArithmeticModel;
import ua.kpi.ipt.srom.srom_check.models.NumberSystem;

import java.math.BigInteger;

@Component
public class LongArithmeticDtoToModelConverter implements Converter<LongArithmeticDto, LongArithmeticModel> {


    @Override
    public LongArithmeticModel convert(LongArithmeticDto source) {
        NumberSystem numberSystem = NumberSystem.valueOf(source.getNumberSystem());

        return LongArithmeticModel.builder()
                .firstNumber(new BigInteger(source.getFirstNumber(), numberSystem.getValue()))
                .secondNumber(new BigInteger(source.getSecondNumber(), numberSystem.getValue()))
                .numberSystem(numberSystem)
                .build();
    }
}