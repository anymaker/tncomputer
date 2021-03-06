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
 * Remove all whitespaces characters
 */
public class RemoveWhitespaces extends Function {

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
      return true;
    }

    StringBuilder b = new StringBuilder();
    int len = string.length();

    for (int ix = 0; ix < len; ix++) {
      char c = string.charAt(ix);
      if (! StringUtil.isWhiteSpace(c)) {
        b.append(c);
      }
    }

    return b.toString();
  }

}