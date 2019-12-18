package a2u.tn.utils.computer.calcobj.functions;

import a2u.tn.utils.computer.calculator.Calculator;
import a2u.tn.utils.computer.calculator.Function;
import a2u.tn.utils.computer.formula.FormulaPart;

import java.util.ArrayList;
import java.util.Collection;
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
public class Like extends Function {

  private static List<Parameter> parameters;

  public Like(Calculator calculator) {
    super(calculator);
    parameters = new ArrayList<>();
    parameters.add(new Parameter(String.class, "string"));
    parameters.add(new Parameter(String.class, "template"));
  }

  @Override
  public String getName() {
    return "like";
  }

  @Override
  public List<Parameter> getParameters() {
    return parameters;
  }

  @Override
  public Object run(Map<String, FormulaPart> namedParams, List<FormulaPart> otherParams, Object row, int rowIndex, Collection<Object> allRows) {
    Object stringValue   = calculator.calcArgument(namedParams.get("string"),   row, rowIndex, allRows);
    Object templateValue = calculator.calcArgument(namedParams.get("template"), row, rowIndex, allRows);

    String string   = calculator.toType(String.class, stringValue);
    String template = calculator.toType(String.class, templateValue);

    Pattern p = Pattern.compile(template);
    Matcher mtch = p.matcher(string);

    boolean istrue =  mtch.find();
    return istrue;
  }
}
