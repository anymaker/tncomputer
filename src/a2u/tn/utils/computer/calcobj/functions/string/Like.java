package a2u.tn.utils.computer.calcobj.functions.string;

import a2u.tn.utils.computer.calculator.CalcContext;
import a2u.tn.utils.computer.calculator.Calculator;
import a2u.tn.utils.computer.calculator.Function;
import a2u.tn.utils.computer.formula.FormulaPart;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Returns true if the condition specified in the template is met, similar to the 'like' operation as in databases.
 *
 * Parameters:
 *   - string for test
 *   - template expression, where
 *          character '_' substitute any one characters
 *          character '%' substitute more than one of any characters
 *
 */
public class Like extends Function {


  @Override
  protected List<Parameter<?>> initParameters() {
    List<Parameter<?>> parameters = new ArrayList<>();
    parameters.add(new Parameter<>(String.class, "string"));
    parameters.add(new Parameter<>(String.class, "template"));
    parameters.add(new Parameter<>(String.class, "escape", false, null));
    return parameters;
  }

  @Override
  public Object run(Calculator calculator, List<FormulaPart> params, Map<String, Object> paramValues, CalcContext ctx) {
    String string   = (String) paramValues.get("string");
    String template = (String) paramValues.get("template");
    String escape   = (String) paramValues.get("escape");

    if (string == null) {
      return false;
    }
    if (template == null) {
      throw new IllegalArgumentException("The template can not be null.");
    }

    Template parsedTemplate = parseTemplate(template);
    boolean isTrue = match(string, parsedTemplate, 0, 0);

    return isTrue;
  }


  private enum PartType {
    Quantifier,
    Text
  }

  private static class TemplatePart {
    private PartType type;
    private String body;
    @Override
    public String toString() {
      return type+"["+body+"]";
    }
  }

  private static class Template {
    private final List<TemplatePart> parts = new ArrayList<>();

    void add(PartType type, String body) {
      TemplatePart lastPart = getLast();
      if (lastPart != null && type == PartType.Text && lastPart.type == PartType.Quantifier) {
        lastPart.body = body;
      }
      else if (lastPart != null && type == PartType.Quantifier && lastPart.type == PartType.Quantifier && lastPart.body == null) {
        lastPart.body = body;
      }
      else {
        TemplatePart part = new TemplatePart();
        part.type = type;
        part.body = body;
        parts.add(part);
      }

    }

    private TemplatePart getLast() {
      if (parts.isEmpty()) {
        return null;
      }
      return parts.get(parts.size()-1);
    }

  }


  private Template parseTemplate(String source) {
    Template template = new Template();

    StringBuilder body = new StringBuilder();

    for (int ix = 0; ix < source.length(); ix++) {
      char c = source.charAt(ix);
      if (c == '%') {
        if (body.length() > 0) {
          template.add(PartType.Text, body.toString());
          body = new StringBuilder();
        }
        template.add(PartType.Quantifier, null);
      }
      else {
        body.append(c);
      }
    }

    if (body.length() > 0) {
      template.add(PartType.Text, body.toString());
    }

    return template;
  }


  /**
   * Checking a string against a template
   * @param string       checked string
   * @param template     template to compare
   * @param templateIx   index of first part in template
   * @param stringIx     index of first character in string
   * @return             true is match
   */
  private boolean match(String string, Template template, int templateIx, int stringIx) {
      for (int tIx = templateIx; tIx < template.parts.size(); tIx++) {
      TemplatePart currentPart = template.parts.get(tIx);

      switch (currentPart.type) {
        case Text:
          boolean isMatch1 = isMatch(string, stringIx, currentPart.body);
          stringIx+=currentPart.body.length();
          if (!isMatch1) {
            return false;
          }
          break;

        case Quantifier:
          if (currentPart.body == null) {
            return true;
          }
          int isMatch2 = findSubstr(string, stringIx, currentPart.body);
          if (isMatch2 < 0) {
            return false;
          }
          stringIx = isMatch2;

          boolean isMatch3 = match(string, template, tIx+1, isMatch2+currentPart.body.length());
          while (!isMatch3 && string.length()-stringIx>currentPart.body.length()) {
            isMatch3 = match(string, template, tIx, ++stringIx);
          }
          return isMatch3;
      }

    }

    return stringIx == string.length();
  }

  /**
   * Searches for a string
   * @param source     string to compare
   * @param ix         index of first character in a string
   * @param substring  the string to be found
   * @return           index of start a searched string
   */
  private int findSubstr(String source, int ix, String substring) {
    if (source.length() < substring.length()) {
      return -1;
    }

    boolean isMatch = isMatch(source, ix, substring);

    while (ix < source.length()-substring.length() && !isMatch) {
      ix++;
      isMatch = isMatch(source, ix, substring);
    }

    return isMatch ? ix : -1;
  }

  /**
   * Checks that part of a string matches a template
   * @param str      incoming string
   * @param startIx  index of first symbol in the incoming string
   * @param template template for matching
   * @return         true is template matches part of the incoming string
   */
  private boolean isMatch(String str, int startIx, String template) {
    int ix = 0;
    while (ix < template.length() && startIx+ix < str.length()
      &&  (template.charAt(ix) == '_' || str.charAt(startIx + ix) == template.charAt(ix)))
    {
      ix++;
    }
    return ix == template.length();
  }

}
