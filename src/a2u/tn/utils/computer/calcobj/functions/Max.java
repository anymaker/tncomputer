package a2u.tn.utils.computer.calcobj.functions;

import a2u.tn.utils.computer.calculator.Calculator;
import a2u.tn.utils.computer.calculator.Function;
import a2u.tn.utils.computer.calculator.Type;
import a2u.tn.utils.computer.formula.FormulaPart;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Detect max value
 */
public class Max extends Function {

  public Max(Calculator calculator) {
    super(calculator);
  }

  @Override
  protected List<Parameter> initParameters() {
    List<Parameter> parameters = new ArrayList<>();
    parameters.add(new Parameter(Object.class, "data"));
    return parameters;
  }

  @Override
  public Object run(List<FormulaPart> params, Object row, int rowIndex, Collection<Object> allRows) {
    Object data = calculator.calcArgument(params.get(0),  row, rowIndex, allRows);
    if (data == null) {
      return null;
    }
    if (data instanceof Collection) {
      Collection list = (Collection) data;
      Object max = null;
      for (Object obj : list) {
        if (obj == null) {
          continue;
        }
        if (max == null) {
          max = obj;
        }
        Type type = calculator.getType(max.getClass());
        if (type.great(obj, max)) {
          max = obj;
        }
      }
      return max;
    }
    return data;

  }
}
