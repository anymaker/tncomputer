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
 * Returns a string resulting from replacing all occurrences of oldString in this string with newString.
 */
public class Replace extends Function {

  @Override
  protected List<Parameter> initParameters() {
    List<Parameter> parameters = new ArrayList<>();
    parameters.add(new Parameter(String.class, "string"));
    parameters.add(new Parameter(String.class, "oldString"));
    parameters.add(new Parameter(String.class, "newString"));
    return parameters;
  }

  @Override
  public Object run(Calculator calculator, List<FormulaPart> params, Map<String, Object> paramValues, CalcContext ctx) {
    String string    = (String) paramValues.get("string");
    String oldString = (String) paramValues.get("oldString");
    String newString = (String) paramValues.get("newString");

    if (string == null) {
      return null;
    }
    if (oldString == null) {
      return string;
    }

    if (newString == null) {
      newString = "";
    }

    String result = StringUtil.replace(string, oldString, newString);
    return result;
  }

}