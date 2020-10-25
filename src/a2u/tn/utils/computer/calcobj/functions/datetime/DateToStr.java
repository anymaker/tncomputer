package a2u.tn.utils.computer.calcobj.functions.datetime;

import a2u.tn.utils.computer.calculator.Calculator;
import a2u.tn.utils.computer.calculator.Function;
import a2u.tn.utils.computer.formula.FormulaPart;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Convert java.util.Date to string with format
 */
public class DateToStr extends Function {

  public DateToStr(Calculator calculator) {
    super(calculator);
  }

  @Override
  protected List<Parameter> initParameters() {
    List<Parameter> parameters = new ArrayList<>();
    parameters.add(new Parameter(Date.class,   "date"));
    parameters.add(new Parameter(String.class, "format"));
    return parameters;
  }

  @Override
  public Object run(List<FormulaPart> params, Object row, int rowIndex, Collection<Object> allRows) {
    Object dateValue   = calculator.calcArgument(params.get(0), row, rowIndex, allRows);
    Object formatValue = calculator.calcArgument(params.get(1), row, rowIndex, allRows);

    Date date     = calculator.toType(Date.class,   dateValue);
    String format = calculator.toType(String.class, formatValue);

    DateFormat df = new SimpleDateFormat(format);
    String str = df.format(date);
    return str;
  }
}
