package ua.kpi.ipt.srom.srom_check.dto;

import lombok.*;
import ua.kpi.ipt.srom.srom_check.models.FiniteFieldsVariants;

import javax.validation.constraints.NotEmpty;
import java.util.Map;

import static ua.kpi.ipt.srom.srom_check.models.FiniteFieldsVariants.VARIANT_TO_POLYNOM;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FiniteFieldDto {
    public static final Map<String, String> variantsToPolynomials = VARIANT_TO_POLYNOM;
    @NotEmpty(message = "{error.empty.field}")
    private String firstNumber;
    @NotEmpty(message = "{error.empty.field}")
    private String secondNumber;
    @NotEmpty(message = "{error.empty.field}")
    private String thirdNumber;
    private String numberSystem;
    private String variant;
    private String neutralByAdding;
    private String neutralByMultiplication;
    private String sum;
    private String basis;
    private String multiplication;
    private String trace;
    private String square;
    private String pow;
    private String reverse;
}
