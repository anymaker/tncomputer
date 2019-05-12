package a2u.tn.utils.computer.calculator;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Function descriptor
 */
public abstract class Function {

  /**
   * Function parameter description
   */
  public class Parameter {
    private Class<?> type;
    private String name;

    public Parameter(Class<?> type, String name) {
      this.type = type;
      this.name = name;
    }

    public Class<?> getType() {
      return type;
    }
    public String getTypeName() {
      return type.getSimpleName();
    }
    public String getName() {
      return name;
    }
  }

  protected Calculator calculator;

  public Function(Calculator calculator) {
    this.calculator = calculator;
  }

  /**
   * Return function name for using in query
   * @return function name
   */
  public abstract String getName();

  /**
   * Return descriptors for incoming parameters
   * @return descriptors for incoming parameters
   */
  public List<Parameter> getParameters() {
    return Collections.emptyList();
  }

  /**
   * Invoke function to execution
   * @param paramValues Map with parameters value
   * @param row         Data from current row
   * @param rowIndex    Index current row in rows
   * @param allRows     Collection with all rows
   * @return            result of execution function
   */
  public abstract Object run(Map<String, Object> paramValues, Object row, int rowIndex, Collection<Object> allRows);


  public String toString() {
    return getName();
  }

}
