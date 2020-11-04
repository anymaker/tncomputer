package a2u.tn.utils.computer.calcobj.functions.datetime;

import a2u.tn.utils.computer.calculator.Calculator;
import a2u.tn.utils.computer.calculator.Function;
import a2u.tn.utils.computer.formula.FormulaPart;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Returns the current date without time, that is, the time will always be 00:00:00
 */
public class SysDate extends Function {

  @Override
  public Object run(Calculator calculator, List<FormulaPart> params, Object row, int rowIndex, Collection<Object> allRows) {
    LocalDate ld = LocalDate.now();
    Date result = java.sql.Date.valueOf(ld);
    return result;
  }

}
