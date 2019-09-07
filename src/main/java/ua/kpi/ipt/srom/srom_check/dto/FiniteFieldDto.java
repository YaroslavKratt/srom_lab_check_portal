package ua.kpi.ipt.srom.srom_check.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FiniteFieldDto {
    @NotEmpty
    String firstNumber;
    @NotEmpty
    String secondNumber;
    @NotEmpty
    String thirdNumber;
    String numberSystem;
    String neutralByAdding;
    String neutralByMultiplication;
    String sum;
    String multiplication;
    String trace;
    String square;
    String pow;
    String reverse;
}
