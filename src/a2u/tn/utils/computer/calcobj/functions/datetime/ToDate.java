package a2u.tn.utils.computer.calcobj.functions.datetime;

import a2u.tn.utils.computer.calculator.CalcContext;
import a2u.tn.utils.computer.calculator.Calculator;
import a2u.tn.utils.computer.calculator.Function;
import a2u.tn.utils.computer.formula.FormulaPart;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Converts a string with format to java.util.Date
 */
public class ToDate extends Function {

  @Override
  protected List<Parameter> initParameters() {
    List<Parameter> parameters = new ArrayList<>();
    parameters.add(new Parameter(String.class, "string"));
    parameters.add(new Parameter(String.class, "format"));
    return parameters;
  }

  @Override
  public Object run(Calculator calculator, List<FormulaPart> params, Map<String, Object> paramValues, CalcContext ctx) {
    String input  = (String) paramValues.get("string");
    String format = (String) paramValues.get("format");

    DateFormat df = new SimpleDateFormat(format);
    try {
      Date date = df.parse(input);
      return date;
    }
    catch (ParseException e) {
      throw new IllegalArgumentException("Invalid date format '"+ format +"' for value '"+ input +"'.");
    }
  }

}
