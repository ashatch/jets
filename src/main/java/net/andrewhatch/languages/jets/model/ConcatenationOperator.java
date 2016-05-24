package net.andrewhatch.languages.jets.model;


class ConcatenationOperator<T> implements Operator<T> {
  @Override
  public String getSymbol() {
    return "+=";
  }

  @Override
  public JetValue<T> apply(final JetValue<T> currentValue, final JetValue<T> operand) {

    if (currentValue.getValue() instanceof Integer) {
      final Integer value = apply((Integer)currentValue.getValue(), (Integer)operand.getValue());
      return new JetValue<T>().withValue((T)value);
    } else {
      final String value = apply(currentValue.toString(), operand.toString());
      return new JetValue<T>().withValue((T)value);
    }
  }

  private String apply(final String s, final String s1) {
    return s+ s1;
  }

  private Integer apply(final Integer value, final Integer value1) {
    return value + value1;
  }
}
