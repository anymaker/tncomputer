package a2u.tn.utils.computer.calcobj.functions.datetime;

import a2u.tn.utils.computer.calculator.Calculator;

import java.time.LocalDateTime;

/**
 * Adds or subtracts a specified number of months
 */
public class AddMonths extends DateCalculationAbstract {

  @Override
  protected String getSecondParamName() {
    return "months";
  }

  @Override
  protected LocalDateTime calc(LocalDateTime current, long item) {
    return current.plusMonths(item);
  }

}
