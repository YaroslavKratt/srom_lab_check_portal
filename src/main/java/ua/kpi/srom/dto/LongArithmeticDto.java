package ua.kpi.srom.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
