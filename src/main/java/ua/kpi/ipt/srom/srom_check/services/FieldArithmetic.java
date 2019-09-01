package ua.kpi.ipt.srom.srom_check.services;

import java.math.BigInteger;

public interface FieldArithmetic {

    BigInteger findNeutralAdditionElement();
    BigInteger findNeutralMultiplicationElement();
    BigInteger adding(BigInteger a, BigInteger b);
    BigInteger multiplication(BigInteger a, BigInteger b);
    BigInteger trace(BigInteger el);
    BigInteger square(BigInteger el);
    BigInteger pow(BigInteger a,BigInteger b);
    BigInteger reverse(BigInteger el);
}
