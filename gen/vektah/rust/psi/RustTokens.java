// This is a generated file. Not intended for manual editing.
package vektah.rust.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import vektah.rust.psi.impl.*;

public interface RustTokens {


  IElementType ARITHMETIC_OPERATORS_5_0 = new RustTokenType("arithmetic_operators_5_0");
  IElementType AS = new RustTokenType("AS");
  IElementType ASSIGN = new RustTokenType("ASSIGN");
  IElementType AT = new RustTokenType("AT");
  IElementType BINOP = new RustTokenType("BINOP");
  IElementType BIN_LIT = new RustTokenType("BIN_LIT");
  IElementType BITWISE_AND = new RustTokenType("BITWISE_AND");
  IElementType BITWISE_OR = new RustTokenType("BITWISE_OR");
  IElementType BITWISE_XOR = new RustTokenType("BITWISE_XOR");
  IElementType BLOCK_COMMENT = new RustTokenType("BLOCK_COMMENT");
  IElementType BOX = new RustTokenType("BOX");
  IElementType CHAR_LIT = new RustTokenType("CHAR_LIT");
  IElementType CLOSE_BRACE = new RustTokenType("CLOSE_BRACE");
  IElementType CLOSE_PAREN = new RustTokenType("CLOSE_PAREN");
  IElementType CLOSE_SQUARE_BRACKET = new RustTokenType("CLOSE_SQUARE_BRACKET");
  IElementType COLON = new RustTokenType("COLON");
  IElementType COMMA = new RustTokenType("COMMA");
  IElementType DEC_LIT = new RustTokenType("DEC_LIT");
  IElementType DIVIDE = new RustTokenType("DIVIDE");
  IElementType DOLLAR = new RustTokenType("DOLLAR");
  IElementType DOT = new RustTokenType("DOT");
  IElementType DOUBLE_COLON = new RustTokenType("DOUBLE_COLON");
  IElementType EQUAL = new RustTokenType("EQUAL");
  IElementType FAT_ARROW = new RustTokenType("FAT_ARROW");
  IElementType GREATER_THAN = new RustTokenType("GREATER_THAN");
  IElementType GREATER_THAN_OR_EQUAL = new RustTokenType("GREATER_THAN_OR_EQUAL");
  IElementType HASH = new RustTokenType("HASH");
  IElementType HEX_LIT = new RustTokenType("HEX_LIT");
  IElementType IDENTIFIER = new RustTokenType("IDENTIFIER");
  IElementType KEYWORD = new RustTokenType("KEYWORD");
  IElementType LEFT_SHIFT = new RustTokenType("LEFT_SHIFT");
  IElementType LESS_THAN = new RustTokenType("LESS_THAN");
  IElementType LESS_THAN_OR_EQUAL = new RustTokenType("LESS_THAN_OR_EQUAL");
  IElementType LINE_COMMENT = new RustTokenType("LINE_COMMENT");
  IElementType LOGICAL_AND = new RustTokenType("LOGICAL_AND");
  IElementType LOGICAL_OR = new RustTokenType("LOGICAL_OR");
  IElementType MINUS = new RustTokenType("MINUS");
  IElementType MULTIPLY = new RustTokenType("MULTIPLY");
  IElementType NOT = new RustTokenType("NOT");
  IElementType NOT_EQUAL = new RustTokenType("NOT_EQUAL");
  IElementType NUM_LIT = new RustTokenType("NUM_LIT");
  IElementType OCT_LIT = new RustTokenType("OCT_LIT");
  IElementType OPEN_BRACE = new RustTokenType("OPEN_BRACE");
  IElementType OPEN_PAREN = new RustTokenType("OPEN_PAREN");
  IElementType OPEN_SQUARE_BRACKET = new RustTokenType("OPEN_SQUARE_BRACKET");
  IElementType PATH = new RustTokenType("PATH");
  IElementType PLUS = new RustTokenType("PLUS");
  IElementType RAW_STRING_LIT = new RustTokenType("RAW_STRING_LIT");
  IElementType REMAINDER = new RustTokenType("REMAINDER");
  IElementType RIGHT_SHIFT = new RustTokenType("RIGHT_SHIFT");
  IElementType SEMICOLON = new RustTokenType("SEMICOLON");
  IElementType SINGLE_QUOTE = new RustTokenType("SINGLE_QUOTE");
  IElementType STRING_LIT = new RustTokenType("STRING_LIT");
  IElementType THIN_ARROW = new RustTokenType("THIN_ARROW");
  IElementType UNOP = new RustTokenType("UNOP");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
