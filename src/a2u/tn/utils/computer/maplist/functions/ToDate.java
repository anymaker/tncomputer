package a2u.tn.utils.computer.maplist.functions;

import a2u.tn.utils.computer.calculator.Calculator;
import a2u.tn.utils.computer.calculator.Function;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Converts a string with format to java.util.Date
 */
public class ToDate extends Function {

  private static List<Parameter> parameters;

  public ToDate(Calculator calculator) {
    super(calculator);
    parameters = new ArrayList<>();
    parameters.add(new Parameter(String.class, "input"));
    parameters.add(new Parameter(String.class, "format"));
  }


  @Override
  public String getName() {
    return "toDate";
  }

  @Override
  public List<Parameter> getParameters() {
    return parameters;
  }

  @Override
  public Object run(Map<String, Object> paramValues, Object row, int rowIndex, Collection<Object> allRows) {
    String input  = calculator.toType(String.class, paramValues.get("input"));
    String format = calculator.toType(String.class, paramValues.get("format"));

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
