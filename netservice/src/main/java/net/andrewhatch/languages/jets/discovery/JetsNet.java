package net.andrewhatch.languages.jets.discovery;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;

import net.andrewhatch.languages.jets.discovery.modules.DiscoveryModule;
import net.andrewhatch.languages.jets.modules.CommandLineModule;
import net.andrewhatch.languages.jets.modules.JetsModule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

public class JetsNet {

  private static Logger logger = LoggerFactory.getLogger(JetsNet.class);

  @Inject
  public JetsNet() {
    logger.trace("JetsNet");
  }

  public static void main(String[] args) {
    Guice.createInjector(
        new CommandLineModule(args),
        new JetsModule(),
        new DiscoveryModule(),
        new AbstractModule() {
          @Override
          protected void configure() {
            bind(JetsNet.class).asEagerSingleton();
          }
        }
    );
  }
}
