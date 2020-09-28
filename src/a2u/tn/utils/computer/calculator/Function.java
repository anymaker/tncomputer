package a2u.tn.utils.computer.calculator;

import a2u.tn.utils.computer.formula.FormulaPart;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

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
    private boolean required = true;
    private String defaultValue = null;

    public Parameter(Class<?> type, String name) {
      this.type = type;
      this.name = name;
    }
    
    public Parameter(Class<?> type, String name, boolean required, String defaultValue) {
      this.type = type;
      this.name = name;
      this.required = required;
      this.defaultValue = defaultValue;
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
    public boolean isRequired() {
      return required;
    };
    public String getDefaultValue() {
      return defaultValue;
    }
  }

  protected Calculator calculator;
  private List<Parameter> parameters;

  public Function(Calculator calculator) {
    this.calculator = calculator;
    parameters = initParameters();
  }

  protected List<Parameter> initParameters() {
    return Collections.emptyList();
  }

  /**
   * Return function name to use in queries
   * @return function name
   */
  public String getName() {
    return this.getClass().getSimpleName();
  }

  /**
   * Return descriptors for incoming parameters
   * @return descriptors for incoming parameters
   */
  public final List<Parameter> getParameters() {
    return parameters;
  }

  /**
   * Invoke function to execution
   * @param params   Other params
   * @param row      Data from current row
   * @param rowIndex Index current row in rows
   * @param allRows  Collection with all rows
   * @return         result of execution function
   */
  public abstract Object run(List<FormulaPart> params, Object row, int rowIndex, Collection<Object> allRows);


  public String toString() {
    return getName();
  }

}
