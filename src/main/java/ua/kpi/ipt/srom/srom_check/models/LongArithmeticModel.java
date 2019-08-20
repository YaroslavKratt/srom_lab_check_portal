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
    private BigInteger sum;
    private BigInteger substraction;
    private BigInteger division;
    private BigInteger multiplication;
    private BigInteger pow;
    private BigInteger square;
    private NumberSystem numberSystem;
}
