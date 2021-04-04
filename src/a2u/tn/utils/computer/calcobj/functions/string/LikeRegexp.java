package a2u.tn.utils.computer.calcobj.functions.string;

import a2u.tn.utils.computer.calculator.CalcContext;
import a2u.tn.utils.computer.calculator.Calculator;
import a2u.tn.utils.computer.calculator.Function;
import a2u.tn.utils.computer.formula.FormulaPart;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Return true if the condition specified by the regular expression is true
 *
 * Parameters:
 *   - string for test
 *   - regular expression
 *
 */
public class LikeRegexp extends Function {

  @Override
  protected List<Parameter<?>> initParameters() {
    List<Parameter<?>> parameters = new ArrayList<>();
    parameters.add(new Parameter<>(String.class, "string"));
    parameters.add(new Parameter<>(String.class, "template"));
    return parameters;
  }

  @Override
  public Object run(Calculator calculator, List<FormulaPart> params, Map<String, Object> paramValues, CalcContext ctx) {
    String string   = (String) paramValues.get("string");
    String template = (String) paramValues.get("template");

    Pattern p = Pattern.compile(template);
    Matcher match = p.matcher(string);

    boolean isTrue =  match.find();
    return isTrue;
  }

}
