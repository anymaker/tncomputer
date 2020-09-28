package a2u.tn.utils.computer.calcobj.functions.datetime;

import a2u.tn.utils.computer.calculator.Calculator;

import java.time.LocalDateTime;

public class AddDays extends DateCalculationAbstract {

  public AddDays(Calculator calculator) {
    super(calculator);
  }

  @Override
  protected String getSecondParamName() {
    return "days";
  }

  @Override
  protected LocalDateTime calc(LocalDateTime current, long item) {
    return current.plusDays(item);
  }

}