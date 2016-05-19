package net.andrewhatch.languages.jets;

import net.andrewhatch.languages.jets.model.Operator;
import net.andrewhatch.languages.jets.model.Operators;
import net.andrewhatch.languages.jets.model.SymbolTable;

import org.antlr.v4.runtime.misc.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

public class JetsVM extends JetsBaseListener {

  private static final Logger logger = LoggerFactory.getLogger(JetsVM.class);
  private final SymbolTable symbolTable;

  @Inject
  public JetsVM(final SymbolTable symbolTable) {
    logger.info("Starting JetsVM");
    this.symbolTable = symbolTable;
  }

  @Override public void exitDeclaration(@NotNull JetsParser.DeclarationContext ctx) {
    String variableName = ctx.Var().getText();
    String value = stripQuotes(ctx.assignedValue.getText());
    symbolTable.addSymbol(variableName, value);
  }

  @Override public void exitAssignment(@NotNull JetsParser.AssignmentContext ctx) {
    String variableName = ctx.Var().getText();
    String value = stripQuotes(ctx.assignedValue.getText());
    symbolTable.setSymbol(variableName, value);
  }

  @Override public void exitModifier(@NotNull JetsParser.ModifierContext ctx) {
    final String variableName = ctx.Var().getText();
    final String currentValue = symbolTable.getSymbol(variableName);
    final Operator operator = Operators.fromString(ctx.operator().getText());
    final String operand = stripQuotes(ctx.operand().getText());
    final String result = operator.apply(currentValue, operand);
    symbolTable.setSymbol(variableName, result);
  }

  @Override public void exitEcho(@NotNull JetsParser.EchoContext ctx) {
    final String variableName = ctx.Var().getText();
    final String value = symbolTable.getSymbol(variableName);
    System.out.println(value);
  }

  private String stripQuotes(final String text) {
    return text.substring(1, text.length() - 1);
  }
}

