package net.andrewhatch.languages.jets.model;

class ConcatenationOperator implements Operator {
  @Override
  public String getSymbol() {
    return "+=";
  }

  @Override
  public String apply(final String currentValue, final String operand) {
    return currentValue + operand;
  }
}
