package a2u.tn.utils.computer.calcobj.functions.string;

import a2u.tn.utils.computer.calculator.CalcContext;
import a2u.tn.utils.computer.calculator.Calculator;
import a2u.tn.utils.computer.calculator.Function;
import a2u.tn.utils.computer.formula.FormulaPart;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Adds characters to the left of the string until the string reaches the specified length.
 */
public class PadLeft extends Function {

  @Override
  protected List<Function.Parameter> initParameters() {
    List<Function.Parameter> parameters = new ArrayList<>();
    parameters.add(new Function.Parameter(String.class, "string"));
    parameters.add(new Function.Parameter(int.class,    "quantity"));
    parameters.add(new Function.Parameter(String.class, "sample", false, ""));
    return parameters;
  }

  @Override
  public Object run(Calculator calculator, List<FormulaPart> params, Map<String, Object> paramValues, CalcContext ctx) {
    String string = (String) paramValues.get("string");
    int length    = (int)    paramValues.get("quantity");
    String sample = (String) paramValues.get("sample");

    if (string == null) {
      string = "";
    }
    if (length == 0) {
      return string;
    }
    if (sample == null || sample.length() == 0) {
      return string;
    }

    StringBuilder b = new StringBuilder(string);
    while (b.length() < length) {
      b.insert(0, sample);
    }

    return b.toString();
  }

}