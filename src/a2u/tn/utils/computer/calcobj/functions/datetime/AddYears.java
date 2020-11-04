package a2u.tn.utils.computer.calcobj.functions.datetime;

import a2u.tn.utils.computer.calculator.Calculator;

import java.time.LocalDateTime;

/**
 * Adds or subtracts a specified number of years
 */
public class AddYears extends DateCalculationAbstract {

  @Override
  protected String getSecondParamName() {
    return "years";
  }

  @Override
  protected LocalDateTime calc(LocalDateTime current, long item) {
    return current.plusYears(item);
  }

}
