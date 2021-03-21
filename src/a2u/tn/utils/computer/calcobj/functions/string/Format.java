package a2u.tn.utils.computer.calcobj.functions.string;

import a2u.tn.utils.computer.calculator.CalcContext;
import a2u.tn.utils.computer.calculator.Calculator;
import a2u.tn.utils.computer.calculator.Function;
import a2u.tn.utils.computer.formula.FormulaPart;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Formats a number into a string by mask
 * Sample:
 *  number: 12345678901
 *  mask:   S(XXX)XXX-XXX XX
 *  result: +(123)456-789 01
 *
 *  Where 'S' the sign holder, default character 'S';
 *        'X' the digit holder, default caracter 'X'.
 */
public class Format extends Function {

  @Override
  protected List<Parameter> initParameters() {
    List<Parameter> parameters = new ArrayList<>();
    parameters.add(new Parameter(String.class, "value"));
    parameters.add(new Parameter(String.class, "mask"));
    parameters.add(new Parameter(String.class, "digitHolder", false, "X"));
    parameters.add(new Parameter(String.class, "signHolder", false, "S"));
    return parameters;
  }

  @Override
  public Object run(Calculator calculator, List<FormulaPart> params, Map<String, Object> paramValues, CalcContext ctx) {
    String value =       (String) paramValues.get("value");
    String mask =        (String) paramValues.get("mask");
    String digitHolder = (String) paramValues.get("digitHolder");
    String signHolder =  (String) paramValues.get("signHolder");

    char digitChar = digitHolder.charAt(0);
    char signChar = signHolder.charAt(0);

    char sign;
    if (value.startsWith("+")) {
      sign = '+';
      value = value.substring(1);
    }
    else if (value.startsWith("-")) {
      sign = '-';
      value = value.substring(1);
    }
    else {
      sign = '+';
    }

    StringBuilder b = new StringBuilder();

    int valueIx = 0;
    int maskIx = 0;

    while (valueIx < value.length() && maskIx < mask.length()) {
      char maskChar = mask.charAt(maskIx);
      if (maskChar == digitChar) {
        b.append(value.charAt(valueIx));
        valueIx++;
      }
      else if (maskChar == signChar) {
        b.append(sign);
      }
      else {
        b.append(maskChar);
      }
      maskIx++;
    }

    return b.toString();
  }

}