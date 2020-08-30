package a2u.tn.utils.computer.calcobj.functions.datetime;

import a2u.tn.utils.computer.calculator.Calculator;

import java.time.LocalDateTime;

public class AddMonths extends DateCalculationAbstract {

  public AddMonths(Calculator calculator) {
    super(calculator);
  }

  @Override
  protected String getSecondParamName() {
    return "months";
  }

  @Override
  protected LocalDateTime calc(LocalDateTime current, long item) {
    return current.plusMonths(item);
  }

}
