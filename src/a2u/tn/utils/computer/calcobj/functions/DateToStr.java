package a2u.tn.utils.computer.calcobj.functions;

import a2u.tn.utils.computer.calculator.Calculator;
import a2u.tn.utils.computer.calculator.Function;
import a2u.tn.utils.computer.formula.FormulaPart;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Convert java.util.Date to string with format
 */
public class DateToStr extends Function {

  private static List<Parameter> parameters;

  public DateToStr(Calculator calculator) {
    super(calculator);
    parameters = new ArrayList<>();
    parameters.add(new Parameter(Date.class,   "input"));
    parameters.add(new Parameter(String.class, "format"));
  }

  @Override
  public String getName() {
    return "datetostr";
  }

  @Override
  public List<Parameter> getParameters() {
    return parameters;
  }

  @Override
  public Object run(Map<String, FormulaPart> namedParams, List<FormulaPart> otherParams, Object row, int rowIndex, Collection<Object> allRows) {
    Object dateValue  = calculator.calcArgument(namedParams.get("input"),  row, rowIndex, allRows);
    Object formatValue = calculator.calcArgument(namedParams.get("format"), row, rowIndex, allRows);

    Date date     = calculator.toType(Date.class,   dateValue);
    String format = calculator.toType(String.class, formatValue);

    DateFormat df = new SimpleDateFormat(format);
    String str = df.format(date);
    return str;
  }
}
