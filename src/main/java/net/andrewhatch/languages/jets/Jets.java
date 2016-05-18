package net.andrewhatch.languages.jets;

import org.antlr.v4.runtime.*;

import java.io.*;


public class Jets {
    public static void main(final String[] args) throws IOException {

        final InputStream in = getInputStream(args);
        final JetsLexer l = new JetsLexer(new ANTLRInputStream(in));
        final JetsParser p = new JetsParser(new CommonTokenStream(l));

        p.addErrorListener(new BaseErrorListener() {
            @Override
            public void syntaxError(final Recognizer<?, ?> recognizer,
                                    final Object offendingSymbol,
                                    final int line,
                                    final int charPositionInLine,
                                    final String msg,
                                    final RecognitionException e) {
                throw new IllegalStateException("failed to parse at line " + line + " due to " + msg, e);
            }
        });

        JetsVM jetsVM = new JetsVM();
        p.addParseListener(jetsVM);
        p.prog();

        System.out.println("Moved to " + jetsVM.getPosition());
    }

    private static InputStream getInputStream(final String[] args) throws FileNotFoundException {
        if (args.length == 0) {
            return System.in;
        } else {
            return new FileInputStream(new File(args[0]));
        }
    }
}
