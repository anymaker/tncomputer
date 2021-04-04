package a2u.tn.utils.computer.calculator;

import java.util.Collection;

public interface CalcContext {

  /**
   * Data for computing
   * @return Current data for computing
   */
  Object getRowData();

  /**
   * Index in collection with all values
   * @return Index
   */
  int getRowIndex();

  /**
   * Collection with all values
   * @return All values
   */
  Collection<Object> getAllRows();

}
