package ua.kpi.ipt.srom.srom_check.converters;

import cc.redberry.rings.bigint.BigInteger;
import cc.redberry.rings.poly.univar.UnivariatePolynomialZp64;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ua.kpi.ipt.srom.srom_check.dto.FiniteFieldDto;
import ua.kpi.ipt.srom.srom_check.models.FiniteFieldModel;
import ua.kpi.ipt.srom.srom_check.models.FiniteFieldsVariants;
import ua.kpi.ipt.srom.srom_check.models.NumberSystem;


import java.util.Arrays;

import static java.util.Optional.ofNullable;

@Component
public class FiniteFieldDtoToModel implements Converter<FiniteFieldDto, FiniteFieldModel> {

    @Override
    public FiniteFieldModel convert(FiniteFieldDto source) {
        NumberSystem numberSystem = NumberSystem.valueOf(source.getNumberSystem());

        return FiniteFieldModel.builder()
                .firstNumber(createPolynomial(new BigInteger(source.getFirstNumber(), numberSystem.getValue()).toString(2)))
                .secondNumber(createPolynomial(new BigInteger(source.getSecondNumber(), numberSystem.getValue()).toString(2)))
                .thirdNumber(ofNullable(source.getThirdNumber())
                        .filter(item -> !item.isEmpty())
                        .map(m -> new BigInteger(m, numberSystem.getValue()))
                        .orElse(null)
                )
                .finiteField(FiniteFieldsVariants.getFieldBy(source.getVariant()))
                .numberSystem(numberSystem)
                .build();
    }
    private UnivariatePolynomialZp64 createPolynomial(String binaryString) {
        long [] polynomialArr = Arrays.stream(binaryString.split(""))
                .mapToLong(n-> Long.parseLong(n,2))
                .toArray();
        ArrayUtils.reverse(polynomialArr);
        return  UnivariatePolynomialZp64.create(2,polynomialArr);

    }
}