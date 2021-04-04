package a2u.tn.utils.computer.calculator;

import a2u.tn.utils.computer.formula.FormulaPart;

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
    private boolean required = true;
    private String defaultValue = null;

    /**
     * Constructor with some parameters
     *
     * @param type Incoming parameter value type
     * @param name Parameter name
     */
    public Parameter(Class<?> type, String name) {
      this.type = type;
      this.name = name;
    }

    /**
     * Constructor with all parameters
     *
     * @param type         Incoming parameter value type
     * @param name         Parameter name
     * @param required     True if the parameter is required
     * @param defaultValue The default value for the parameter. Use it if the parameter is not required but must have a value.
     */
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

    @Override
    public String toString() {
      return name +"("+ type.getSimpleName() +")";
    }
  }

  private List<Parameter> parameters;

  public Function() {
    parameters = initParameters();
  }

  protected List<Parameter> initParameters() {
    return Collections.emptyList();
  }

  /**
   * Return function name to use in queries
   *
   * @return function name
   */
  public String getName() {
    return this.getClass().getSimpleName();
  }

  /**
   * Return descriptors for incoming parameters
   *
   * @return descriptors for incoming parameters
   */
  public final List<Parameter> getParameters() {
    return parameters;
  }

  /**
   * Invoke function to execution
   *
   * @param calculator  Calculator for executing or type conversion
   * @param params      Other params
   * @param paramValues Prepared values of parameters
   * @param ctx         Data for calculating
   * @return            Result of execution function
   */
  public abstract Object run(Calculator calculator, List<FormulaPart> params, Map<String, Object> paramValues, CalcContext ctx);


  public String toString() {
    return getName();
  }

}
