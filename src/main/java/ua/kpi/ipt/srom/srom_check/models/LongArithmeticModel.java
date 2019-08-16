package ua.kpi.ipt.srom.srom_check.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@Builder
public class LongArithmeticModel {
    @NotEmpty(message = "{error.empty.field}")
    private String firstNumber;
    @NotEmpty(message = "{error.empty.field}")
    private String secondNumber;
    private String module;
    @NotNull
    private String numberSystem;

    public LongArithmeticModel() {

    }
}
