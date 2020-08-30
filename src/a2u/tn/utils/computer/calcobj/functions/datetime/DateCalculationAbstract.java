package a2u.tn.utils.computer.calcobj.functions.datetime;

import a2u.tn.utils.computer.calculator.Calculator;
import a2u.tn.utils.computer.calculator.Function;
import a2u.tn.utils.computer.formula.FormulaPart;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Abstract class for date calculation
 */
public abstract class DateCalculationAbstract extends Function {

  public DateCalculationAbstract(Calculator calculator) {
    super(calculator);
  }


  @Override
  protected List<Parameter> initParameters() {
    List<Parameter> parameters = new ArrayList<>();
    parameters.add(new Parameter(Date.class, "date"));
    parameters.add(new Parameter(Long.class, getSecondParamName()));
    return parameters;
  }

  protected abstract String getSecondParamName();

  @Override
  public Object run(List<FormulaPart> params, Object row, int rowIndex, Collection<Object> allRows) {
    Object dateValue   = calculator.calcArgument(params.get(0),  row, rowIndex, allRows);
    Object monthsValue = calculator.calcArgument(params.get(1),  row, rowIndex, allRows);

    LocalDateTime date  = calculator.toType(LocalDateTime.class, dateValue);
    Long item = calculator.toType(Long.class, monthsValue);
    
    LocalDateTime calcedDate = calc(date, item);

    Date result = Date.from(calcedDate.atZone(ZoneId.systemDefault()).toInstant());

    return result;
  }

  protected abstract LocalDateTime calc(LocalDateTime current, long item);

}
