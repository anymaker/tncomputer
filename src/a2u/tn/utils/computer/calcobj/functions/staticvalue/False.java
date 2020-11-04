package a2u.tn.utils.computer.calcobj.functions.staticvalue;

import a2u.tn.utils.computer.calculator.Calculator;
import a2u.tn.utils.computer.calculator.Function;
import a2u.tn.utils.computer.formula.FormulaPart;

import java.util.Collection;
import java.util.List;

/**
 * Return boolean value 'false'
 */
public class False extends Function {

  @Override
  public Object run(Calculator calculator, List<FormulaPart> params, Object row, int rowIndex, Collection<Object> allRows) {
    return false;
  }
}
