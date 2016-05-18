package net.andrewhatch.languages.jets;

import org.antlr.v4.runtime.misc.NotNull;

public class JetsVM extends JetsBaseListener {

    private int position = 0;

    @Override
    public void exitFd(@NotNull JetsParser.FdContext ctx) {
        JetsParser.NumberContext number = ctx.number();
        position += Integer.valueOf(number.getText());
    }

    @Override
    public void exitBk(@NotNull JetsParser.BkContext ctx) {
        JetsParser.NumberContext number = ctx.number();
        position -= Integer.valueOf(number.getText());
    }

    public int getPosition() {
        return position;
    }
}

