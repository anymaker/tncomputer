package a2u.tn.utils.computer.calcobj.functions.datetime;

import a2u.tn.utils.computer.calculator.Calculator;
import a2u.tn.utils.computer.calculator.Function;
import a2u.tn.utils.computer.formula.FormulaPart;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Returns the current datetime
 */
public class SysTime extends Function {

  public SysTime(Calculator calculator) {
    super(calculator);
  }

  @Override
  public Object run(List<FormulaPart> params, Object row, int rowIndex, Collection<Object> allRows) {
    return new Date();
  }

}
