package net.andrewhatch.languages.jets.modules;

import com.google.inject.AbstractModule;

import net.andrewhatch.languages.jets.JetsVM;
import net.andrewhatch.languages.jets.model.SymbolTable;
import net.andrewhatch.languages.jets.model.SymbolTableMap;

public class JetsModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(SymbolTable.class).to(SymbolTableMap.class);
    bind(JetsVM.class);
  }
}
