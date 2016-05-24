package net.andrewhatch.languages.jets;

import net.andrewhatch.languages.jets.model.JetValue;
import net.andrewhatch.languages.jets.model.Operator;
import net.andrewhatch.languages.jets.model.Operators;
import net.andrewhatch.languages.jets.model.Symbol;
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
    String type = ctx.Type().getText();
    String variableName = ctx.Var().getText();
    String value = stripQuotes(ctx.assignedValue.getText());
    Symbol<?> symbol = createSymbol(type, variableName, value);
    symbolTable.addSymbol(symbol);
    logger.trace("variable {} assigned to {}", variableName, value);
  }

  private Symbol<?> createSymbol(final String type, final String variableName, final String value) {
    switch (variableName) {
      case "String": {
        return new Symbol<>(variableName, new JetValue<String>().withValue(value));
      }
      case "Integer": {
        return new Symbol<>(variableName, new JetValue<Integer>().withValue(Integer.valueOf(value)));
      }
      default:
        throw new RuntimeException("unknown type " + type);
    }
  }

  @Override public void exitAssignment(@NotNull JetsParser.AssignmentContext ctx) {
    String variableName = ctx.Var().getText();
    String stringValue = stripQuotes(ctx.assignedValue.getText());
    final Symbol symbol = symbolTable.getSymbol(variableName);
    symbol.setValue(getJetValue(symbol, stringValue));
  }

  private JetValue getJetValue(final Symbol symbol, final String stringValue) {
    if (symbol.getValue().getValue() instanceof Integer) {
      return new JetValue<Integer>().withValue(Integer.valueOf(stringValue));
    } else {
      return new JetValue<String>().withValue(stringValue);
    }
  }

  @Override public void exitModifier(@NotNull JetsParser.ModifierContext ctx) {
    final String variableName = ctx.Var().getText();
    final Symbol symbol = symbolTable.getSymbol(variableName);
    final Operator operator = Operators.fromString(ctx.operator().getText());
    final String operand = stripQuotes(ctx.operand().getText());
    final JetValue result = operator.apply(getJetValue(symbol, symbol.getValue().getValue().toString()), getJetValue(symbol, operand));
    symbol.setValue(result);
  }

  @Override public void exitEcho(@NotNull JetsParser.EchoContext ctx) {
    final String variableName = ctx.Var().getText();
    final String value = symbolTable.getSymbol(variableName).getValue().toString();
    System.out.println(value);
  }

  private String stripQuotes(final String text) {
    return text.substring(1, text.length() - 1);
  }
}

