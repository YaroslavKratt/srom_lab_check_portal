package ua.kpi.srom.services;

import cc.redberry.rings.bigint.BigInteger;
import cc.redberry.rings.poly.univar.UnivariatePolynomialZp64;
import ua.kpi.srom.models.FiniteFieldModel;

public interface FieldArithmetic {

    UnivariatePolynomialZp64 findNeutralAdditionElement();

    UnivariatePolynomialZp64 findNeutralMultiplicationElement();

    UnivariatePolynomialZp64 sum(UnivariatePolynomialZp64 a, UnivariatePolynomialZp64 b);

    UnivariatePolynomialZp64 multiplication(UnivariatePolynomialZp64 a, UnivariatePolynomialZp64 b);

    UnivariatePolynomialZp64 trace(UnivariatePolynomialZp64 el);

    UnivariatePolynomialZp64 square(UnivariatePolynomialZp64 el);

    UnivariatePolynomialZp64 pow(UnivariatePolynomialZp64 a, BigInteger b);

    UnivariatePolynomialZp64 reverse(UnivariatePolynomialZp64 el);

    FiniteFieldModel calculateAll(FiniteFieldModel model);
}
