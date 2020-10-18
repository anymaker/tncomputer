package a2u.tn.utils.computer.calcobj.functions.datetime;

import a2u.tn.utils.computer.calculator.Calculator;
import a2u.tn.utils.computer.calculator.Function;
import a2u.tn.utils.computer.formula.FormulaPart;

import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Returns the last day of the year
 */
public class LastDayInYear extends Function {

  public LastDayInYear(Calculator calculator) {
    super(calculator);
  }

  @Override
  protected List<Parameter> initParameters() {
    List<Parameter> parameters = new ArrayList<>();
    parameters.add(new Parameter(Date.class, "date"));
    return parameters;
  }

  @Override
  public Object run(List<FormulaPart> params, Object row, int rowIndex, Collection<Object> allRows) {
    Object value = calculator.calcArgument(params.get(0), row, rowIndex, allRows);
    LocalDateTime date = calculator.toType(LocalDateTime.class, value);

    LocalDateTime calcedDate = date.with(TemporalAdjusters.lastDayOfYear());

    Date result = calculator.toType(Date.class, calcedDate);
    return result;
  }

}
