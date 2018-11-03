package a2u.tn.utils.computer.maplist.functions;

import a2u.tn.utils.computer.calculator.Calculator;
import a2u.tn.utils.computer.calculator.Function;

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
  public Object run(Map<String, Object> paramValues, Object row, int rowIndex, Collection<Object> allRows) {
    String string   = calculator.toType(String.class, paramValues.get("string"));
    String template = calculator.toType(String.class, paramValues.get("template"));

    Pattern p = Pattern.compile(template);
    Matcher mtch = p.matcher(string);

    boolean istrue =  mtch.find();
    return istrue;
  }
}
