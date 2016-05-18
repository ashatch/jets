package net.andrewhatch.languages.jets.model;

import net.andrewhatch.languages.jets.exceptions.SymbolAlreadyDeclaredException;
import net.andrewhatch.languages.jets.exceptions.SymbolNotFoundException;

public interface SymbolTable {
  void addSymbol(String key, String value) throws SymbolAlreadyDeclaredException;
  String getSymbol(String key) throws SymbolNotFoundException;
  void setSymbol(String variableName, String result);
}
