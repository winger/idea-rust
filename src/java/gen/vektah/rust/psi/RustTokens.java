// This is a generated file. Not intended for manual editing.
package vektah.rust.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import vektah.rust.psi.impl.*;

public interface RustTokens {

  IElementType EXPRESSION = new RustTokenType("EXPRESSION");
  IElementType EXTERN = new RustTokenType("EXTERN");
  IElementType EXTERN_INTERNAL = new RustTokenType("EXTERN_INTERNAL");
  IElementType FUNCTION = new RustTokenType("FUNCTION");
  IElementType FUNCTION_DECLARATION = new RustTokenType("FUNCTION_DECLARATION");
  IElementType FUNCTION_TYPE = new RustTokenType("FUNCTION_TYPE");
  IElementType GENERIC = new RustTokenType("GENERIC");
  IElementType GENERIC_PARAMS = new RustTokenType("GENERIC_PARAMS");
  IElementType LET = new RustTokenType("LET");
  IElementType LIFETIME = new RustTokenType("LIFETIME");
  IElementType PATH = new RustTokenType("PATH");
  IElementType REF = new RustTokenType("REF");
  IElementType SELF_ARG = new RustTokenType("SELF_ARG");
  IElementType STANDARD_ARG = new RustTokenType("STANDARD_ARG");
  IElementType STATEMENT_BLOCK = new RustTokenType("STATEMENT_BLOCK");
  IElementType STATEMENT_BLOCK_INTERNAL = new RustTokenType("STATEMENT_BLOCK_INTERNAL");
  IElementType STATEMENT_WILDCARD = new RustTokenType("STATEMENT_WILDCARD");
  IElementType STRUCT = new RustTokenType("STRUCT");
  IElementType STRUCT_BODY = new RustTokenType("STRUCT_BODY");
  IElementType STRUCT_PROPERTY = new RustTokenType("STRUCT_PROPERTY");
  IElementType STRUCT_RECOVER = new RustTokenType("STRUCT_RECOVER");
  IElementType TRAIT = new RustTokenType("TRAIT");
  IElementType TUPLE_ARG = new RustTokenType("TUPLE_ARG");
  IElementType TYPE_BASIC = new RustTokenType("TYPE_BASIC");
  IElementType TYPE_CLOSURE = new RustTokenType("TYPE_CLOSURE");
  IElementType TYPE_DEFINITION = new RustTokenType("TYPE_DEFINITION");
  IElementType TYPE_NONE = new RustTokenType("TYPE_NONE");
  IElementType TYPE_PROC = new RustTokenType("TYPE_PROC");
  IElementType TYPE_TUPLE = new RustTokenType("TYPE_TUPLE");
  IElementType USE = new RustTokenType("USE");

  IElementType AS = new RustTokenType("AS");
  IElementType ASSIGN = new RustTokenType("=");
  IElementType AT = new RustTokenType("@");
  IElementType BINOP = new RustTokenType("BINOP");
  IElementType BIN_LIT = new RustTokenType("BIN_LIT");
  IElementType BITWISE_AND = new RustTokenType("&");
  IElementType BITWISE_OR = new RustTokenType("|");
  IElementType BITWISE_XOR = new RustTokenType("^");
  IElementType BLOCK_COMMENT = new RustTokenType("BLOCK_COMMENT");
  IElementType BOX = new RustTokenType("~");
  IElementType CHAR_LIT = new RustTokenType("CHAR_LIT");
  IElementType CLOSE_BRACE = new RustTokenType("}");
  IElementType CLOSE_PAREN = new RustTokenType(")");
  IElementType CLOSE_SQUARE_BRACKET = new RustTokenType("]");
  IElementType COLON = new RustTokenType(":");
  IElementType COMMA = new RustTokenType(",");
  IElementType DEC_LIT = new RustTokenType("DEC_LIT");
  IElementType DIVIDE = new RustTokenType("/");
  IElementType DOLLAR = new RustTokenType("$");
  IElementType DOT = new RustTokenType(".");
  IElementType DOUBLE_COLON = new RustTokenType("::");
  IElementType EQUAL = new RustTokenType("==");
  IElementType FAT_ARROW = new RustTokenType("=>");
  IElementType GREATER_THAN = new RustTokenType(">");
  IElementType GREATER_THAN_OR_EQUAL = new RustTokenType(">=");
  IElementType HASH = new RustTokenType("#");
  IElementType HEX_LIT = new RustTokenType("HEX_LIT");
  IElementType IDENTIFIER = new RustTokenType("IDENTIFIER");
  IElementType KW_AS = new RustTokenType("as");
  IElementType KW_BREAK = new RustTokenType("break");
  IElementType KW_CRATE = new RustTokenType("crate");
  IElementType KW_ELSE = new RustTokenType("else");
  IElementType KW_ENUM = new RustTokenType("enum");
  IElementType KW_EXTERN = new RustTokenType("extern");
  IElementType KW_FALSE = new RustTokenType("false");
  IElementType KW_FN = new RustTokenType("fn");
  IElementType KW_FOR = new RustTokenType("for");
  IElementType KW_IF = new RustTokenType("if");
  IElementType KW_IMPL = new RustTokenType("impl");
  IElementType KW_IN = new RustTokenType("in");
  IElementType KW_LET = new RustTokenType("let");
  IElementType KW_LOOP = new RustTokenType("loop");
  IElementType KW_MATCH = new RustTokenType("match");
  IElementType KW_MOD = new RustTokenType("mod");
  IElementType KW_MUT = new RustTokenType("mut");
  IElementType KW_PRIV = new RustTokenType("priv");
  IElementType KW_PROC = new RustTokenType("proc");
  IElementType KW_PUB = new RustTokenType("pub");
  IElementType KW_REF = new RustTokenType("ref");
  IElementType KW_RETURN = new RustTokenType("return");
  IElementType KW_SELF = new RustTokenType("self");
  IElementType KW_STATIC = new RustTokenType("static");
  IElementType KW_STRUCT = new RustTokenType("struct");
  IElementType KW_SUPER = new RustTokenType("super");
  IElementType KW_TRAIT = new RustTokenType("trait");
  IElementType KW_TRUE = new RustTokenType("true");
  IElementType KW_TYPE = new RustTokenType("type");
  IElementType KW_UNSAFE = new RustTokenType("unsafe");
  IElementType KW_USE = new RustTokenType("use");
  IElementType KW_WHILE = new RustTokenType("while");
  IElementType LESS_THAN = new RustTokenType("<");
  IElementType LESS_THAN_OR_EQUAL = new RustTokenType("<=");
  IElementType LINE_COMMENT = new RustTokenType("LINE_COMMENT");
  IElementType MINUS = new RustTokenType("-");
  IElementType MULTIPLY = new RustTokenType("*");
  IElementType NOT = new RustTokenType("!");
  IElementType NOT_EQUAL = new RustTokenType("!=");
  IElementType NUM_LIT = new RustTokenType("NUM_LIT");
  IElementType OCT_LIT = new RustTokenType("OCT_LIT");
  IElementType OPEN_BRACE = new RustTokenType("{");
  IElementType OPEN_PAREN = new RustTokenType("(");
  IElementType OPEN_SQUARE_BRACKET = new RustTokenType("[");
  IElementType PLUS = new RustTokenType("+");
  IElementType RAW_STRING_LIT = new RustTokenType("RAW_STRING_LIT");
  IElementType REMAINDER = new RustTokenType("%");
  IElementType SEMICOLON = new RustTokenType(";");
  IElementType SINGLE_QUOTE = new RustTokenType("'");
  IElementType STRING_LIT = new RustTokenType("STRING_LIT");
  IElementType THIN_ARROW = new RustTokenType("->");
  IElementType UNOP = new RustTokenType("UNOP");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
       if (type == EXPRESSION) {
        return new RustExpressionImpl(node);
      }
      else if (type == EXTERN) {
        return new RustExternImpl(node);
      }
      else if (type == EXTERN_INTERNAL) {
        return new RustExternInternalImpl(node);
      }
      else if (type == FUNCTION) {
        return new RustFunctionImpl(node);
      }
      else if (type == FUNCTION_DECLARATION) {
        return new RustFunctionDeclarationImpl(node);
      }
      else if (type == FUNCTION_TYPE) {
        return new RustFunctionTypeImpl(node);
      }
      else if (type == GENERIC) {
        return new RustGenericImpl(node);
      }
      else if (type == GENERIC_PARAMS) {
        return new RustGenericParamsImpl(node);
      }
      else if (type == LET) {
        return new RustLetImpl(node);
      }
      else if (type == LIFETIME) {
        return new RustLifetimeImpl(node);
      }
      else if (type == PATH) {
        return new RustPathImpl(node);
      }
      else if (type == REF) {
        return new RustRefImpl(node);
      }
      else if (type == SELF_ARG) {
        return new RustSelfArgImpl(node);
      }
      else if (type == STANDARD_ARG) {
        return new RustStandardArgImpl(node);
      }
      else if (type == STATEMENT_BLOCK) {
        return new RustStatementBlockImpl(node);
      }
      else if (type == STATEMENT_BLOCK_INTERNAL) {
        return new RustStatementBlockInternalImpl(node);
      }
      else if (type == STATEMENT_WILDCARD) {
        return new RustStatementWildcardImpl(node);
      }
      else if (type == STRUCT) {
        return new RustStructImpl(node);
      }
      else if (type == STRUCT_BODY) {
        return new RustStructBodyImpl(node);
      }
      else if (type == STRUCT_PROPERTY) {
        return new RustStructPropertyImpl(node);
      }
      else if (type == STRUCT_RECOVER) {
        return new RustStructRecoverImpl(node);
      }
      else if (type == TRAIT) {
        return new RustTraitImpl(node);
      }
      else if (type == TUPLE_ARG) {
        return new RustTupleArgImpl(node);
      }
      else if (type == TYPE_BASIC) {
        return new RustTypeBasicImpl(node);
      }
      else if (type == TYPE_CLOSURE) {
        return new RustTypeClosureImpl(node);
      }
      else if (type == TYPE_DEFINITION) {
        return new RustTypeDefinitionImpl(node);
      }
      else if (type == TYPE_NONE) {
        return new RustTypeNoneImpl(node);
      }
      else if (type == TYPE_PROC) {
        return new RustTypeProcImpl(node);
      }
      else if (type == TYPE_TUPLE) {
        return new RustTypeTupleImpl(node);
      }
      else if (type == USE) {
        return new RustUseImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
