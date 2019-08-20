package ua.kpi.ipt.srom.srom_check.services;

import ua.kpi.ipt.srom.srom_check.models.LongArithmeticModel;

import java.math.BigInteger;


public interface CalculationService {
    BigInteger add(BigInteger a,BigInteger b);

    BigInteger subtract(BigInteger a,BigInteger b);

    BigInteger multiply(BigInteger a,BigInteger b);

    BigInteger square(BigInteger a);

    BigInteger divide(BigInteger a,BigInteger b);

    BigInteger pow (BigInteger a,BigInteger b);

    BigInteger module(BigInteger a, BigInteger b);

    LongArithmeticModel calculateAll(LongArithmeticModel model);
}
