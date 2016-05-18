package net.andrewhatch.languages.jets.exceptions;

public class SymbolNotFoundException extends SymbolTableException {
  public SymbolNotFoundException(final String symbol) {
    super("Symbol '" + symbol + "' not declared.");
  }
}
