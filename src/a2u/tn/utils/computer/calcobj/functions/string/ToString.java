package a2u.tn.utils.computer.calcobj.functions.string;

import a2u.tn.utils.computer.calculator.CalcContext;
import a2u.tn.utils.computer.calculator.Calculator;
import a2u.tn.utils.computer.calculator.Function;
import a2u.tn.utils.computer.formula.FormulaPart;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Convert any value to String
 */
public class ToString extends Function {

  @Override
  protected List<Parameter<?>> initParameters() {
    List<Parameter<?>> parameters = new ArrayList<>();
    parameters.add(new Parameter<>(Object.class, "object"));
    return parameters;
  }

  @Override
  public Object run(Calculator calculator, List<FormulaPart> params, Map<String, Object> paramValues, CalcContext ctx) {
    Object object_ = paramValues.get("object");
    String object = calculator.toType(String.class, object_);
    return object;
  }
}
