package ua.kpi.srom.services;

import cc.redberry.rings.bigint.BigInteger;
import cc.redberry.rings.poly.FiniteField;
import cc.redberry.rings.poly.univar.UnivariatePolynomialZp64;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ua.kpi.srom.models.FiniteFieldModel;
@Slf4j
@Component
public class FiniteFieldArithmeticPolynomial implements FieldArithmetic {
    private FiniteField<UnivariatePolynomialZp64> finiteField;


    @Override
    public UnivariatePolynomialZp64 findNeutralAdditionElement() {
        return finiteField.getOne();
    }

    @Override
    public UnivariatePolynomialZp64 findNeutralMultiplicationElement() {
        return finiteField.getOne();
    }

    @Override
    public UnivariatePolynomialZp64 sum(UnivariatePolynomialZp64 a, UnivariatePolynomialZp64 b) {
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
       return  finiteField.reciprocal(el.clone());
    }

    @Override
    public FiniteFieldModel calculateAll(FiniteFieldModel model) {
        finiteField = model.getFiniteField();
        UnivariatePolynomialZp64 a = model.getFirstNumber();
        UnivariatePolynomialZp64 b = model.getSecondNumber();
        BigInteger c = model.getThirdNumber();

        model.setMultiplication(multiplication(a,b));
        model.setSquare(square(a));
        model.setPow(pow(a,c));
        model.setReverse(reverse(a));
        model.setSum(sum(a,b));
        model.setTrace(trace(a));
        model.setGetNeutralByMultiplication(findNeutralMultiplicationElement());
        model.setNeutralByAdding(findNeutralAdditionElement());

        return   model;
    }
}
