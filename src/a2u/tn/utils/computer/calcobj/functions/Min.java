package a2u.tn.utils.computer.calcobj.functions;

import a2u.tn.utils.computer.calculator.Calculator;
import a2u.tn.utils.computer.calculator.Function;
import a2u.tn.utils.computer.calculator.Type;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Detect minimal value in path
 */
public class Min extends Function {

  private static List<Parameter> parameters;

  public Min(Calculator calculator) {
    super(calculator);
    parameters = new ArrayList<>();
    parameters.add(new Parameter(Object.class, "data"));
  }

  @Override
  public String getName() {
    return "min";
  }

  @Override
  public List<Parameter> getParameters() {
    return parameters;
  }

  @Override
  public Object run(Map<String, Object> paramValues, Object row, int rowIndex, Collection<Object> allRows) {
    Object data = paramValues.get("data");
    if (data == null) {
      return null;
    }
    if (data instanceof Collection) {
      Collection list = (Collection) data;
      Object min = null;
      for (Object obj : list) {
        if (obj == null) {
          continue;
        }
        if (min == null) {
          min = obj;
        }
        Type type = calculator.getType(min.getClass());
        if (type.less(obj, min)) {
          min = obj;
        }
      }
      return min;
    }
    return data;

  }
}
