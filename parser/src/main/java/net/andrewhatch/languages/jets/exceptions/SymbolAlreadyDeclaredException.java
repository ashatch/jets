package net.andrewhatch.languages.jets.exceptions;

public class SymbolAlreadyDeclaredException extends SymbolTableException {
  public SymbolAlreadyDeclaredException(final String variableName) {
    super("Symbol '" + variableName + "' already declared");
  }
}
