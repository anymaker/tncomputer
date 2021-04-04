package a2u.tn.utils.computer.calcobj.functions.string;

import a2u.tn.utils.computer.StringUtil;
import a2u.tn.utils.computer.calculator.CalcContext;
import a2u.tn.utils.computer.calculator.Calculator;
import a2u.tn.utils.computer.calculator.Function;
import a2u.tn.utils.computer.formula.FormulaPart;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Remove leading whitespace
 */
public class LTrim extends Function {

  @Override
  protected List<Parameter<?>> initParameters() {
    List<Parameter<?>> parameters = new ArrayList<>();
    parameters.add(new Parameter<>(String.class, "string"));
    return parameters;
  }

  @Override
  public Object run(Calculator calculator, List<FormulaPart> params, Map<String, Object> paramValues, CalcContext ctx) {
    String string = (String) paramValues.get("string");

    if (string == null) {
      return null;
    }

    return StringUtil.LTrim(string);
  }

}