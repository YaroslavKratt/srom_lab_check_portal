package ua.kpi.srom.converters;

import static java.util.Optional.ofNullable;

import cc.redberry.rings.bigint.BigInteger;
import cc.redberry.rings.poly.univar.UnivariatePolynomialZp64;
import java.util.Arrays;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ua.kpi.srom.dto.FiniteFieldDto;
import ua.kpi.srom.models.FiniteFieldModel;
import ua.kpi.srom.models.FiniteFieldsVariants;
import ua.kpi.srom.models.NumberSystem;

@Component
public class FiniteFieldDtoToModel implements Converter<FiniteFieldDto, FiniteFieldModel> {

  @Override
  public FiniteFieldModel convert(FiniteFieldDto source) {
    NumberSystem numberSystem = NumberSystem.valueOf(source.getNumberSystem());

    return FiniteFieldModel.builder()
        .firstNumber(
            createPolynomial(
                new BigInteger(source.getFirstNumber(), numberSystem.getValue()).toString(2)))
        .secondNumber(
            createPolynomial(
                new BigInteger(source.getSecondNumber(), numberSystem.getValue()).toString(2)))
        .thirdNumber(
            ofNullable(source.getThirdNumber())
                .filter(item -> !item.isEmpty())
                .map(m -> new BigInteger(m, numberSystem.getValue()))
                .orElse(null))
        .finiteField(FiniteFieldsVariants.getFieldBy(source.getVariant()))
        .numberSystem(numberSystem)
        .build();
  }

  private UnivariatePolynomialZp64 createPolynomial(String binaryString) {
    long[] polynomialArr =
        Arrays.stream(binaryString.split("")).mapToLong(n -> Long.parseLong(n, 2)).toArray();
    ArrayUtils.reverse(polynomialArr);
    return UnivariatePolynomialZp64.create(2, polynomialArr);
  }
}
