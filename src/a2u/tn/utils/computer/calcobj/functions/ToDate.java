package a2u.tn.utils.computer.calcobj.functions;

import a2u.tn.utils.computer.calculator.Calculator;
import a2u.tn.utils.computer.calculator.Function;
import a2u.tn.utils.computer.formula.FormulaPart;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Converts a string with format to java.util.Date
 */
public class ToDate extends Function {

  public ToDate(Calculator calculator) {
    super(calculator);
  }

  @Override
  protected List<Parameter> initParameters() {
    List<Parameter> parameters = new ArrayList<>();
    parameters.add(new Parameter(String.class, "input"));
    parameters.add(new Parameter(String.class, "format"));
    return parameters;
  }

  @Override
  public Object run(List<FormulaPart> params, Object row, int rowIndex, Collection<Object> allRows) {
    Object inputValue  = calculator.calcArgument(params.get(0),  row, rowIndex, allRows);
    Object formatValue = calculator.calcArgument(params.get(1), row, rowIndex, allRows);

    String input  = calculator.toType(String.class, inputValue);
    String format = calculator.toType(String.class, formatValue);

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
