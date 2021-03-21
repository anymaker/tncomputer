package a2u.tn.utils.computer.calcobj.types;

import a2u.tn.utils.computer.calculator.Calculator;
import a2u.tn.utils.computer.calculator.Converter;
import a2u.tn.utils.computer.calculator.IllegalOperationException;
import a2u.tn.utils.computer.calculator.Type;

import java.util.Objects;

public class TObject extends Type {

  public TObject(Calculator calculator) {
    super(calculator);
  }

  @Override
  public void fillConverter(Converter converter) {
    converter.addConverter(Object.class, null, value -> null);
    converter.addConverter(Object.class, Converter.AnyClass.class, value -> value);
  }

  @Override
  public Class<?> forClass() {
    return Object.class;
  }

  public boolean equal(Object v1, Object v2) {
    if (v1 == null || v2 == null) {
      throw new IllegalArgumentException();
    }
    Object val1 = v1.getClass().isEnum() ? String.valueOf(v1) : v1;
    Object val2 = v2.getClass().isEnum() ? String.valueOf(v2) : v2;

    return Objects.equals(val1, val2);
  }
  public boolean notequal(Object v1, Object v2) {
    return !equal(v1, v2);
  }

  @SuppressWarnings("unchecked")
  public boolean great(Object v1, Object v2) {
    if (v1 != null && v2 != null) {
      if (v1.getClass().equals(v2.getClass()) && v1 instanceof Comparable) {
        return ((Comparable) v1).compareTo(v2) > 0;
      }
    }
    throw new IllegalOperationException("Operation great is not allowed for type '"+ forClass().getName() +"'.");
  }

  @SuppressWarnings("unchecked")
  public boolean greatEqual(Object v1, Object v2) {
    if (v1 != null && v2 != null) {
      if (v1.getClass().equals(v2.getClass()) && v1 instanceof Comparable) {
        return ((Comparable) v1).compareTo(v2) >= 0;
      }
    }
    throw new IllegalOperationException("Operation greatEqual is not allowed for type '"+ forClass().getName() +"'.");
  }

  @SuppressWarnings("unchecked")
  public boolean less(Object v1, Object v2) {
    if (v1 != null && v2 != null) {
      if (v1.getClass().equals(v2.getClass()) && v1 instanceof Comparable) {
        return ((Comparable) v1).compareTo(v2) < 0;
      }
    }
    throw new IllegalOperationException("Operation less is not allowed for type '"+ forClass().getName() +"'.");
  }

  @SuppressWarnings("unchecked")
  public boolean lessEqual(Object v1, Object v2) {
    if (v1 != null && v2 != null) {
      if (v1.getClass().equals(v2.getClass()) && v1 instanceof Comparable) {
        return ((Comparable) v1).compareTo(v2) <= 0;
      }
    }
    throw new IllegalOperationException("Operation lessEqual is not allowed for type '"+ forClass().getName() +"'.");
  }

}
