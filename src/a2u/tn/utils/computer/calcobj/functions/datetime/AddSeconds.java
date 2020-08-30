package a2u.tn.utils.computer.calcobj.functions.datetime;

import a2u.tn.utils.computer.calculator.Calculator;

import java.time.LocalDateTime;

public class AddSeconds extends DateCalculationAbstract {

  public AddSeconds(Calculator calculator) {
    super(calculator);
  }

  @Override
  protected String getSecondParamName() {
    return "seconds";
  }

  @Override
  protected LocalDateTime calc(LocalDateTime current, long item) {
    return current.plusSeconds(item);
  }

}
