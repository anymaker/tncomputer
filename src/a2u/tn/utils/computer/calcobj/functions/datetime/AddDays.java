package a2u.tn.utils.computer.calcobj.functions.datetime;

import a2u.tn.utils.computer.calculator.Calculator;

import java.time.LocalDateTime;

/**
 * Adds or subtracts the specified number of days
 */
public class AddDays extends DateCalculationAbstract {

  @Override
  protected String getSecondParamName() {
    return "days";
  }

  @Override
  protected LocalDateTime calc(LocalDateTime current, long item) {
    return current.plusDays(item);
  }

}
