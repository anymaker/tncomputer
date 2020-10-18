package a2u.tn.utils.computer.calcobj.functions.datetime;

import a2u.tn.utils.computer.calculator.Calculator;

import java.time.LocalDateTime;

/**
 * Adds or subtracts a specified number of hours
 */
public class AddHours extends DateCalculationAbstract {

  public AddHours(Calculator calculator) {
    super(calculator);
  }

  @Override
  protected String getSecondParamName() {
    return "hours";
  }

  @Override
  protected LocalDateTime calc(LocalDateTime current, long item) {
    return current.plusHours(item);
  }

}
