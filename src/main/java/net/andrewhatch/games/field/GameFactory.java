package net.andrewhatch.games.field;

import net.andrewhatch.games.field.parser.FieldBaseListener;
import net.andrewhatch.games.field.parser.FieldLexer;
import net.andrewhatch.games.field.parser.FieldParser;
import org.antlr.v4.runtime.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class GameFactory {
    public Game createGame(final InputStream in) throws IOException {
        final FieldLexer l = new FieldLexer(new ANTLRInputStream(in));
        final FieldParser p = new FieldParser(new CommonTokenStream(l));
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

        final AtomicReference<String> name = new AtomicReference<>();
        final Map<String, Integer> points = new HashMap<>();
        final String[][] grid = new String[5][5];

        p.addParseListener(new FieldBaseListener() {
            int x;
            int y;

            @Override
            public void exitField(final FieldParser.FieldContext ctx) {
                name.set(ctx.name.getText());
            }

            @Override
            public void exitLocation(final FieldParser.LocationContext ctx) {
                x = Integer.parseInt(ctx.x.getText());
                y = Integer.parseInt(ctx.y.getText());
            }

            @Override
            public void exitBurial(final FieldParser.BurialContext ctx) {
                grid[x][y] = ctx.treasure.getText();
            }

            @Override
            public void exitPoints(final FieldParser.PointsContext ctx) {
                points.put(ctx.treasure.getText(), Integer.parseInt(ctx.value.getText()));
            }
        });
        p.field();

        return new Game(name.get(), points, grid);
    }
}