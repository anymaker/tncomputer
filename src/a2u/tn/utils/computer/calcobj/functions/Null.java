package a2u.tn.utils.computer.calcobj.functions;

import a2u.tn.utils.computer.calculator.Calculator;
import a2u.tn.utils.computer.calculator.Function;
import a2u.tn.utils.computer.formula.FormulaPart;

import java.util.Collection;
import java.util.List;
import java.util.Map;


/**
 * Return null value
 */
public class Null extends Function {

  public Null(Calculator calculator) {
    super(calculator);
  }

  @Override
  public String getName() {
    return "null";
  }

  @Override
  public Object run(Map<String, FormulaPart> namedParams, List<FormulaPart> otherParams, Object row, int rowIndex, Collection<Object> allRows) {
    return null;
  }
}
