package a2u.tn.utils.computer.formula;

import a2u.tn.utils.computer.StringUtil;

/**
 * Extract value from source
 */
public class FPValue implements FormulaPart {

  private String fldName;      //Field name in source
  private FormulaPart filter;  //Filter that will be applied to the result
  private FPValue next = null; //Next extractvalue command

  public FPValue(String fieldName) {
    this.fldName = fieldName;
  }

  public String getFieldName() {
    return fldName;
  }

  public FormulaPart getFilter() {
    return filter;
  }
  public void setFilter(FormulaPart filter) {
    this.filter = filter;
  }

  public FPValue getNext() {
    return next;
  }
  public void setNext(FPValue next) {
    this.next = next;
  }

  /**
   * Return FPValue from start and withoun end step
   * @return part or null if no har next
   */
  public FPValue getWithoutEnd() {
    if (next == null) {
      return null;
    }
    FPValue start = clone();
    FPValue last = start;
    while (last.next != null && last.next.next != null) {
      FPValue part = last.next.clone();
      last.next = part;
      last = part;
    }
    last.next = null;
    return start;
  }

  /**
   * Return target extracting value part
   * @return end part in sequence
   */
  public FPValue getTargetPart() {
    FPValue part = this;
    while (true) {
      if (part.next == null) {
        return part;
      }
      else {
        part = part.next;
      }
    }
  }


  @Override
  public String toString() {
    StringBuilder b = new StringBuilder(".");
    b.append(fldName);
    if (filter != null) {
      b.append("(").append(filter).append(")");
    }
    if (next != null) {
      b.append(next);
    }
    return b.toString();
  }

  @Override
  public void toFormated(StringBuilder b, int indent) {
    int indent2 = indent + 2;

    b.append(".value[");

    b.append(fldName);
    if (filter != null) {
      b.append("(");
      b.append("\n");
      StringUtil.repeat(b, " ", indent2);
      filter.toFormated(b, indent2);
      b.append("\n");
      StringUtil.repeat(b, " ", indent);
      b.append(")");
    }
    b.append("]");

    if (filter != null) {
      b.append("\n");
      StringUtil.repeat(b, " ", indent);
    }

    if (next != null) {
      if (filter == null) {
        b.append("\n");
        StringUtil.repeat(b, " ", indent);
      }
      next.toFormated(b, indent);
    }
  }

  @Override
  public String toJson() {
    return "{type= 'value', fieldName: '" + fldName + "', filter: " + (filter==null?"null":filter.toJson()) + ", next: " + (next==null?"null":next.toJson()) + "}";
  }


  public FPValue clone() {
    FPValue newvalue = new FPValue(fldName);
    newvalue.filter = filter;
    newvalue.next = next;
    return newvalue;
  }
}
