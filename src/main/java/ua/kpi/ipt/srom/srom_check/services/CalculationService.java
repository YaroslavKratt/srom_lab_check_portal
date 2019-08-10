package ua.kpi.ipt.srom.srom_check.services;

import java.math.BigInteger;
import java.util.Map;


public interface CalculationService {
    BigInteger add(BigInteger a,BigInteger b);

    BigInteger subtract(BigInteger a,BigInteger b);

    BigInteger multiply(BigInteger a,BigInteger b);

    BigInteger square(BigInteger a);

    BigInteger divide(BigInteger a,BigInteger b);

    BigInteger pow (BigInteger a,BigInteger b);

    BigInteger modulo(BigInteger a, BigInteger b);

    Map<String,BigInteger> calculateAll();
}
