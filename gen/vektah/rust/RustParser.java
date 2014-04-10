// This is a generated file. Not intended for manual editing.
package vektah.rust;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import com.intellij.openapi.diagnostic.Logger;
import static vektah.rust.psi.RustTokens.*;
import static com.intellij.lang.parser.GeneratedParserUtilBase.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class RustParser implements PsiParser {

  public static final Logger LOG_ = Logger.getInstance("vektah.rust.RustParser");

  public ASTNode parse(IElementType root_, PsiBuilder builder_) {
    boolean result_;
    builder_ = adapt_builder_(root_, builder_, this, null);
    Marker marker_ = enter_section_(builder_, 0, _COLLAPSE_, null);
    if (root_ == PATH) {
      result_ = path(builder_, 0);
    }
    else if (root_ == TYPE) {
      result_ = type(builder_, 0);
    }
    else if (root_ == USE) {
      result_ = use(builder_, 0);
    }
    else {
      result_ = parse_root_(root_, builder_, 0);
    }
    exit_section_(builder_, 0, marker_, root_, result_, true, TRUE_CONDITION);
    return builder_.getTreeBuilt();
  }

  protected boolean parse_root_(final IElementType root_, final PsiBuilder builder_, final int level_) {
    return simpleFile(builder_, level_ + 1);
  }

  /* ********************************************************** */
  // PLUS|MINUS|MULTIPLY|DIVIDE|REMAINDER|
  static boolean arithmetic_operators(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "arithmetic_operators")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, PLUS);
    if (!result_) result_ = consumeToken(builder_, MINUS);
    if (!result_) result_ = consumeToken(builder_, MULTIPLY);
    if (!result_) result_ = consumeToken(builder_, DIVIDE);
    if (!result_) result_ = consumeToken(builder_, REMAINDER);
    if (!result_) result_ = consumeToken(builder_, ARITHMETIC_OPERATORS_5_0);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // BITWISE_AND|BITWISE_OR|BITWISE_XOR|LEFT_SHIFT|RIGHT_SHIFT
  static boolean bitwise_operators(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "bitwise_operators")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, BITWISE_AND);
    if (!result_) result_ = consumeToken(builder_, BITWISE_OR);
    if (!result_) result_ = consumeToken(builder_, BITWISE_XOR);
    if (!result_) result_ = consumeToken(builder_, LEFT_SHIFT);
    if (!result_) result_ = consumeToken(builder_, RIGHT_SHIFT);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // LOGICAL_OR|LOGICAL_AND
  static boolean boolean_operators(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "boolean_operators")) return false;
    if (!nextTokenIs(builder_, "", LOGICAL_AND, LOGICAL_OR)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, LOGICAL_OR);
    if (!result_) result_ = consumeToken(builder_, LOGICAL_AND);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // BLOCK_COMMENT|LINE_COMMENT
  static boolean comment(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "comment")) return false;
    if (!nextTokenIs(builder_, "", BLOCK_COMMENT, LINE_COMMENT)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, BLOCK_COMMENT);
    if (!result_) result_ = consumeToken(builder_, LINE_COMMENT);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // EQUAL|GREATER_THAN|LESS_THAN|NOT_EQUAL|GREATER_THAN_OR_EQUAL|LESS_THAN_OR_EQUAL
  static boolean comparison_operators(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "comparison_operators")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, EQUAL);
    if (!result_) result_ = consumeToken(builder_, GREATER_THAN);
    if (!result_) result_ = consumeToken(builder_, LESS_THAN);
    if (!result_) result_ = consumeToken(builder_, NOT_EQUAL);
    if (!result_) result_ = consumeToken(builder_, GREATER_THAN_OR_EQUAL);
    if (!result_) result_ = consumeToken(builder_, LESS_THAN_OR_EQUAL);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // KW_AS|KW_BREAK|KW_CRATE|KW_ELSE|KW_ENUM|KW_EXTERN|KW_FALSE|KW_FN|KW_FOR|KW_IF|KW_IMPL|KW_IN|KW_LET|KW_LOOP|KW_MATCH|KW_MOD|KW_MUT|KW_PRIV|KW_PROC|KW_PUB|KW_REF|KW_RETURN|KW_SELF|KW_STATIC|KW_STRUCT|KW_SUPER|KW_TRUE|KW_TRAIT|KW_TYPE|KW_UNSAFE|KW_WHILE
  static boolean keyword(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "keyword")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, KW_AS);
    if (!result_) result_ = consumeToken(builder_, KW_BREAK);
    if (!result_) result_ = consumeToken(builder_, KW_CRATE);
    if (!result_) result_ = consumeToken(builder_, KW_ELSE);
    if (!result_) result_ = consumeToken(builder_, KW_ENUM);
    if (!result_) result_ = consumeToken(builder_, KW_EXTERN);
    if (!result_) result_ = consumeToken(builder_, KW_FALSE);
    if (!result_) result_ = consumeToken(builder_, KW_FN);
    if (!result_) result_ = consumeToken(builder_, KW_FOR);
    if (!result_) result_ = consumeToken(builder_, KW_IF);
    if (!result_) result_ = consumeToken(builder_, KW_IMPL);
    if (!result_) result_ = consumeToken(builder_, KW_IN);
    if (!result_) result_ = consumeToken(builder_, KW_LET);
    if (!result_) result_ = consumeToken(builder_, KW_LOOP);
    if (!result_) result_ = consumeToken(builder_, KW_MATCH);
    if (!result_) result_ = consumeToken(builder_, KW_MOD);
    if (!result_) result_ = consumeToken(builder_, KW_MUT);
    if (!result_) result_ = consumeToken(builder_, KW_PRIV);
    if (!result_) result_ = consumeToken(builder_, KW_PROC);
    if (!result_) result_ = consumeToken(builder_, KW_PUB);
    if (!result_) result_ = consumeToken(builder_, KW_REF);
    if (!result_) result_ = consumeToken(builder_, KW_RETURN);
    if (!result_) result_ = consumeToken(builder_, KW_SELF);
    if (!result_) result_ = consumeToken(builder_, KW_STATIC);
    if (!result_) result_ = consumeToken(builder_, KW_STRUCT);
    if (!result_) result_ = consumeToken(builder_, KW_SUPER);
    if (!result_) result_ = consumeToken(builder_, KW_TRUE);
    if (!result_) result_ = consumeToken(builder_, KW_TRAIT);
    if (!result_) result_ = consumeToken(builder_, KW_TYPE);
    if (!result_) result_ = consumeToken(builder_, KW_UNSAFE);
    if (!result_) result_ = consumeToken(builder_, KW_WHILE);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // STRING_LIT|RAW_STRING_LIT|CHAR_LIT|NUM_LIT|DEC_LIT|BIN_LIT|OCT_LIT|HEX_LIT
  static boolean literal(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "literal")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, STRING_LIT);
    if (!result_) result_ = consumeToken(builder_, RAW_STRING_LIT);
    if (!result_) result_ = consumeToken(builder_, CHAR_LIT);
    if (!result_) result_ = consumeToken(builder_, NUM_LIT);
    if (!result_) result_ = consumeToken(builder_, DEC_LIT);
    if (!result_) result_ = consumeToken(builder_, BIN_LIT);
    if (!result_) result_ = consumeToken(builder_, OCT_LIT);
    if (!result_) result_ = consumeToken(builder_, HEX_LIT);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // !(SEMICOLON)
  static boolean not_semicolon(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "not_semicolon")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NOT_, null);
    result_ = !consumeToken(builder_, SEMICOLON);
    exit_section_(builder_, level_, marker_, null, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // arithmetic_operators|bitwise_operators|boolean_operators|comparison_operators|unary_operators|AS|ASSIGN
  static boolean operators(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "operators")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = arithmetic_operators(builder_, level_ + 1);
    if (!result_) result_ = bitwise_operators(builder_, level_ + 1);
    if (!result_) result_ = boolean_operators(builder_, level_ + 1);
    if (!result_) result_ = comparison_operators(builder_, level_ + 1);
    if (!result_) result_ = unary_operators(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, AS);
    if (!result_) result_ = consumeToken(builder_, ASSIGN);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // IDENTIFIER (
  // 	'::' (
  // 		'<' type (',' type)* '>' |
  // 		path
  // 	)
  // )?
  public static boolean path(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "path")) return false;
    if (!nextTokenIs(builder_, IDENTIFIER)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, IDENTIFIER);
    result_ = result_ && path_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, PATH, result_);
    return result_;
  }

  // (
  // 	'::' (
  // 		'<' type (',' type)* '>' |
  // 		path
  // 	)
  // )?
  private static boolean path_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "path_1")) return false;
    path_1_0(builder_, level_ + 1);
    return true;
  }

  // '::' (
  // 		'<' type (',' type)* '>' |
  // 		path
  // 	)
  private static boolean path_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "path_1_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, "::");
    result_ = result_ && path_1_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // '<' type (',' type)* '>' |
  // 		path
  private static boolean path_1_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "path_1_0_1")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = path_1_0_1_0(builder_, level_ + 1);
    if (!result_) result_ = path(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // '<' type (',' type)* '>'
  private static boolean path_1_0_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "path_1_0_1_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, "<");
    result_ = result_ && type(builder_, level_ + 1);
    result_ = result_ && path_1_0_1_0_2(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, ">");
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (',' type)*
  private static boolean path_1_0_1_0_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "path_1_0_1_0_2")) return false;
    int pos_ = current_position_(builder_);
    while (true) {
      if (!path_1_0_1_0_2_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "path_1_0_1_0_2", pos_)) break;
      pos_ = current_position_(builder_);
    }
    return true;
  }

  // ',' type
  private static boolean path_1_0_1_0_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "path_1_0_1_0_2_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, ",");
    result_ = result_ && type(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // IDENTIFIER ( '::' path_glob)? |
  // 	'*' |
  // 	'{' IDENTIFIER ( ',' IDENTIFIER )* '}'
  static boolean path_glob(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "path_glob")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = path_glob_0(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, "*");
    if (!result_) result_ = path_glob_2(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // IDENTIFIER ( '::' path_glob)?
  private static boolean path_glob_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "path_glob_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, IDENTIFIER);
    result_ = result_ && path_glob_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // ( '::' path_glob)?
  private static boolean path_glob_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "path_glob_0_1")) return false;
    path_glob_0_1_0(builder_, level_ + 1);
    return true;
  }

  // '::' path_glob
  private static boolean path_glob_0_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "path_glob_0_1_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, "::");
    result_ = result_ && path_glob(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // '{' IDENTIFIER ( ',' IDENTIFIER )* '}'
  private static boolean path_glob_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "path_glob_2")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, "{");
    result_ = result_ && consumeToken(builder_, IDENTIFIER);
    result_ = result_ && path_glob_2_2(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, "}");
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // ( ',' IDENTIFIER )*
  private static boolean path_glob_2_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "path_glob_2_2")) return false;
    int pos_ = current_position_(builder_);
    while (true) {
      if (!path_glob_2_2_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "path_glob_2_2", pos_)) break;
      pos_ = current_position_(builder_);
    }
    return true;
  }

  // ',' IDENTIFIER
  private static boolean path_glob_2_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "path_glob_2_2_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, ",");
    result_ = result_ && consumeToken(builder_, IDENTIFIER);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // ( use
  // 	| token
  // )*
  static boolean simpleFile(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "simpleFile")) return false;
    int pos_ = current_position_(builder_);
    while (true) {
      if (!simpleFile_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "simpleFile", pos_)) break;
      pos_ = current_position_(builder_);
    }
    return true;
  }

  // use
  // 	| token
  private static boolean simpleFile_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "simpleFile_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = use(builder_, level_ + 1);
    if (!result_) result_ = token(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // UNOP|BINOP
  static boolean simple_token(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "simple_token")) return false;
    if (!nextTokenIs(builder_, "", BINOP, UNOP)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, UNOP);
    if (!result_) result_ = consumeToken(builder_, BINOP);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // HASH|OPEN_SQUARE_BRACKET|CLOSE_SQUARE_BRACKET|OPEN_PAREN|CLOSE_PAREN|OPEN_BRACE|CLOSE_BRACE|COMMA|COLON|SEMICOLON|DOUBLE_COLON|THIN_ARROW|FAT_ARROW|DOT|AT|SINGLE_QUOTE|DOLLAR
  static boolean symbol(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "symbol")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, HASH);
    if (!result_) result_ = consumeToken(builder_, OPEN_SQUARE_BRACKET);
    if (!result_) result_ = consumeToken(builder_, CLOSE_SQUARE_BRACKET);
    if (!result_) result_ = consumeToken(builder_, OPEN_PAREN);
    if (!result_) result_ = consumeToken(builder_, CLOSE_PAREN);
    if (!result_) result_ = consumeToken(builder_, OPEN_BRACE);
    if (!result_) result_ = consumeToken(builder_, CLOSE_BRACE);
    if (!result_) result_ = consumeToken(builder_, COMMA);
    if (!result_) result_ = consumeToken(builder_, COLON);
    if (!result_) result_ = consumeToken(builder_, SEMICOLON);
    if (!result_) result_ = consumeToken(builder_, DOUBLE_COLON);
    if (!result_) result_ = consumeToken(builder_, THIN_ARROW);
    if (!result_) result_ = consumeToken(builder_, FAT_ARROW);
    if (!result_) result_ = consumeToken(builder_, DOT);
    if (!result_) result_ = consumeToken(builder_, AT);
    if (!result_) result_ = consumeToken(builder_, SINGLE_QUOTE);
    if (!result_) result_ = consumeToken(builder_, DOLLAR);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // simple_token|literal|comment|symbol|operators|keyword|IDENTIFIER
  static boolean token(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "token")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = simple_token(builder_, level_ + 1);
    if (!result_) result_ = literal(builder_, level_ + 1);
    if (!result_) result_ = comment(builder_, level_ + 1);
    if (!result_) result_ = symbol(builder_, level_ + 1);
    if (!result_) result_ = operators(builder_, level_ + 1);
    if (!result_) result_ = keyword(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, IDENTIFIER);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // IDENTIFIER (
  // 	'<' type (',' type)* '>' |
  // 	'::' type
  // )+
  public static boolean type(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "type")) return false;
    if (!nextTokenIs(builder_, IDENTIFIER)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, IDENTIFIER);
    result_ = result_ && type_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, TYPE, result_);
    return result_;
  }

  // (
  // 	'<' type (',' type)* '>' |
  // 	'::' type
  // )+
  private static boolean type_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "type_1")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = type_1_0(builder_, level_ + 1);
    int pos_ = current_position_(builder_);
    while (result_) {
      if (!type_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "type_1", pos_)) break;
      pos_ = current_position_(builder_);
    }
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // '<' type (',' type)* '>' |
  // 	'::' type
  private static boolean type_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "type_1_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = type_1_0_0(builder_, level_ + 1);
    if (!result_) result_ = type_1_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // '<' type (',' type)* '>'
  private static boolean type_1_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "type_1_0_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, "<");
    result_ = result_ && type(builder_, level_ + 1);
    result_ = result_ && type_1_0_0_2(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, ">");
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (',' type)*
  private static boolean type_1_0_0_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "type_1_0_0_2")) return false;
    int pos_ = current_position_(builder_);
    while (true) {
      if (!type_1_0_0_2_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "type_1_0_0_2", pos_)) break;
      pos_ = current_position_(builder_);
    }
    return true;
  }

  // ',' type
  private static boolean type_1_0_0_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "type_1_0_0_2_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, ",");
    result_ = result_ && type(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // '::' type
  private static boolean type_1_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "type_1_0_1")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, "::");
    result_ = result_ && type(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // NOT|BOX
  static boolean unary_operators(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "unary_operators")) return false;
    if (!nextTokenIs(builder_, "", BOX, NOT)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, NOT);
    if (!result_) result_ = consumeToken(builder_, BOX);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // ['pub'] 'use' use_part ';'
  public static boolean use(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "use")) return false;
    if (!nextTokenIs(builder_, "<use>", KW_PUB, KW_USE)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<use>");
    result_ = use_0(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, KW_USE);
    result_ = result_ && use_part(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, ";");
    exit_section_(builder_, level_, marker_, USE, result_, false, null);
    return result_;
  }

  // ['pub']
  private static boolean use_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "use_0")) return false;
    consumeToken(builder_, KW_PUB);
    return true;
  }

  /* ********************************************************** */
  // IDENTIFIER [
  // 	'=' path |
  // 	'::' path_glob
  // ]
  static boolean use_part(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "use_part")) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = consumeToken(builder_, IDENTIFIER);
    pinned_ = result_; // pin = 1
    result_ = result_ && use_part_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, null, result_, pinned_, not_semicolon_parser_);
    return result_ || pinned_;
  }

  // [
  // 	'=' path |
  // 	'::' path_glob
  // ]
  private static boolean use_part_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "use_part_1")) return false;
    use_part_1_0(builder_, level_ + 1);
    return true;
  }

  // '=' path |
  // 	'::' path_glob
  private static boolean use_part_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "use_part_1_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = use_part_1_0_0(builder_, level_ + 1);
    if (!result_) result_ = use_part_1_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // '=' path
  private static boolean use_part_1_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "use_part_1_0_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, "=");
    result_ = result_ && path(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // '::' path_glob
  private static boolean use_part_1_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "use_part_1_0_1")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, "::");
    result_ = result_ && path_glob(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  final static Parser not_semicolon_parser_ = new Parser() {
    public boolean parse(PsiBuilder builder_, int level_) {
      return not_semicolon(builder_, level_ + 1);
    }
  };
}
