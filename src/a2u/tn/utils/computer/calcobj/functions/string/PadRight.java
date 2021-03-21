package a2u.tn.utils.computer.calcobj.functions.string;

import a2u.tn.utils.computer.calculator.CalcContext;
import a2u.tn.utils.computer.calculator.Calculator;
import a2u.tn.utils.computer.calculator.Function;
import a2u.tn.utils.computer.formula.FormulaPart;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Adds characters to the right of the string until the string reaches the specified length.
 */
public class PadRight extends Function {

  @Override
  protected List<Parameter> initParameters() {
    List<Function.Parameter> parameters = new ArrayList<>();
    parameters.add(new Function.Parameter(String.class, "string"));
    parameters.add(new Function.Parameter(int.class,    "length"));
    parameters.add(new Function.Parameter(String.class, "sample"));
    return parameters;
  }

  @Override
  public Object run(Calculator calculator, List<FormulaPart> params, Map<String, Object> paramValues, CalcContext ctx) {
    String string = (String) paramValues.get("string");
    int length    = (int)    paramValues.get("length");
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
      b.append(sample);
    }

    return b.toString();
  }

}