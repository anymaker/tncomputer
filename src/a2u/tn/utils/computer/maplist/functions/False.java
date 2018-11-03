package a2u.tn.utils.computer.maplist.functions;

import a2u.tn.utils.computer.calculator.Calculator;
import a2u.tn.utils.computer.calculator.Function;

import java.util.Collection;
import java.util.Map;

/**
 * Return boolean value 'false'
 */
public class False extends Function {

  public False(Calculator calculator) {
    super(calculator);
  }

  @Override
  public String getName() {
    return "false";
  }


  @Override
  public Object run(Map<String, Object> paramValues, Object row, int rowIndex, Collection<Object> allRows) {
    return false;
  }
}
