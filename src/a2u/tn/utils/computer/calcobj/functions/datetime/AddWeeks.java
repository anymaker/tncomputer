package a2u.tn.utils.computer.calcobj.functions.datetime;

import a2u.tn.utils.computer.calculator.Calculator;

import java.time.LocalDateTime;

public class AddWeeks extends DateCalculationAbstract {

  public AddWeeks(Calculator calculator) {
    super(calculator);
  }

  @Override
  protected String getSecondParamName() {
    return "weeks";
  }

  @Override
  protected LocalDateTime calc(LocalDateTime current, long item) {
    return current.plusWeeks(item);
  }

}
