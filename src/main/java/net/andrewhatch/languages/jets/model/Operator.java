package net.andrewhatch.languages.jets.model;

public interface Operator<U> {
  String getSymbol();
  JetValue<U> apply(JetValue<U> currentValue, JetValue<U> operand);
}
