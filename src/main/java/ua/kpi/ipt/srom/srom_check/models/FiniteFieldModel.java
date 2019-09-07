package ua.kpi.ipt.srom.srom_check.models;

import cc.redberry.rings.bigint.BigInteger;
import lombok.*;

import javax.validation.constraints.NotEmpty;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FiniteFieldModel {
    @NotEmpty
    BigInteger firstNumber;
    @NotEmpty
    BigInteger secondNumber;
    @NotEmpty
    BigInteger thirdNumber;
    NumberSystem numberSystem;
    BigInteger neutralByAdding;
    BigInteger getNeutralByMultiplication;
    BigInteger sum;
    BigInteger multiplication;
    BigInteger trace;
    BigInteger square;
    BigInteger pow;
    BigInteger reverse;

}
