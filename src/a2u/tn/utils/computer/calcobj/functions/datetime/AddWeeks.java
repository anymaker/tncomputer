package a2u.tn.utils.computer.calcobj.functions.datetime;

import a2u.tn.utils.computer.calculator.Calculator;

import java.time.LocalDateTime;

/**
 * Adds or subtracts a specified number of weeks
 */
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
