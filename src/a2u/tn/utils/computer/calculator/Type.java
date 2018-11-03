package a2u.tn.utils.computer.calculator;

/**
 * Type description
 */
public abstract class Type {

  protected Calculator calculator;

  public Type(Calculator calculator) {
    this.calculator = calculator;
  }

  public abstract void fillConverter(Converter converter);


  public abstract Class<?> forClass();


  public Object mul(Object v1, Object v2) {
    throw new IllegalOperationException("Operation mul is not aloowet for type '"+ forClass().getName() +"'.");
  }
  public Object div(Object v1, Object v2) {
    throw new IllegalOperationException("Operation div is not aloowet for type '"+ forClass().getName() +"'.");
  }

  public Object plus(Object v1, Object v2) {
    throw new IllegalOperationException("Operation plus is not aloowet for type '"+ forClass().getName() +"'.");
  }
  public Object minus(Object v1, Object v2) {
    throw new IllegalOperationException("Operation minus is not aloowet for type '"+ forClass().getName() +"'.");
  }

  public boolean equal(Object v1, Object v2) {
    throw new IllegalOperationException("Operation equal is not aloowet for type '"+ forClass().getName() +"'.");
  }
  public boolean notequal(Object v1, Object v2) {
    throw new IllegalOperationException("Operation notequal is not aloowet for type '"+ forClass().getName() +"'.");
  }

  public boolean great(Object v1, Object v2) {
    throw new IllegalOperationException("Operation great is not aloowet for type '"+ forClass().getName() +"'.");
  }
  public boolean greatEqual(Object v1, Object v2) {
    throw new IllegalOperationException("Operation greatEqual is not aloowet for type '"+ forClass().getName() +"'.");
  }
  public boolean less(Object v1, Object v2) {
    throw new IllegalOperationException("Operation less is not aloowet for type '"+ forClass().getName() +"'.");
  }
  public boolean lessEqual(Object v1, Object v2) {
    throw new IllegalOperationException("Operation lessEqual is not aloowet for type '"+ forClass().getName() +"'.");
  }

  public Object and(Object v1, Object v2) {
    boolean value1 = calculator.toType(Boolean.class, v1);
    boolean value2 = calculator.toType(Boolean.class, v2);
    return value1 && value2;
  }
  public Object or(Object v1, Object v2) {
    boolean value1 = calculator.toType(Boolean.class, v1);
    boolean value2 = calculator.toType(Boolean.class, v2);
    return value1 || value2;
  }
  public Object xor(Object v1, Object v2) {
    boolean value1 = calculator.toType(Boolean.class, v1);
    boolean value2 = calculator.toType(Boolean.class, v2);
    return (value1 && !value2) || (!value1 && value2);
  }


}
