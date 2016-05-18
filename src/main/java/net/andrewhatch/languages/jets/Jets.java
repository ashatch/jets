package net.andrewhatch.languages.jets;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;

import net.andrewhatch.languages.jets.modules.CommandLineModule;
import net.andrewhatch.languages.jets.modules.JetsModule;

import org.antlr.v4.runtime.*;

import java.io.*;

import javax.inject.Inject;
import javax.inject.Named;


public class Jets {

  @Inject
  public Jets(@Named("jets.commandline.args") String... args) {
    try {
      final InputStream in = getInputStream(args);
      final JetsLexer l = new JetsLexer(new ANTLRInputStream(in));
      final JetsParser p = new JetsParser(new CommonTokenStream(l));
      p.addErrorListener(new JetsErrorListener());

      JetsVM jetsVM = new JetsVM();
      p.addParseListener(jetsVM);
      p.prog();

      System.out.println("Moved to " + jetsVM.getPosition());
    } catch (IOException ioe) {
      ioe.printStackTrace();
    }
  }

  private static InputStream getInputStream(final String[] args) throws FileNotFoundException {
    if (args.length == 0) {
      return System.in;
    } else {
      return new FileInputStream(new File(args[0]));
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
