package a2u.tn.utils.computer.calcobj.functions;

import a2u.tn.utils.computer.calculator.Calculator;
import a2u.tn.utils.computer.calculator.Function;

import java.util.Collection;
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
  public Object run(Map<String, Object> paramValues, Object row, int rowIndex, Collection<Object> allRows) {
    return null;
  }
}
