package ua.kpi.srom.models;

import java.math.BigInteger;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LongArithmeticModel {
    private BigInteger firstNumber;
    private BigInteger secondNumber;
    private BigInteger module;
    private BigInteger moduleRes;
    private BigInteger sum;
    private BigInteger subtraction;
    private BigInteger division;
    private BigInteger multiplication;
    private BigInteger pow;
    private BigInteger square;
    private BigInteger gcd;
    private BigInteger lcm;
    private BigInteger moduleAdd;
    private BigInteger moduleSubtraction;
    private BigInteger moduleMult;
    private BigInteger moduleSquare;
    private BigInteger modulePow;
    private NumberSystem numberSystem;
}
