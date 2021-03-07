package a2u.tn.utils.computer.calcobj.functions.string;

import a2u.tn.utils.computer.calculator.Calculator;
import a2u.tn.utils.computer.calculator.Function;
import a2u.tn.utils.computer.formula.FormulaPart;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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
  protected List<Parameter> initParameters() {
    List<Parameter> parameters = new ArrayList<>();
    parameters.add(new Parameter(String.class, "string"));
    parameters.add(new Parameter(String.class, "template"));
    return parameters;
  }

  @Override
  public Object run(Calculator calculator, List<FormulaPart> params, Object row, int rowIndex, Collection<Object> allRows) {
    Object stringValue   = calculator.calcArgument(params.get(0), row, rowIndex, allRows);
    Object templateValue = calculator.calcArgument(params.get(1), row, rowIndex, allRows);

    String string   = calculator.toType(String.class, stringValue);
    String template = calculator.toType(String.class, templateValue);

    Pattern p = Pattern.compile(template);
    Matcher mtch = p.matcher(string);

    boolean istrue =  mtch.find();
    return istrue;
  }

}
