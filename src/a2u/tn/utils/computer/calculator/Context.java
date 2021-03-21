package a2u.tn.utils.computer.calculator;

import java.util.Collection;

public class Context implements CalcContext {

  private Object rowData;

  public static Context of(Object rowData, int rowIndex, Collection<Object> allRows) {
    Context ctx = new Context();
    ctx.rowData = rowData;
    ctx.rowIndex = rowIndex;
    ctx.allRows = allRows;
    return ctx;
  }

  private int rowIndex;
  private Collection<Object> allRows;

  @Override
  public Object getRowData() {
    return rowData;
  }

  public void setRowData(Object rowData) {
    this.rowData = rowData;
  }

  @Override
  public int getRowIndex() {
    return rowIndex;
  }

  public void setRowIndex(int rowIndex) {
    this.rowIndex = rowIndex;
  }

  @Override
  public Collection<Object> getAllRows() {
    return allRows;
  }

  public void setAllRows(Collection<Object> allRows) {
    this.allRows = allRows;
  }
}
