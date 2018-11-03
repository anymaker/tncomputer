package a2u.tn.utils.computer.maplist.functions;

import a2u.tn.utils.computer.calculator.CalculatingException;
import a2u.tn.utils.computer.calculator.Calculator;
import a2u.tn.utils.computer.calculator.Function;

import java.util.Collection;
import java.util.Map;

/**
 * Return true is current row is first
 *
 * Using:
 * If query      .mailList.autor
 * return        {List:['Autor01','Autor02','Autor03']}
 *
 * Then query    .mailList.autor(firstRow)
 * will return   {String:'Autor01'}
 */
public class First extends Function {

  public First(Calculator calculator) {
    super(calculator);
  }

  @Override
  public String getName() {
    return "first";
  }

  @Override
  public Object run(Map<String, Object> paramValues, Object row, int rowIndex, Collection<Object> allRows) {
    if (allRows == null) {
      throw new CalculatingException("Function 'first' can only be used to filtering rows.");
    }
    return rowIndex == 0;
  }
}
