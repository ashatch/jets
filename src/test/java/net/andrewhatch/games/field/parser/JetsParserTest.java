package net.andrewhatch.games.field.parser;

import net.andrewhatch.languages.jets.JetsLexer;
import net.andrewhatch.languages.jets.JetsParser;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.junit.Test;

public class JetsParserTest {
  @Test
  public void testExample() throws Exception {
    JetsLexer l = new JetsLexer(new ANTLRInputStream(getClass().getResourceAsStream("/example.jets")));
    JetsParser p = new JetsParser(new CommonTokenStream(l));
    p.addErrorListener(new BaseErrorListener() {
      @Override
      public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
        throw new IllegalStateException("failed to parse at line " + line + " due to " + msg, e);
      }
    });
    p.parse();
  }
}
