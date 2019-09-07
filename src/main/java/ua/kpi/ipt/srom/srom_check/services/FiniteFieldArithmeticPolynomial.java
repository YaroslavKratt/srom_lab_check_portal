package ua.kpi.ipt.srom.srom_check.services;

import cc.redberry.rings.bigint.BigInteger;
import cc.redberry.rings.poly.FiniteField;
import cc.redberry.rings.poly.univar.UnivariatePolynomialZp64;
import org.springframework.stereotype.Component;

@Component
public class FiniteFieldArithmeticPolynomial implements FieldArithmetic {
    private FiniteField<UnivariatePolynomialZp64> finiteField;

    /*public FiniteFieldArithmeticPolynomial(FiniteField<UnivariatePolynomialZp64> finiteField) {
        this.finiteField = finiteField;
    }*/
    @Override
    public UnivariatePolynomialZp64 findNeutralAdditionElement() {
        return null;
    }

    @Override
    public UnivariatePolynomialZp64 findNeutralMultiplicationElement() {
        return null;
    }

    @Override
    public UnivariatePolynomialZp64 adding(UnivariatePolynomialZp64 a, UnivariatePolynomialZp64 b) {
        return finiteField.add(a,b);
    }

    @Override
    public UnivariatePolynomialZp64 multiplication(UnivariatePolynomialZp64 a, UnivariatePolynomialZp64 b) {
        return finiteField.multiply(a,b);
    }

    @Override
    public UnivariatePolynomialZp64 trace(UnivariatePolynomialZp64 el) {

        return finiteField.trace(el);
    }

    @Override
    public UnivariatePolynomialZp64 square(UnivariatePolynomialZp64 el) {
        return finiteField.pow(el,2);
    }

    @Override
    public UnivariatePolynomialZp64 pow(UnivariatePolynomialZp64 a, BigInteger b) {
        return finiteField.pow(a, b);
    }

    @Override
    public UnivariatePolynomialZp64 reverse(UnivariatePolynomialZp64 el) {
       return  el.reverse();
    }

    @Override
    public void calculateAll() {


    }
}