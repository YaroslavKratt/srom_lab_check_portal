package ua.kpi.srom.dto;

import jakarta.validation.constraints.NotEmpty;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.kpi.srom.models.FiniteFieldsVariants;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FiniteFieldDto {

  public static final Map<String, String> variantsToPolynomials =
      FiniteFieldsVariants.VARIANT_TO_POLYNOM;

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
