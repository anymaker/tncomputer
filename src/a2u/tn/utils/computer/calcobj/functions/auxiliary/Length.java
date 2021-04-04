package a2u.tn.utils.computer.calcobj.functions.auxiliary;

import a2u.tn.utils.computer.calculator.CalcContext;
import a2u.tn.utils.computer.calculator.Calculator;
import a2u.tn.utils.computer.calculator.Function;
import a2u.tn.utils.computer.formula.FormulaPart;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Returns the length of the object, or -1 if not applicable
 */
public class Length extends Function {

  @Override
  protected List<Parameter<?>> initParameters() {
    List<Parameter<?>> parameters = new ArrayList<>();
    parameters.add(new Parameter<>(Object.class, "value"));
    return parameters;
  }

  @Override
  public Object run(Calculator calculator, List<FormulaPart> params, Map<String, Object> paramValues, CalcContext ctx) {
    Object value = paramValues.get("value");

    if (value == null) {
      return 0;
    }
    else if (value instanceof CharSequence) {
      return ((CharSequence) value).length();
    }
    else if (value instanceof Number) {
      return String.valueOf(value).length();
    }
    else if (value instanceof Map) {
      return ((Map) value).size();
    }
    else if (value instanceof Collection) {
      return ((Collection) value).size();
    }

    return -1;
  }

}