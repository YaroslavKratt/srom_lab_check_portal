package ua.kpi.ipt.srom.srom_check.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ua.kpi.ipt.srom.srom_check.dto.FiniteFieldDto;
import ua.kpi.ipt.srom.srom_check.models.FiniteFieldModel;

@Component
public class FiniteFieldModelToDto implements Converter<FiniteFieldModel, FiniteFieldDto> {
    @Override
    public FiniteFieldDto convert(FiniteFieldModel source) {
        int numberSystem = source.getNumberSystem().getValue();
        return FiniteFieldDto.builder()
                .firstNumber(source.getFirstNumber().toString(numberSystem))
                .secondNumber(source.getSecondNumber().toString(numberSystem))
                .thirdNumber(source.getThirdNumber().toString(numberSystem))
                .sum(source.getSum().toString(numberSystem))
                .neutralByMultiplication(source.getGetNeutralByMultiplication().toString(numberSystem))
                .multiplication(source.getMultiplication().toString(numberSystem))
                .numberSystem(source.getNumberSystem().toString())
                .neutralByAdding(source.getNeutralByAdding().toString(numberSystem))
                .pow(source.getPow().toString(numberSystem))
                .reverse(source.getReverse().toString(numberSystem))
                .square(source.getSquare().toString(numberSystem))
                .trace(source.getTrace().toString(numberSystem))
                .build();
    }
}