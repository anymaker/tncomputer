package a2u.tn.utils.computer.calcobj.functions.datetime;

import a2u.tn.utils.computer.calculator.CalcContext;
import a2u.tn.utils.computer.calculator.Calculator;
import a2u.tn.utils.computer.calculator.Function;
import a2u.tn.utils.computer.formula.FormulaPart;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Convert java.util.Date to string with format
 */
public class DateToStr extends Function {

  @Override
  protected List<Parameter<?>> initParameters() {
    List<Parameter<?>> parameters = new ArrayList<>();
    parameters.add(new Parameter<>(OffsetDateTime.class,   "date"));
    parameters.add(new Parameter<>(String.class, "format"));
    return parameters;
  }

  @Override
  public Object run(Calculator calculator, List<FormulaPart> params, Map<String, Object> paramValues, CalcContext ctx) {
    OffsetDateTime date = (OffsetDateTime) paramValues.get("date");
    String format       = (String) paramValues.get("format");

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern(format);
    String str = dtf.format(date);
    return str;
  }
}
