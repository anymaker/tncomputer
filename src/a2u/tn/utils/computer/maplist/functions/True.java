package a2u.tn.utils.computer.maplist.functions;

import a2u.tn.utils.computer.calculator.Calculator;
import a2u.tn.utils.computer.calculator.Function;

import java.util.Collection;
import java.util.Map;

/**
 * Return boolean value 'true'
 */
public class True extends Function {

  public True(Calculator calculator) {
    super(calculator);
  }

  @Override
  public String getName() {
    return "true";
  }

  @Override
  public Object run(Map<String, Object> paramValues, Object row, int rowIndex, Collection<Object> allRows) {
    return true;
  }
}
