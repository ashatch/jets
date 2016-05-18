package net.andrewhatch.languages.jets.model;

import java.util.HashMap;
import java.util.Map;

public class Operators {
  private static Map<String,Operator> operatorSymbolMap;

  static {
    operatorSymbolMap = new HashMap<>();
    final ConcatentationOperator concat = new ConcatentationOperator();
    operatorSymbolMap.put(concat.getSymbol(), concat);
  }

  public static Operator fromString(final String key) {
    final Operator operator = operatorSymbolMap.get(key);
    if (operator == null) {
      throw new RuntimeException("Invalid operator string '" + key + "'");
    }
    return operator;
  }
}
