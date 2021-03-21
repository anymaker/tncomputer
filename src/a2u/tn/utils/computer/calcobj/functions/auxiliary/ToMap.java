package a2u.tn.utils.computer.calcobj.functions.auxiliary;

import a2u.tn.utils.computer.calculator.CalcContext;
import a2u.tn.utils.computer.calculator.Calculator;
import a2u.tn.utils.computer.calculator.Function;
import a2u.tn.utils.computer.formula.FormulaPart;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Returns Map from given parameters
 */
public class ToMap extends Function {

  @Override
  protected List<Parameter> initParameters() {
    List<Parameter> parameters = new ArrayList<>();
    return parameters;
  }



  @Override
  public Object run(Calculator calculator, List<FormulaPart> params, Map<String, Object> paramValues, CalcContext ctx) {

    Map<Object, Object> map = new LinkedHashMap<>();

    for (FormulaPart param : params) {
      Object objs = calculator.calcArgument(param, ctx);
      List values = calculator.toType(List.class, objs);
      if (values != null) {
        if (values.size() != 2) {
          throw new IllegalArgumentException("Invalid params. Example tomap(('key1', .value1),('key2', .value2))");
        }
        map.put(values.get(0), values.get(1));
      }

    }

    return map;
  }
}
