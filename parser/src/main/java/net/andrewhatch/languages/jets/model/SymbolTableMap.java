package net.andrewhatch.languages.jets.model;

import net.andrewhatch.languages.jets.exceptions.SymbolAlreadyDeclaredException;
import net.andrewhatch.languages.jets.exceptions.SymbolNotFoundException;

import java.util.HashMap;
import java.util.Map;

public class SymbolTableMap implements SymbolTable {

  private Map<String,Symbol> map;

  public SymbolTableMap() {
    this.map = new HashMap<>();
  }

  @Override
  public void addSymbol(final Symbol symbol) throws SymbolAlreadyDeclaredException {
    if (map.containsKey(symbol.getKey())) {
      throw new SymbolAlreadyDeclaredException(symbol.getKey());
    }

    map.put(symbol.getKey(), symbol);
  }

  @Override
  public Symbol getSymbol(final String key) throws SymbolNotFoundException {
    assertExists(key);
    return map.get(key);
  }
  
  private void assertExists(final String key) {
    if (!map.containsKey(key)) {
      throw new SymbolNotFoundException(key);
    }
  }
}
