package ua.kpi.srom.converters;

import static java.util.Optional.*;

import java.math.BigInteger;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ua.kpi.srom.dto.LongArithmeticDto;
import ua.kpi.srom.models.LongArithmeticModel;
import ua.kpi.srom.models.NumberSystem;

@Component
public class LongArithmeticDtoToModelConverter
    implements Converter<LongArithmeticDto, LongArithmeticModel> {

  @Override
  public LongArithmeticModel convert(LongArithmeticDto source) {
    NumberSystem numberSystem = NumberSystem.valueOf(source.getNumberSystem());

    return LongArithmeticModel.builder()
        .firstNumber(new BigInteger(source.getFirstNumber(), numberSystem.getValue()))
        .secondNumber(new BigInteger(source.getSecondNumber(), numberSystem.getValue()))
        .module(
            ofNullable(source.getModule())
                .filter(item -> !item.isEmpty())
                .map(m -> new BigInteger(m, numberSystem.getValue()))
                .orElse(null))
        .numberSystem(numberSystem)
        .build();
  }
}
