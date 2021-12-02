package ua.kpi.srom.converters;

import cc.redberry.rings.poly.univar.UnivariatePolynomialZp64;
import java.math.BigInteger;
import java.util.Arrays;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ua.kpi.srom.dto.FiniteFieldDto;
import ua.kpi.srom.models.FiniteFieldModel;

@Component
public class FiniteFieldModelToDto implements Converter<FiniteFieldModel, FiniteFieldDto> {

  @Override
  public FiniteFieldDto convert(FiniteFieldModel source) {
    int numberSystem = source.getNumberSystem().getValue();
    return FiniteFieldDto.builder()
        .firstNumber(polynomialToStringNumber(source.getFirstNumber(), numberSystem))
        .secondNumber(polynomialToStringNumber(source.getSecondNumber(), numberSystem))
        .thirdNumber(source.getThirdNumber().toString(numberSystem))
        .sum(polynomialToStringNumber(source.getSum(), numberSystem))
        .neutralByMultiplication(
            polynomialToStringNumber(source.getGetNeutralByMultiplication(), numberSystem))
        .multiplication(polynomialToStringNumber(source.getMultiplication(), numberSystem))
        .numberSystem(source.getNumberSystem().toString())
        .neutralByAdding(polynomialToStringNumber(source.getNeutralByAdding(), numberSystem))
        .pow(polynomialToStringNumber(source.getPow(), numberSystem))
        .reverse(polynomialToStringNumber(source.getReverse(), numberSystem))
        .square(polynomialToStringNumber(source.getSquare(), numberSystem))
        .trace(polynomialToStringNumber(source.getTrace(), numberSystem))
        .build();
  }

  private String polynomialToStringNumber(UnivariatePolynomialZp64 polynomial, int numberSystem) {
    long[] polynomialArr = polynomial.stream().toArray();
    ArrayUtils.reverse(polynomialArr);
    StringBuilder builder = new StringBuilder();
    Arrays.stream(polynomialArr).forEach(builder::append);

    return new BigInteger(builder.toString(), 2).toString(numberSystem);
  }
}
