package net.andrewhatch.languages.jets;

import org.antlr.v4.runtime.misc.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

class JetsVM extends JetsBaseListener {

  private static final Logger logger = LoggerFactory.getLogger(JetsVM.class);

  private int position = 0;

  @Inject
  public JetsVM() {
    logger.info("Starting JetsVM");
  }

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

  @Override public void exitDeclaration(@NotNull JetsParser.DeclarationContext ctx) {
    String variableName = ctx.Var().getText();
    String value = ctx.assignedValue.getText();
    logger.trace("variable {} assigned to {}", variableName, value);
  }


  int getPosition() {
    return position;
  }
}

