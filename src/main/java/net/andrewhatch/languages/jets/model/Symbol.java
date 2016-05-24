package net.andrewhatch.languages.jets.model;

public class Symbol {
  private final String key;
  private String value;

  public Symbol(final String key, final String value) {
    this.key = key;
    this.value = value;
  }

  String getKey() {
    return key;
  }

  public String getValue() {
    return value;
  }

  public void setValue(final String value) {
    this.value = value;
  }
}
