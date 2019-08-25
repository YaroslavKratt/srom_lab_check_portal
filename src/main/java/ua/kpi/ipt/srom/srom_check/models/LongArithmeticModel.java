package ua.kpi.ipt.srom.srom_check.models;

import lombok.*;

import java.math.BigInteger;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
