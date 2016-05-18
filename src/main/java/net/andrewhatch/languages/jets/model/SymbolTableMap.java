package net.andrewhatch.languages.jets.model;

import net.andrewhatch.languages.jets.exceptions.SymbolAlreadyDeclaredException;
import net.andrewhatch.languages.jets.exceptions.SymbolNotFoundException;

import java.util.HashMap;
import java.util.Map;

public class SymbolTableMap implements SymbolTable {

  private Map<String,String> map;

  public SymbolTableMap() {
    this.map = new HashMap<>();
  }

  @Override
  public void addSymbol(final String key, final String value) throws SymbolAlreadyDeclaredException {
    if (map.containsKey(key)) {
      throw new SymbolAlreadyDeclaredException(key);
    }

    map.put(key, value);
  }

  @Override
  public String getSymbol(final String key) throws SymbolNotFoundException {
    assertExists(key);
    return map.get(key);
  }

  @Override
  public void setSymbol(final String variableName, final String result) {
    assertExists(variableName);
    map.put(variableName, result);
  }

  private void assertExists(final String key) {
    if (!map.containsKey(key)) {
      throw new SymbolNotFoundException(key);
    }
  }
}
