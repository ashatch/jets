package net.andrewhatch.languages.jets;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;

import net.andrewhatch.languages.jets.modules.CommandLineModule;
import net.andrewhatch.languages.jets.modules.JetsModule;

import org.antlr.v4.runtime.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

import javax.inject.Inject;
import javax.inject.Named;


public class Jets {
  private static final Logger logger = LoggerFactory.getLogger(Jets.class);

  @Inject
  public Jets(JetsVM jetsVM,
      @Named("jets.commandline.args") String... args) {
    try {
      final InputStream in = getInputStream(args);
      final JetsLexer l = new JetsLexer(new ANTLRInputStream(in));
      final JetsParser p = new JetsParser(new CommonTokenStream(l));
      p.addErrorListener(new JetsErrorListener());
      p.addParseListener(jetsVM);
      p.prog();

    } catch (IOException ioe) {
      logger.error("IO fault", ioe);
    }
  }

  private static InputStream getInputStream(final String[] args) throws FileNotFoundException {
    if (args.length == 0) {
      logger.info("Reading from STDIN");
      return System.in;
    } else {
      final String filePath = args[0];
      logger.info("Reading from {}", filePath);
      return new FileInputStream(new File(filePath));
    }
  }

  public static void main(final String[] args) throws IOException {
    Guice.createInjector(
        new JetsModule(),
        new CommandLineModule(args),
        new AbstractModule() {
          @Override
          protected void configure() {
            bind(Jets.class).asEagerSingleton();
          }
        }
    );
  }
}
