package a2u.tn.utils.computer.calculator;

import java.util.Collection;

public interface CalcContext {

  /**
   * Data for computing
   */
  Object getRowData();

  /**
   * index in collection with all values
   */
  int getRowIndex();

  /**
   * collection with all values
   */
  Collection<Object> getAllRows();

}
