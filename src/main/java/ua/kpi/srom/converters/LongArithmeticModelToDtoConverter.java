package ua.kpi.srom.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ua.kpi.srom.dto.LongArithmeticDto;
import ua.kpi.srom.models.LongArithmeticModel;

import java.math.BigInteger;
import java.util.Optional;

@Component
public class LongArithmeticModelToDtoConverter implements Converter<LongArithmeticModel, LongArithmeticDto> {
    @Override
    public LongArithmeticDto convert(LongArithmeticModel source) {
        int numberSystem = source.getNumberSystem().getValue();

        return LongArithmeticDto.builder()
                .firstNumber(mapIfNotNull(source.getFirstNumber(), numberSystem))
                .secondNumber(mapIfNotNull(source.getSecondNumber(), numberSystem))
                .module(mapIfNotNull(source.getModule(), numberSystem))
                .sum(mapIfNotNull(source.getSum(), numberSystem))
                .division(mapIfNotNull(source.getDivision(), numberSystem))
                .numberSystem(source.getNumberSystem().toString())
                .multiplication(mapIfNotNull(source.getMultiplication(), numberSystem))
                .pow(mapIfNotNull(source.getPow(), numberSystem))
                .moduleRes(mapIfNotNull(source.getModuleRes(), numberSystem))
                .square(mapIfNotNull(source.getSquare(), numberSystem))
                .subtraction(mapIfNotNull(source.getSubtraction(), numberSystem))
                .gcd(mapIfNotNull(source.getGcd(), numberSystem))
                .lcm(mapIfNotNull(source.getLcm(), numberSystem))
                .moduleAdd(mapIfNotNull(source.getModuleAdd(), numberSystem))
                .moduleMult(mapIfNotNull(source.getModuleMult(), numberSystem))
                .modulePow(mapIfNotNull(source.getModulePow(), numberSystem))
                .moduleSquare(mapIfNotNull(source.getModuleSquare(), numberSystem))
                .moduleSubtractioc(mapIfNotNull(source.getModuleSubtraction(), numberSystem))
                .build();
    }

    private String mapIfNotNull(BigInteger number, int numberSystem) {
        return Optional.ofNullable(number)
                .map(n -> n.toString(numberSystem))
                .orElse(null);
    }
}
