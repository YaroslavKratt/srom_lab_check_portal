package ua.kpi.ipt.srom.srom_check.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ua.kpi.ipt.srom.srom_check.dto.LongArithmeticDto;
import ua.kpi.ipt.srom.srom_check.models.LongArithmeticModel;

@Component
public class LongArithmeticModelToDtoConverter implements Converter<LongArithmeticModel, LongArithmeticDto> {
    @Override
    public LongArithmeticDto convert(LongArithmeticModel source) {
        int numberSystem = source.getNumberSystem().getValue();

        return LongArithmeticDto.builder()
                .firstNumber(source.getFirstNumber().toString(numberSystem))
                .secondNumber(source.getSecondNumber().toString(numberSystem))
                .sum(source.getSum().toString(numberSystem))
                .division(source.getDivision().toString(numberSystem))
                .numberSystem(source.getNumberSystem().toString())
                .multiplication(source.getMultiplication().toString(numberSystem))
                .pow(source.getPow().toString(numberSystem))
                .module(source.getModule().toString(numberSystem))
                .square(source.getSquare().toString(numberSystem))
                .substraction(source.getSubstraction().toString(numberSystem))
                .build();
    }
}