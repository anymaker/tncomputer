package a2u.tn.utils.computer.formula;

import a2u.tn.utils.computer.StringUtil;

import java.util.Collections;
import java.util.List;

/**
 * Function calling info
 */
public class FPFunction implements FormulaPart {

  private String name;              //Function name
  private List<FormulaPart> params; //Caling arguments

  public FPFunction(String name, List<FormulaPart> params) {
    this.name = name;
    if (params == null) {
      this.params = Collections.emptyList();
    }
    else {
      this.params = params;
    }
  }

  public String getName() {
    return name;
  }
  public List<FormulaPart> getParams() {
    return params;
  }

  @Override
  public String toString() {
    StringBuilder b = new StringBuilder();
    b.append(name).append('(');
    if (params != null && !params.isEmpty()) {
      String list = StringUtil.collectionToString(", ", params, Object::toString);
      b.append(list);
    }
    b.append(')');
    return b.toString();
  }

  @Override
  public void toFormated(StringBuilder b, int indent) {
    b.append(".fn[").append(name);
    if (params != null && !params.isEmpty()) {
      b.append('(');
      for (int ix=0; ix< params.size(); ix++) {
        if (ix > 0) {
          b.append(",");
        }
        b.append("\n");
        StringUtil.repeat(b, " ", indent + 2);
        FormulaPart pp = params.get(ix);
        pp.toFormated(b, indent + 2);
      }


      b.append('\n');
      StringUtil.repeat(b, " ", indent);
      b.append(')');
    }
    b.append(']');
  }

  @Override
  public String toJson() {
    StringBuilder b = new StringBuilder();
    b.append("{type: 'function', name:'").append(name).append("'");
    if (params != null && !params.isEmpty()) {
      b.append(", params: [");
      for (int ix=0; ix< params.size(); ix++) {
        if (ix > 0) {
          b.append(',');
        }
        FormulaPart pp = params.get(ix);
        b.append(pp.toJson());
      }
      b.append(']');
    }
    b.append('}');
    return b.toString();
  }

}
