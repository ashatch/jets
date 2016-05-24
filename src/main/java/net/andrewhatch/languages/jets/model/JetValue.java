package net.andrewhatch.languages.jets.model;

public class JetValue<T> {
  protected T value;

  public T getValue() {
    return value;
  }

  public JetValue<T> withValue(final T value) {
    this.value = value;
    return this;
  }
}
