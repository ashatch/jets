package net.andrewhatch.languages.jets.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;

import javax.inject.Named;

public class CommandLineModule extends AbstractModule {
  private final String[] args;

  public CommandLineModule(final String... args) {
    this.args = args;
  }

  @Override
  protected void configure() {}

  @Provides
  @Named("jets.commandline.args")
  public String[] getArgs() {
    return this.args;
  }
}
