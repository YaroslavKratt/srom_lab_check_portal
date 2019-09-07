package ua.kpi.ipt.srom.srom_check.converters;

import cc.redberry.rings.bigint.BigInteger;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ua.kpi.ipt.srom.srom_check.dto.FiniteFieldDto;
import ua.kpi.ipt.srom.srom_check.models.FiniteFieldModel;
import ua.kpi.ipt.srom.srom_check.models.NumberSystem;


import static java.util.Optional.ofNullable;
@Component
public class FiniteFieldDtoToModel implements Converter<FiniteFieldDto, FiniteFieldModel> {

    @Override
    public FiniteFieldModel convert(FiniteFieldDto source) {
        NumberSystem numberSystem = NumberSystem.valueOf(source.getNumberSystem());

        return FiniteFieldModel.builder()
                .firstNumber(new BigInteger(source.getFirstNumber(), numberSystem.getValue()))
                .secondNumber(new BigInteger(source.getSecondNumber(), numberSystem.getValue()))
                .thirdNumber(ofNullable(source.getThirdNumber())
                        .filter(item -> !item.isEmpty())
                        .map(m -> new BigInteger(m, numberSystem.getValue()))
                        .orElse(null)
                )
                .numberSystem(numberSystem)
                .build();
    }
}