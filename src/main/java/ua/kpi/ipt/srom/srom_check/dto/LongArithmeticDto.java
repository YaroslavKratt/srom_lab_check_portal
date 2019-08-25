package ua.kpi.ipt.srom.srom_check.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LongArithmeticDto {
    @NotEmpty(message = "{error.empty.field}")
    private String firstNumber;
    @NotEmpty(message = "{error.empty.field}")
    private String secondNumber;
    private String module;
    @NotEmpty(message = "{error.empty.field}")
    private String numberSystem;

    private String sum;
    private String subtraction;
    private String division;
    private String moduleRes;
    private String multiplication;
    private String pow;
    private String square;
    private String gcd;
    private String lcm;
    private String moduleAdd;
    private String moduleSubtractioc;
    private String moduleMult;
    private String moduleSquare;
    private String modulePow;
}