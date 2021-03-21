package a2u.tn.utils.computer.calcobj.functions.forcollections;

import a2u.tn.utils.computer.calculator.CalcContext;
import a2u.tn.utils.computer.calculator.Calculator;
import a2u.tn.utils.computer.calculator.Function;
import a2u.tn.utils.computer.calculator.Type;
import a2u.tn.utils.computer.formula.FormulaPart;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Detect max value
 */
public class Max extends Function {

  @Override
  protected List<Parameter> initParameters() {
    List<Parameter> parameters = new ArrayList<>();
    parameters.add(new Parameter(Object.class, "collection"));
    return parameters;
  }

  @Override
  public Object run(Calculator calculator, List<FormulaPart> params, Map<String, Object> paramValues, CalcContext ctx) {
    Collection<?> incomingCollection;

    if (params.size() == 1) {
      Object data = paramValues.get("collection");
      if (data instanceof Collection) {
        incomingCollection = (Collection) data;
      }
      else {
        incomingCollection = Collections.singletonList(data);
      }
    }
    else {
      List<Object> list = new ArrayList<>();
      for (FormulaPart param : params) {
        Object data = calculator.calcArgument(param,  ctx);
        list.add(data);
      }
      incomingCollection = list;
    }

    Object max = null;
    for (Object obj : incomingCollection) {
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

}
