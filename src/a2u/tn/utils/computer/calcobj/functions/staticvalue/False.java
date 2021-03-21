package a2u.tn.utils.computer.calcobj.functions.staticvalue;

import a2u.tn.utils.computer.calculator.CalcContext;
import a2u.tn.utils.computer.calculator.Calculator;
import a2u.tn.utils.computer.calculator.Function;
import a2u.tn.utils.computer.formula.FormulaPart;

import java.util.List;
import java.util.Map;

/**
 * Return boolean value 'false'
 */
public class False extends Function {

  @Override
  public Object run(Calculator calculator, List<FormulaPart> params, Map<String, Object> paramValues, CalcContext ctx) {
    return false;
  }
}
