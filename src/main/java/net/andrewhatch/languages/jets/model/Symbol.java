package net.andrewhatch.languages.jets.model;

public class Symbol<T> {
  private final String key;
  private JetValue<T> value;

  public Symbol(final String key) {
    this(key, null);
  }

  public Symbol(final String key, final JetValue<T> value) {
    this.key = key;
    this.value = value;
  }

  String getKey() {
    return key;
  }

  public JetValue<T> getValue() {
    return value;
  }

  public void setValue(final JetValue<T> value) {
    this.value = value;
  }
}
