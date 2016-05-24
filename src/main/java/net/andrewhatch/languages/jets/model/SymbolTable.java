package net.andrewhatch.languages.jets.model;

import net.andrewhatch.languages.jets.exceptions.SymbolAlreadyDeclaredException;
import net.andrewhatch.languages.jets.exceptions.SymbolNotFoundException;

public interface SymbolTable {
  void addSymbol(Symbol symbol) throws SymbolAlreadyDeclaredException;
  Symbol getSymbol(String key) throws SymbolNotFoundException;
}
