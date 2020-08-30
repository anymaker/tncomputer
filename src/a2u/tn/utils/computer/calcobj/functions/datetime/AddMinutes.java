package a2u.tn.utils.computer.calcobj.functions.datetime;

import a2u.tn.utils.computer.calculator.Calculator;

import java.time.LocalDateTime;

public class AddMinutes extends DateCalculationAbstract {

  public AddMinutes(Calculator calculator) {
    super(calculator);
  }

  @Override
  protected String getSecondParamName() {
    return "minutes";
  }

  @Override
  protected LocalDateTime calc(LocalDateTime current, long item) {
    return current.plusMinutes(item);
  }

}
