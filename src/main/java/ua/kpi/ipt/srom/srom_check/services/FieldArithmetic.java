package ua.kpi.ipt.srom.srom_check.services;

import cc.redberry.rings.bigint.BigInteger;
import cc.redberry.rings.poly.univar.UnivariatePolynomialZp64;

public interface FieldArithmetic {

    UnivariatePolynomialZp64 findNeutralAdditionElement();

    UnivariatePolynomialZp64 findNeutralMultiplicationElement();

    UnivariatePolynomialZp64 adding(UnivariatePolynomialZp64 a, UnivariatePolynomialZp64 b);

    UnivariatePolynomialZp64 multiplication(UnivariatePolynomialZp64 a, UnivariatePolynomialZp64 b);

    UnivariatePolynomialZp64 trace(UnivariatePolynomialZp64 el);

    UnivariatePolynomialZp64 square(UnivariatePolynomialZp64 el);

    UnivariatePolynomialZp64 pow(UnivariatePolynomialZp64 a, BigInteger b);

    UnivariatePolynomialZp64 reverse(UnivariatePolynomialZp64 el);

    void calculateAll();
}
