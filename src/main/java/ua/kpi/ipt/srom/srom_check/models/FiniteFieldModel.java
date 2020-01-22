package ua.kpi.ipt.srom.srom_check.models;

import cc.redberry.rings.bigint.BigInteger;
import cc.redberry.rings.poly.FiniteField;
import cc.redberry.rings.poly.univar.UnivariatePolynomialZp64;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FiniteFieldModel {
    @NotEmpty
    private UnivariatePolynomialZp64 firstNumber;

    @NotEmpty
    private UnivariatePolynomialZp64 secondNumber;

    @NotEmpty
    private BigInteger thirdNumber;
    private FiniteField<UnivariatePolynomialZp64> finiteField;
    private NumberSystem numberSystem;
    private UnivariatePolynomialZp64 neutralByAdding;
    private UnivariatePolynomialZp64 getNeutralByMultiplication;
    private UnivariatePolynomialZp64 sum;
    private UnivariatePolynomialZp64 multiplication;
    private UnivariatePolynomialZp64 trace;
    private UnivariatePolynomialZp64 square;
    private UnivariatePolynomialZp64 pow;
    private UnivariatePolynomialZp64 reverse;


}
