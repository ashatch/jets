package net.andrewhatch.languages.jets.model;

public interface Operator {
  String getSymbol();
  String apply(String currentValue, String operand);
}
