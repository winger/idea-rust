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
    if (root_ == ATTRIBUTE) {
      result_ = attribute(builder_, 0);
    }
    else if (root_ == ATTRIBUTE_ARG) {
      result_ = attribute_arg(builder_, 0);
    }
    else if (root_ == EXPRESSION) {
      result_ = expression(builder_, 0);
    }
    else if (root_ == EXTERN_BLOCK) {
      result_ = extern_block(builder_, 0);
    }
    else if (root_ == EXTERN_CRATE) {
      result_ = extern_crate(builder_, 0);
    }
    else if (root_ == FUNCTION) {
      result_ = function(builder_, 0);
    }
    else if (root_ == FUNCTION_DECLARATION) {
      result_ = function_declaration(builder_, 0);
    }
    else if (root_ == FUNCTION_TYPE) {
      result_ = function_type(builder_, 0);
    }
    else if (root_ == GENERIC) {
      result_ = generic(builder_, 0);
    }
    else if (root_ == GENERIC_PARAMS) {
      result_ = generic_params(builder_, 0);
    }
    else if (root_ == LET) {
      result_ = let(builder_, 0);
    }
    else if (root_ == LIFETIME) {
      result_ = lifetime(builder_, 0);
    }
    else if (root_ == PATH) {
      result_ = path(builder_, 0);
    }
    else if (root_ == REF) {
      result_ = ref(builder_, 0);
    }
    else if (root_ == SELF_ARG) {
      result_ = self_arg(builder_, 0);
    }
    else if (root_ == STANDARD_ARG) {
      result_ = standard_arg(builder_, 0);
    }
    else if (root_ == STATEMENT_BLOCK) {
      result_ = statement_block(builder_, 0);
    }
    else if (root_ == STATEMENT_BLOCK_INTERNAL) {
      result_ = statement_block_internal(builder_, 0);
    }
    else if (root_ == STATEMENT_WILDCARD) {
      result_ = statement_wildcard(builder_, 0);
    }
    else if (root_ == STRUCT) {
      result_ = struct(builder_, 0);
    }
    else if (root_ == STRUCT_BODY) {
      result_ = struct_body(builder_, 0);
    }
    else if (root_ == STRUCT_PROPERTY) {
      result_ = struct_property(builder_, 0);
    }
    else if (root_ == TRAIT) {
      result_ = trait(builder_, 0);
    }
    else if (root_ == TRAIT_FUNCTION_DECLARATION) {
      result_ = trait_function_declaration(builder_, 0);
    }
    else if (root_ == TUPLE_ARG) {
      result_ = tuple_arg(builder_, 0);
    }
    else if (root_ == TUPLE_BODY) {
      result_ = tuple_body(builder_, 0);
    }
    else if (root_ == TYPE_BASIC) {
      result_ = type_basic(builder_, 0);
    }
    else if (root_ == TYPE_CLOSURE) {
      result_ = type_closure(builder_, 0);
    }
    else if (root_ == TYPE_DEFINITION) {
      result_ = type_definition(builder_, 0);
    }
    else if (root_ == TYPE_NONE) {
      result_ = type_none(builder_, 0);
    }
    else if (root_ == TYPE_PROC) {
      result_ = type_proc(builder_, 0);
    }
    else if (root_ == TYPE_TUPLE) {
      result_ = type_tuple(builder_, 0);
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
  // self_arg | standard_arg | tuple_arg
  static boolean arg(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "arg")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = self_arg(builder_, level_ + 1);
    if (!result_) result_ = standard_arg(builder_, level_ + 1);
    if (!result_) result_ = tuple_arg(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // PLUS|MINUS|MULTIPLY|DIVIDE|REMAINDER
  static boolean arithmetic_operators(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "arithmetic_operators")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, PLUS);
    if (!result_) result_ = consumeToken(builder_, MINUS);
    if (!result_) result_ = consumeToken(builder_, MULTIPLY);
    if (!result_) result_ = consumeToken(builder_, DIVIDE);
    if (!result_) result_ = consumeToken(builder_, REMAINDER);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // attribute_internal ']'
  public static boolean attribute(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute")) return false;
    if (!nextTokenIs(builder_, HASH)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = attribute_internal(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, CLOSE_SQUARE_BRACKET);
    exit_section_(builder_, marker_, ATTRIBUTE, result_);
    return result_;
  }

  /* ********************************************************** */
  // IDENTIFIER [ '=' literal | '(' attribute_list ')' ]
  public static boolean attribute_arg(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_arg")) return false;
    if (!nextTokenIs(builder_, IDENTIFIER)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, IDENTIFIER);
    result_ = result_ && attribute_arg_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, ATTRIBUTE_ARG, result_);
    return result_;
  }

  // [ '=' literal | '(' attribute_list ')' ]
  private static boolean attribute_arg_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_arg_1")) return false;
    attribute_arg_1_0(builder_, level_ + 1);
    return true;
  }

  // '=' literal | '(' attribute_list ')'
  private static boolean attribute_arg_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_arg_1_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = attribute_arg_1_0_0(builder_, level_ + 1);
    if (!result_) result_ = attribute_arg_1_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // '=' literal
  private static boolean attribute_arg_1_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_arg_1_0_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, ASSIGN);
    result_ = result_ && literal(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // '(' attribute_list ')'
  private static boolean attribute_arg_1_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_arg_1_0_1")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, OPEN_PAREN);
    result_ = result_ && attribute_list(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, CLOSE_PAREN);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // '#' '!'? '[' attribute_list
  static boolean attribute_internal(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_internal")) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = consumeToken(builder_, HASH);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, attribute_internal_1(builder_, level_ + 1));
    result_ = pinned_ && report_error_(builder_, consumeToken(builder_, OPEN_SQUARE_BRACKET)) && result_;
    result_ = pinned_ && attribute_list(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, null, result_, pinned_, not_close_square_bracket_parser_);
    return result_ || pinned_;
  }

  // '!'?
  private static boolean attribute_internal_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_internal_1")) return false;
    consumeToken(builder_, NOT);
    return true;
  }

  /* ********************************************************** */
  // attribute_arg ( ',' attribute_arg )*
  static boolean attribute_list(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_list")) return false;
    if (!nextTokenIs(builder_, IDENTIFIER)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = attribute_arg(builder_, level_ + 1);
    result_ = result_ && attribute_list_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // ( ',' attribute_arg )*
  private static boolean attribute_list_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_list_1")) return false;
    int pos_ = current_position_(builder_);
    while (true) {
      if (!attribute_list_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "attribute_list_1", pos_)) break;
      pos_ = current_position_(builder_);
    }
    return true;
  }

  // ',' attribute_arg
  private static boolean attribute_list_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_list_1_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COMMA);
    result_ = result_ && attribute_arg(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // BITWISE_AND|BITWISE_OR|BITWISE_XOR
  static boolean bitwise_operators(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "bitwise_operators")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, BITWISE_AND);
    if (!result_) result_ = consumeToken(builder_, BITWISE_OR);
    if (!result_) result_ = consumeToken(builder_, BITWISE_XOR);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // OPEN_SQUARE_BRACKET|CLOSE_SQUARE_BRACKET|OPEN_PAREN|CLOSE_PAREN|OPEN_BRACE|CLOSE_BRACE
  static boolean brackets(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "brackets")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, OPEN_SQUARE_BRACKET);
    if (!result_) result_ = consumeToken(builder_, CLOSE_SQUARE_BRACKET);
    if (!result_) result_ = consumeToken(builder_, OPEN_PAREN);
    if (!result_) result_ = consumeToken(builder_, CLOSE_PAREN);
    if (!result_) result_ = consumeToken(builder_, OPEN_BRACE);
    if (!result_) result_ = consumeToken(builder_, CLOSE_BRACE);
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
  // expression_wildcard*
  public static boolean expression(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "expression")) return false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<expression>");
    int pos_ = current_position_(builder_);
    while (true) {
      if (!expression_wildcard(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "expression", pos_)) break;
      pos_ = current_position_(builder_);
    }
    exit_section_(builder_, level_, marker_, EXPRESSION, true, false, null);
    return true;
  }

  /* ********************************************************** */
  // statement_block
  // 	| function
  // 	| use
  // 	| let
  // 	| type_definition
  // 	| attribute
  // 	| extern
  // 	| (simple_token|literal|comment|operators|keyword|IDENTIFIER|OPEN_SQUARE_BRACKET|CLOSE_SQUARE_BRACKET|OPEN_PAREN|CLOSE_PAREN|HASH|COMMA|DOUBLE_COLON|THIN_ARROW|FAT_ARROW|DOT|AT|SINGLE_QUOTE|DOLLAR|':')
  static boolean expression_wildcard(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "expression_wildcard")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = statement_block(builder_, level_ + 1);
    if (!result_) result_ = function(builder_, level_ + 1);
    if (!result_) result_ = use(builder_, level_ + 1);
    if (!result_) result_ = let(builder_, level_ + 1);
    if (!result_) result_ = type_definition(builder_, level_ + 1);
    if (!result_) result_ = attribute(builder_, level_ + 1);
    if (!result_) result_ = extern(builder_, level_ + 1);
    if (!result_) result_ = expression_wildcard_7(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // simple_token|literal|comment|operators|keyword|IDENTIFIER|OPEN_SQUARE_BRACKET|CLOSE_SQUARE_BRACKET|OPEN_PAREN|CLOSE_PAREN|HASH|COMMA|DOUBLE_COLON|THIN_ARROW|FAT_ARROW|DOT|AT|SINGLE_QUOTE|DOLLAR|':'
  private static boolean expression_wildcard_7(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "expression_wildcard_7")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = simple_token(builder_, level_ + 1);
    if (!result_) result_ = literal(builder_, level_ + 1);
    if (!result_) result_ = comment(builder_, level_ + 1);
    if (!result_) result_ = operators(builder_, level_ + 1);
    if (!result_) result_ = keyword(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, IDENTIFIER);
    if (!result_) result_ = consumeToken(builder_, OPEN_SQUARE_BRACKET);
    if (!result_) result_ = consumeToken(builder_, CLOSE_SQUARE_BRACKET);
    if (!result_) result_ = consumeToken(builder_, OPEN_PAREN);
    if (!result_) result_ = consumeToken(builder_, CLOSE_PAREN);
    if (!result_) result_ = consumeToken(builder_, HASH);
    if (!result_) result_ = consumeToken(builder_, COMMA);
    if (!result_) result_ = consumeToken(builder_, DOUBLE_COLON);
    if (!result_) result_ = consumeToken(builder_, THIN_ARROW);
    if (!result_) result_ = consumeToken(builder_, FAT_ARROW);
    if (!result_) result_ = consumeToken(builder_, DOT);
    if (!result_) result_ = consumeToken(builder_, AT);
    if (!result_) result_ = consumeToken(builder_, SINGLE_QUOTE);
    if (!result_) result_ = consumeToken(builder_, DOLLAR);
    if (!result_) result_ = consumeToken(builder_, COLON);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // extern_crate ';' | extern_block '}'
  static boolean extern(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "extern")) return false;
    if (!nextTokenIs(builder_, KW_EXTERN)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = extern_0(builder_, level_ + 1);
    if (!result_) result_ = extern_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // extern_crate ';'
  private static boolean extern_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "extern_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = extern_crate(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, SEMICOLON);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // extern_block '}'
  private static boolean extern_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "extern_1")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = extern_block(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, CLOSE_BRACE);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // 'extern' STRING_LIT? '{' (attribute | trait_function_declaration ';')*
  public static boolean extern_block(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "extern_block")) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<extern block>");
    result_ = consumeToken(builder_, KW_EXTERN);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, extern_block_1(builder_, level_ + 1));
    result_ = pinned_ && report_error_(builder_, consumeToken(builder_, OPEN_BRACE)) && result_;
    result_ = pinned_ && extern_block_3(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, EXTERN_BLOCK, result_, pinned_, not_close_brace_parser_);
    return result_ || pinned_;
  }

  // STRING_LIT?
  private static boolean extern_block_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "extern_block_1")) return false;
    consumeToken(builder_, STRING_LIT);
    return true;
  }

  // (attribute | trait_function_declaration ';')*
  private static boolean extern_block_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "extern_block_3")) return false;
    int pos_ = current_position_(builder_);
    while (true) {
      if (!extern_block_3_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "extern_block_3", pos_)) break;
      pos_ = current_position_(builder_);
    }
    return true;
  }

  // attribute | trait_function_declaration ';'
  private static boolean extern_block_3_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "extern_block_3_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = attribute(builder_, level_ + 1);
    if (!result_) result_ = extern_block_3_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // trait_function_declaration ';'
  private static boolean extern_block_3_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "extern_block_3_0_1")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = trait_function_declaration(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, SEMICOLON);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // 'extern' 'crate' IDENTIFIER
  public static boolean extern_crate(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "extern_crate")) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<extern crate>");
    result_ = consumeToken(builder_, KW_EXTERN);
    result_ = result_ && consumeToken(builder_, KW_CRATE);
    pinned_ = result_; // pin = 2
    result_ = result_ && consumeToken(builder_, IDENTIFIER);
    exit_section_(builder_, level_, marker_, EXTERN_CRATE, result_, pinned_, not_semicolon_parser_);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // function_declaration statement_block
  public static boolean function(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "function")) return false;
    if (!nextTokenIs(builder_, "<function>", KW_FN, KW_PUB)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<function>");
    result_ = function_declaration(builder_, level_ + 1);
    result_ = result_ && statement_block(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, FUNCTION, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // ['pub'] 'fn' IDENTIFIER generic_params? '(' ( arg (',' arg)* )? ')' ['->' return_type]
  public static boolean function_declaration(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "function_declaration")) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<function declaration>");
    result_ = function_declaration_0(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, KW_FN);
    pinned_ = result_; // pin = 2
    result_ = result_ && report_error_(builder_, consumeToken(builder_, IDENTIFIER));
    result_ = pinned_ && report_error_(builder_, function_declaration_3(builder_, level_ + 1)) && result_;
    result_ = pinned_ && report_error_(builder_, consumeToken(builder_, OPEN_PAREN)) && result_;
    result_ = pinned_ && report_error_(builder_, function_declaration_5(builder_, level_ + 1)) && result_;
    result_ = pinned_ && report_error_(builder_, consumeToken(builder_, CLOSE_PAREN)) && result_;
    result_ = pinned_ && function_declaration_7(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, FUNCTION_DECLARATION, result_, pinned_, function_recover_parser_);
    return result_ || pinned_;
  }

  // ['pub']
  private static boolean function_declaration_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "function_declaration_0")) return false;
    consumeToken(builder_, KW_PUB);
    return true;
  }

  // generic_params?
  private static boolean function_declaration_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "function_declaration_3")) return false;
    generic_params(builder_, level_ + 1);
    return true;
  }

  // ( arg (',' arg)* )?
  private static boolean function_declaration_5(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "function_declaration_5")) return false;
    function_declaration_5_0(builder_, level_ + 1);
    return true;
  }

  // arg (',' arg)*
  private static boolean function_declaration_5_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "function_declaration_5_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = arg(builder_, level_ + 1);
    result_ = result_ && function_declaration_5_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (',' arg)*
  private static boolean function_declaration_5_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "function_declaration_5_0_1")) return false;
    int pos_ = current_position_(builder_);
    while (true) {
      if (!function_declaration_5_0_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "function_declaration_5_0_1", pos_)) break;
      pos_ = current_position_(builder_);
    }
    return true;
  }

  // ',' arg
  private static boolean function_declaration_5_0_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "function_declaration_5_0_1_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COMMA);
    result_ = result_ && arg(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // ['->' return_type]
  private static boolean function_declaration_7(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "function_declaration_7")) return false;
    function_declaration_7_0(builder_, level_ + 1);
    return true;
  }

  // '->' return_type
  private static boolean function_declaration_7_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "function_declaration_7_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, THIN_ARROW);
    result_ = result_ && return_type(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // !('{'|';')
  static boolean function_recover(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "function_recover")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NOT_, null);
    result_ = !function_recover_0(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, null, result_, false, null);
    return result_;
  }

  // '{'|';'
  private static boolean function_recover_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "function_recover_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, OPEN_BRACE);
    if (!result_) result_ = consumeToken(builder_, SEMICOLON);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // ['extern' STRING_LIT] 'unsafe'? 'fn' '(' type_closure_args ')' ['->' return_type]
  public static boolean function_type(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "function_type")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<function type>");
    result_ = function_type_0(builder_, level_ + 1);
    result_ = result_ && function_type_1(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, KW_FN);
    result_ = result_ && consumeToken(builder_, OPEN_PAREN);
    result_ = result_ && type_closure_args(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, CLOSE_PAREN);
    result_ = result_ && function_type_6(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, FUNCTION_TYPE, result_, false, null);
    return result_;
  }

  // ['extern' STRING_LIT]
  private static boolean function_type_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "function_type_0")) return false;
    function_type_0_0(builder_, level_ + 1);
    return true;
  }

  // 'extern' STRING_LIT
  private static boolean function_type_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "function_type_0_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, KW_EXTERN);
    result_ = result_ && consumeToken(builder_, STRING_LIT);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // 'unsafe'?
  private static boolean function_type_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "function_type_1")) return false;
    consumeToken(builder_, KW_UNSAFE);
    return true;
  }

  // ['->' return_type]
  private static boolean function_type_6(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "function_type_6")) return false;
    function_type_6_0(builder_, level_ + 1);
    return true;
  }

  // '->' return_type
  private static boolean function_type_6_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "function_type_6_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, THIN_ARROW);
    result_ = result_ && return_type(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // generic_internal '>'
  public static boolean generic(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "generic")) return false;
    if (!nextTokenIs(builder_, LESS_THAN)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = generic_internal(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, GREATER_THAN);
    exit_section_(builder_, marker_, GENERIC, result_);
    return result_;
  }

  /* ********************************************************** */
  // '<' (generic_without_lifetime | generic_with_lifetime)
  static boolean generic_internal(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "generic_internal")) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = consumeToken(builder_, LESS_THAN);
    pinned_ = result_; // pin = 1
    result_ = result_ && generic_internal_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, null, result_, pinned_, generic_recover_parser_);
    return result_ || pinned_;
  }

  // generic_without_lifetime | generic_with_lifetime
  private static boolean generic_internal_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "generic_internal_1")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = generic_without_lifetime(builder_, level_ + 1);
    if (!result_) result_ = generic_with_lifetime(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // lifetime | IDENTIFIER [(':' | '=') type ('+' type)* ]
  static boolean generic_param(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "generic_param")) return false;
    if (!nextTokenIs(builder_, "", SINGLE_QUOTE, IDENTIFIER)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = lifetime(builder_, level_ + 1);
    if (!result_) result_ = generic_param_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // IDENTIFIER [(':' | '=') type ('+' type)* ]
  private static boolean generic_param_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "generic_param_1")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, IDENTIFIER);
    result_ = result_ && generic_param_1_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // [(':' | '=') type ('+' type)* ]
  private static boolean generic_param_1_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "generic_param_1_1")) return false;
    generic_param_1_1_0(builder_, level_ + 1);
    return true;
  }

  // (':' | '=') type ('+' type)*
  private static boolean generic_param_1_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "generic_param_1_1_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = generic_param_1_1_0_0(builder_, level_ + 1);
    result_ = result_ && type(builder_, level_ + 1);
    result_ = result_ && generic_param_1_1_0_2(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // ':' | '='
  private static boolean generic_param_1_1_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "generic_param_1_1_0_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COLON);
    if (!result_) result_ = consumeToken(builder_, ASSIGN);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // ('+' type)*
  private static boolean generic_param_1_1_0_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "generic_param_1_1_0_2")) return false;
    int pos_ = current_position_(builder_);
    while (true) {
      if (!generic_param_1_1_0_2_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "generic_param_1_1_0_2", pos_)) break;
      pos_ = current_position_(builder_);
    }
    return true;
  }

  // '+' type
  private static boolean generic_param_1_1_0_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "generic_param_1_1_0_2_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, PLUS);
    result_ = result_ && type(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // '<' generic_param (',' generic_param)* '>'
  public static boolean generic_params(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "generic_params")) return false;
    if (!nextTokenIs(builder_, LESS_THAN)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, LESS_THAN);
    result_ = result_ && generic_param(builder_, level_ + 1);
    result_ = result_ && generic_params_2(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, GREATER_THAN);
    exit_section_(builder_, marker_, GENERIC_PARAMS, result_);
    return result_;
  }

  // (',' generic_param)*
  private static boolean generic_params_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "generic_params_2")) return false;
    int pos_ = current_position_(builder_);
    while (true) {
      if (!generic_params_2_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "generic_params_2", pos_)) break;
      pos_ = current_position_(builder_);
    }
    return true;
  }

  // ',' generic_param
  private static boolean generic_params_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "generic_params_2_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COMMA);
    result_ = result_ && generic_param(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // !('>')
  static boolean generic_recover(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "generic_recover")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NOT_, null);
    result_ = !generic_recover_0(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, null, result_, false, null);
    return result_;
  }

  // ('>')
  private static boolean generic_recover_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "generic_recover_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, GREATER_THAN);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // lifetime (',' lifetime)* (',' type)*
  static boolean generic_with_lifetime(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "generic_with_lifetime")) return false;
    if (!nextTokenIs(builder_, SINGLE_QUOTE)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = lifetime(builder_, level_ + 1);
    result_ = result_ && generic_with_lifetime_1(builder_, level_ + 1);
    result_ = result_ && generic_with_lifetime_2(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (',' lifetime)*
  private static boolean generic_with_lifetime_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "generic_with_lifetime_1")) return false;
    int pos_ = current_position_(builder_);
    while (true) {
      if (!generic_with_lifetime_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "generic_with_lifetime_1", pos_)) break;
      pos_ = current_position_(builder_);
    }
    return true;
  }

  // ',' lifetime
  private static boolean generic_with_lifetime_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "generic_with_lifetime_1_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COMMA);
    result_ = result_ && lifetime(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (',' type)*
  private static boolean generic_with_lifetime_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "generic_with_lifetime_2")) return false;
    int pos_ = current_position_(builder_);
    while (true) {
      if (!generic_with_lifetime_2_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "generic_with_lifetime_2", pos_)) break;
      pos_ = current_position_(builder_);
    }
    return true;
  }

  // ',' type
  private static boolean generic_with_lifetime_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "generic_with_lifetime_2_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COMMA);
    result_ = result_ && type(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // type ( ',' type)*
  static boolean generic_without_lifetime(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "generic_without_lifetime")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = type(builder_, level_ + 1);
    result_ = result_ && generic_without_lifetime_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // ( ',' type)*
  private static boolean generic_without_lifetime_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "generic_without_lifetime_1")) return false;
    int pos_ = current_position_(builder_);
    while (true) {
      if (!generic_without_lifetime_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "generic_without_lifetime_1", pos_)) break;
      pos_ = current_position_(builder_);
    }
    return true;
  }

  // ',' type
  private static boolean generic_without_lifetime_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "generic_without_lifetime_1_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COMMA);
    result_ = result_ && type(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // KW_AS|KW_BREAK|KW_CRATE|KW_ELSE|KW_LET|KW_STRUCT|KW_ENUM|KW_FALSE|KW_FOR|KW_IF|KW_IMPL|KW_IN|KW_LOOP|KW_MATCH|KW_MOD|KW_MUT|KW_PRIV|KW_PROC|KW_PUB|KW_REF|KW_RETURN|KW_SELF|KW_STATIC|KW_SUPER|KW_TRUE|KW_TYPE|KW_UNSAFE|KW_WHILE
  static boolean keyword(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "keyword")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, KW_AS);
    if (!result_) result_ = consumeToken(builder_, KW_BREAK);
    if (!result_) result_ = consumeToken(builder_, KW_CRATE);
    if (!result_) result_ = consumeToken(builder_, KW_ELSE);
    if (!result_) result_ = consumeToken(builder_, KW_LET);
    if (!result_) result_ = consumeToken(builder_, KW_STRUCT);
    if (!result_) result_ = consumeToken(builder_, KW_ENUM);
    if (!result_) result_ = consumeToken(builder_, KW_FALSE);
    if (!result_) result_ = consumeToken(builder_, KW_FOR);
    if (!result_) result_ = consumeToken(builder_, KW_IF);
    if (!result_) result_ = consumeToken(builder_, KW_IMPL);
    if (!result_) result_ = consumeToken(builder_, KW_IN);
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
    if (!result_) result_ = consumeToken(builder_, KW_SUPER);
    if (!result_) result_ = consumeToken(builder_, KW_TRUE);
    if (!result_) result_ = consumeToken(builder_, KW_TYPE);
    if (!result_) result_ = consumeToken(builder_, KW_UNSAFE);
    if (!result_) result_ = consumeToken(builder_, KW_WHILE);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // 'let' (type_tuple | let_destructuring | var_name) [':' type] ['=' expression] ';'
  public static boolean let(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "let")) return false;
    if (!nextTokenIs(builder_, KW_LET)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, KW_LET);
    result_ = result_ && let_1(builder_, level_ + 1);
    result_ = result_ && let_2(builder_, level_ + 1);
    result_ = result_ && let_3(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, SEMICOLON);
    exit_section_(builder_, marker_, LET, result_);
    return result_;
  }

  // type_tuple | let_destructuring | var_name
  private static boolean let_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "let_1")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = type_tuple(builder_, level_ + 1);
    if (!result_) result_ = let_destructuring(builder_, level_ + 1);
    if (!result_) result_ = var_name(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // [':' type]
  private static boolean let_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "let_2")) return false;
    let_2_0(builder_, level_ + 1);
    return true;
  }

  // ':' type
  private static boolean let_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "let_2_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COLON);
    result_ = result_ && type(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // ['=' expression]
  private static boolean let_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "let_3")) return false;
    let_3_0(builder_, level_ + 1);
    return true;
  }

  // '=' expression
  private static boolean let_3_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "let_3_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, ASSIGN);
    result_ = result_ && expression(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // type '{' IDENTIFIER [':' IDENTIFIER] (',' IDENTIFIER [':' IDENTIFIER] )* [',' '.' '.'] '}'
  static boolean let_destructuring(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "let_destructuring")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = type(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, OPEN_BRACE);
    result_ = result_ && consumeToken(builder_, IDENTIFIER);
    result_ = result_ && let_destructuring_3(builder_, level_ + 1);
    result_ = result_ && let_destructuring_4(builder_, level_ + 1);
    result_ = result_ && let_destructuring_5(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, CLOSE_BRACE);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // [':' IDENTIFIER]
  private static boolean let_destructuring_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "let_destructuring_3")) return false;
    let_destructuring_3_0(builder_, level_ + 1);
    return true;
  }

  // ':' IDENTIFIER
  private static boolean let_destructuring_3_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "let_destructuring_3_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COLON);
    result_ = result_ && consumeToken(builder_, IDENTIFIER);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (',' IDENTIFIER [':' IDENTIFIER] )*
  private static boolean let_destructuring_4(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "let_destructuring_4")) return false;
    int pos_ = current_position_(builder_);
    while (true) {
      if (!let_destructuring_4_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "let_destructuring_4", pos_)) break;
      pos_ = current_position_(builder_);
    }
    return true;
  }

  // ',' IDENTIFIER [':' IDENTIFIER]
  private static boolean let_destructuring_4_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "let_destructuring_4_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COMMA);
    result_ = result_ && consumeToken(builder_, IDENTIFIER);
    result_ = result_ && let_destructuring_4_0_2(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // [':' IDENTIFIER]
  private static boolean let_destructuring_4_0_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "let_destructuring_4_0_2")) return false;
    let_destructuring_4_0_2_0(builder_, level_ + 1);
    return true;
  }

  // ':' IDENTIFIER
  private static boolean let_destructuring_4_0_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "let_destructuring_4_0_2_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COLON);
    result_ = result_ && consumeToken(builder_, IDENTIFIER);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // [',' '.' '.']
  private static boolean let_destructuring_5(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "let_destructuring_5")) return false;
    let_destructuring_5_0(builder_, level_ + 1);
    return true;
  }

  // ',' '.' '.'
  private static boolean let_destructuring_5_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "let_destructuring_5_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COMMA);
    result_ = result_ && consumeToken(builder_, DOT);
    result_ = result_ && consumeToken(builder_, DOT);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // "'" (IDENTIFIER | 'static')
  public static boolean lifetime(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "lifetime")) return false;
    if (!nextTokenIs(builder_, SINGLE_QUOTE)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, SINGLE_QUOTE);
    result_ = result_ && lifetime_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, LIFETIME, result_);
    return result_;
  }

  // IDENTIFIER | 'static'
  private static boolean lifetime_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "lifetime_1")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, IDENTIFIER);
    if (!result_) result_ = consumeToken(builder_, KW_STATIC);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // string_literal | numeric_literal
  static boolean literal(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "literal")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = string_literal(builder_, level_ + 1);
    if (!result_) result_ = numeric_literal(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // !('}')
  static boolean not_close_brace(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "not_close_brace")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NOT_, null);
    result_ = !not_close_brace_0(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, null, result_, false, null);
    return result_;
  }

  // ('}')
  private static boolean not_close_brace_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "not_close_brace_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, CLOSE_BRACE);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // !(')')
  static boolean not_close_parenthesis(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "not_close_parenthesis")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NOT_, null);
    result_ = !not_close_parenthesis_0(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, null, result_, false, null);
    return result_;
  }

  // (')')
  private static boolean not_close_parenthesis_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "not_close_parenthesis_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, CLOSE_PAREN);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // !(']')
  static boolean not_close_square_bracket(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "not_close_square_bracket")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NOT_, null);
    result_ = !not_close_square_bracket_0(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, null, result_, false, null);
    return result_;
  }

  // (']')
  private static boolean not_close_square_bracket_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "not_close_square_bracket_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, CLOSE_SQUARE_BRACKET);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // !(';')
  static boolean not_semicolon(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "not_semicolon")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NOT_, null);
    result_ = !not_semicolon_0(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, null, result_, false, null);
    return result_;
  }

  // (';')
  private static boolean not_semicolon_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "not_semicolon_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, SEMICOLON);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // BIN_LIT | OCT_LIT | HEX_LIT | DEC_LIT
  static boolean numeric_literal(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "numeric_literal")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, BIN_LIT);
    if (!result_) result_ = consumeToken(builder_, OCT_LIT);
    if (!result_) result_ = consumeToken(builder_, HEX_LIT);
    if (!result_) result_ = consumeToken(builder_, DEC_LIT);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // arithmetic_operators|bitwise_operators|comparison_operators|unary_operators|AS|ASSIGN
  static boolean operators(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "operators")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = arithmetic_operators(builder_, level_ + 1);
    if (!result_) result_ = bitwise_operators(builder_, level_ + 1);
    if (!result_) result_ = comparison_operators(builder_, level_ + 1);
    if (!result_) result_ = unary_operators(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, AS);
    if (!result_) result_ = consumeToken(builder_, ASSIGN);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // '::'? IDENTIFIER ('::' IDENTIFIER)* ['::' generic]
  public static boolean path(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "path")) return false;
    if (!nextTokenIs(builder_, "<path>", DOUBLE_COLON, IDENTIFIER)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<path>");
    result_ = path_0(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, IDENTIFIER);
    result_ = result_ && path_2(builder_, level_ + 1);
    result_ = result_ && path_3(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, PATH, result_, false, null);
    return result_;
  }

  // '::'?
  private static boolean path_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "path_0")) return false;
    consumeToken(builder_, DOUBLE_COLON);
    return true;
  }

  // ('::' IDENTIFIER)*
  private static boolean path_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "path_2")) return false;
    int pos_ = current_position_(builder_);
    while (true) {
      if (!path_2_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "path_2", pos_)) break;
      pos_ = current_position_(builder_);
    }
    return true;
  }

  // '::' IDENTIFIER
  private static boolean path_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "path_2_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, DOUBLE_COLON);
    result_ = result_ && consumeToken(builder_, IDENTIFIER);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // ['::' generic]
  private static boolean path_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "path_3")) return false;
    path_3_0(builder_, level_ + 1);
    return true;
  }

  // '::' generic
  private static boolean path_3_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "path_3_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, DOUBLE_COLON);
    result_ = result_ && generic(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // '&'+ lifetime?
  public static boolean ref(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ref")) return false;
    if (!nextTokenIs(builder_, BITWISE_AND)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = ref_0(builder_, level_ + 1);
    result_ = result_ && ref_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, REF, result_);
    return result_;
  }

  // '&'+
  private static boolean ref_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ref_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, BITWISE_AND);
    int pos_ = current_position_(builder_);
    while (result_) {
      if (!consumeToken(builder_, BITWISE_AND)) break;
      if (!empty_element_parsed_guard_(builder_, "ref_0", pos_)) break;
      pos_ = current_position_(builder_);
    }
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // lifetime?
  private static boolean ref_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ref_1")) return false;
    lifetime(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // type | '!'
  static boolean return_type(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "return_type")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = type(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, NOT);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // ref? 'mut'? '~'? 'self'
  public static boolean self_arg(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "self_arg")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<self arg>");
    result_ = self_arg_0(builder_, level_ + 1);
    result_ = result_ && self_arg_1(builder_, level_ + 1);
    result_ = result_ && self_arg_2(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, KW_SELF);
    exit_section_(builder_, level_, marker_, SELF_ARG, result_, false, null);
    return result_;
  }

  // ref?
  private static boolean self_arg_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "self_arg_0")) return false;
    ref(builder_, level_ + 1);
    return true;
  }

  // 'mut'?
  private static boolean self_arg_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "self_arg_1")) return false;
    consumeToken(builder_, KW_MUT);
    return true;
  }

  // '~'?
  private static boolean self_arg_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "self_arg_2")) return false;
    consumeToken(builder_, BOX);
    return true;
  }

  /* ********************************************************** */
  // ( use
  // 	| function
  // 	| struct
  // 	| trait
  // 	| extern
  // 	| type_definition
  // 	| attribute
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
  // 	| function
  // 	| struct
  // 	| trait
  // 	| extern
  // 	| type_definition
  // 	| attribute
  // 	| token
  private static boolean simpleFile_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "simpleFile_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = use(builder_, level_ + 1);
    if (!result_) result_ = function(builder_, level_ + 1);
    if (!result_) result_ = struct(builder_, level_ + 1);
    if (!result_) result_ = trait(builder_, level_ + 1);
    if (!result_) result_ = extern(builder_, level_ + 1);
    if (!result_) result_ = type_definition(builder_, level_ + 1);
    if (!result_) result_ = attribute(builder_, level_ + 1);
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
  // var_name [':' type ]
  public static boolean standard_arg(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "standard_arg")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<standard arg>");
    result_ = var_name(builder_, level_ + 1);
    result_ = result_ && standard_arg_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, STANDARD_ARG, result_, false, null);
    return result_;
  }

  // [':' type ]
  private static boolean standard_arg_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "standard_arg_1")) return false;
    standard_arg_1_0(builder_, level_ + 1);
    return true;
  }

  // ':' type
  private static boolean standard_arg_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "standard_arg_1_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COLON);
    result_ = result_ && type(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // statement_block
  // 	| function
  // 	| use
  // 	| let
  // 	| statement_wildcard
  // 	| type_definition
  // 	| struct
  // 	| attribute
  // 	| extern
  static boolean statement(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "statement")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = statement_block(builder_, level_ + 1);
    if (!result_) result_ = function(builder_, level_ + 1);
    if (!result_) result_ = use(builder_, level_ + 1);
    if (!result_) result_ = let(builder_, level_ + 1);
    if (!result_) result_ = statement_wildcard(builder_, level_ + 1);
    if (!result_) result_ = type_definition(builder_, level_ + 1);
    if (!result_) result_ = struct(builder_, level_ + 1);
    if (!result_) result_ = attribute(builder_, level_ + 1);
    if (!result_) result_ = extern(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // statement_block_internal '}'
  public static boolean statement_block(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "statement_block")) return false;
    if (!nextTokenIs(builder_, OPEN_BRACE)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = statement_block_internal(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, CLOSE_BRACE);
    exit_section_(builder_, marker_, STATEMENT_BLOCK, result_);
    return result_;
  }

  /* ********************************************************** */
  // '{' statement*
  public static boolean statement_block_internal(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "statement_block_internal")) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<statement block internal>");
    result_ = consumeToken(builder_, OPEN_BRACE);
    pinned_ = result_; // pin = 1
    result_ = result_ && statement_block_internal_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, STATEMENT_BLOCK_INTERNAL, result_, pinned_, not_close_brace_parser_);
    return result_ || pinned_;
  }

  // statement*
  private static boolean statement_block_internal_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "statement_block_internal_1")) return false;
    int pos_ = current_position_(builder_);
    while (true) {
      if (!statement(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "statement_block_internal_1", pos_)) break;
      pos_ = current_position_(builder_);
    }
    return true;
  }

  /* ********************************************************** */
  // simple_token|literal|comment|symbol|operators|keyword|IDENTIFIER|OPEN_SQUARE_BRACKET|CLOSE_SQUARE_BRACKET|OPEN_PAREN|CLOSE_PAREN
  public static boolean statement_wildcard(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "statement_wildcard")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<statement wildcard>");
    result_ = simple_token(builder_, level_ + 1);
    if (!result_) result_ = literal(builder_, level_ + 1);
    if (!result_) result_ = comment(builder_, level_ + 1);
    if (!result_) result_ = symbol(builder_, level_ + 1);
    if (!result_) result_ = operators(builder_, level_ + 1);
    if (!result_) result_ = keyword(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, IDENTIFIER);
    if (!result_) result_ = consumeToken(builder_, OPEN_SQUARE_BRACKET);
    if (!result_) result_ = consumeToken(builder_, CLOSE_SQUARE_BRACKET);
    if (!result_) result_ = consumeToken(builder_, OPEN_PAREN);
    if (!result_) result_ = consumeToken(builder_, CLOSE_PAREN);
    exit_section_(builder_, level_, marker_, STATEMENT_WILDCARD, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // STRING_LIT | RAW_STRING_LIT | CHAR_LIT
  static boolean string_literal(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "string_literal")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, STRING_LIT);
    if (!result_) result_ = consumeToken(builder_, RAW_STRING_LIT);
    if (!result_) result_ = consumeToken(builder_, CHAR_LIT);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // 'pub'? 'struct' IDENTIFIER generic_params? (struct_body '}' | tuple_body ')' | ';')
  public static boolean struct(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "struct")) return false;
    if (!nextTokenIs(builder_, "<struct>", KW_PUB, KW_STRUCT)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<struct>");
    result_ = struct_0(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, KW_STRUCT);
    result_ = result_ && consumeToken(builder_, IDENTIFIER);
    result_ = result_ && struct_3(builder_, level_ + 1);
    result_ = result_ && struct_4(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, STRUCT, result_, false, null);
    return result_;
  }

  // 'pub'?
  private static boolean struct_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "struct_0")) return false;
    consumeToken(builder_, KW_PUB);
    return true;
  }

  // generic_params?
  private static boolean struct_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "struct_3")) return false;
    generic_params(builder_, level_ + 1);
    return true;
  }

  // struct_body '}' | tuple_body ')' | ';'
  private static boolean struct_4(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "struct_4")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = struct_4_0(builder_, level_ + 1);
    if (!result_) result_ = struct_4_1(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, SEMICOLON);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // struct_body '}'
  private static boolean struct_4_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "struct_4_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = struct_body(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, CLOSE_BRACE);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // tuple_body ')'
  private static boolean struct_4_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "struct_4_1")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = tuple_body(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, CLOSE_PAREN);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // '{' [struct_property (',' struct_property)* ] ','?
  public static boolean struct_body(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "struct_body")) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<struct body>");
    result_ = consumeToken(builder_, OPEN_BRACE);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, struct_body_1(builder_, level_ + 1));
    result_ = pinned_ && struct_body_2(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, STRUCT_BODY, result_, pinned_, not_close_brace_parser_);
    return result_ || pinned_;
  }

  // [struct_property (',' struct_property)* ]
  private static boolean struct_body_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "struct_body_1")) return false;
    struct_body_1_0(builder_, level_ + 1);
    return true;
  }

  // struct_property (',' struct_property)*
  private static boolean struct_body_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "struct_body_1_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = struct_property(builder_, level_ + 1);
    result_ = result_ && struct_body_1_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (',' struct_property)*
  private static boolean struct_body_1_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "struct_body_1_0_1")) return false;
    int pos_ = current_position_(builder_);
    while (true) {
      if (!struct_body_1_0_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "struct_body_1_0_1", pos_)) break;
      pos_ = current_position_(builder_);
    }
    return true;
  }

  // ',' struct_property
  private static boolean struct_body_1_0_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "struct_body_1_0_1_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COMMA);
    result_ = result_ && struct_property(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // ','?
  private static boolean struct_body_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "struct_body_2")) return false;
    consumeToken(builder_, COMMA);
    return true;
  }

  /* ********************************************************** */
  // ['priv' | 'pub'] standard_arg
  public static boolean struct_property(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "struct_property")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<struct property>");
    result_ = struct_property_0(builder_, level_ + 1);
    result_ = result_ && standard_arg(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, STRUCT_PROPERTY, result_, false, null);
    return result_;
  }

  // ['priv' | 'pub']
  private static boolean struct_property_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "struct_property_0")) return false;
    struct_property_0_0(builder_, level_ + 1);
    return true;
  }

  // 'priv' | 'pub'
  private static boolean struct_property_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "struct_property_0_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, KW_PRIV);
    if (!result_) result_ = consumeToken(builder_, KW_PUB);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // HASH|COMMA|SEMICOLON|DOUBLE_COLON|THIN_ARROW|FAT_ARROW|DOT|AT|SINGLE_QUOTE|DOLLAR|':'
  static boolean symbol(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "symbol")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, HASH);
    if (!result_) result_ = consumeToken(builder_, COMMA);
    if (!result_) result_ = consumeToken(builder_, SEMICOLON);
    if (!result_) result_ = consumeToken(builder_, DOUBLE_COLON);
    if (!result_) result_ = consumeToken(builder_, THIN_ARROW);
    if (!result_) result_ = consumeToken(builder_, FAT_ARROW);
    if (!result_) result_ = consumeToken(builder_, DOT);
    if (!result_) result_ = consumeToken(builder_, AT);
    if (!result_) result_ = consumeToken(builder_, SINGLE_QUOTE);
    if (!result_) result_ = consumeToken(builder_, DOLLAR);
    if (!result_) result_ = consumeToken(builder_, COLON);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // simple_token|literal|comment|symbol|brackets|operators|keyword|IDENTIFIER
  static boolean token(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "token")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = simple_token(builder_, level_ + 1);
    if (!result_) result_ = literal(builder_, level_ + 1);
    if (!result_) result_ = comment(builder_, level_ + 1);
    if (!result_) result_ = symbol(builder_, level_ + 1);
    if (!result_) result_ = brackets(builder_, level_ + 1);
    if (!result_) result_ = operators(builder_, level_ + 1);
    if (!result_) result_ = keyword(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, IDENTIFIER);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // trait_internal '}'
  public static boolean trait(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "trait")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<trait>");
    result_ = trait_internal(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, CLOSE_BRACE);
    exit_section_(builder_, level_, marker_, TRAIT, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // ['pub'] 'fn' IDENTIFIER generic_params? '(' ( (arg|type) (',' (arg|type))* )? ')' ['->' return_type]
  public static boolean trait_function_declaration(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "trait_function_declaration")) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<trait function declaration>");
    result_ = trait_function_declaration_0(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, KW_FN);
    pinned_ = result_; // pin = 2
    result_ = result_ && report_error_(builder_, consumeToken(builder_, IDENTIFIER));
    result_ = pinned_ && report_error_(builder_, trait_function_declaration_3(builder_, level_ + 1)) && result_;
    result_ = pinned_ && report_error_(builder_, consumeToken(builder_, OPEN_PAREN)) && result_;
    result_ = pinned_ && report_error_(builder_, trait_function_declaration_5(builder_, level_ + 1)) && result_;
    result_ = pinned_ && report_error_(builder_, consumeToken(builder_, CLOSE_PAREN)) && result_;
    result_ = pinned_ && trait_function_declaration_7(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, TRAIT_FUNCTION_DECLARATION, result_, pinned_, not_semicolon_parser_);
    return result_ || pinned_;
  }

  // ['pub']
  private static boolean trait_function_declaration_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "trait_function_declaration_0")) return false;
    consumeToken(builder_, KW_PUB);
    return true;
  }

  // generic_params?
  private static boolean trait_function_declaration_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "trait_function_declaration_3")) return false;
    generic_params(builder_, level_ + 1);
    return true;
  }

  // ( (arg|type) (',' (arg|type))* )?
  private static boolean trait_function_declaration_5(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "trait_function_declaration_5")) return false;
    trait_function_declaration_5_0(builder_, level_ + 1);
    return true;
  }

  // (arg|type) (',' (arg|type))*
  private static boolean trait_function_declaration_5_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "trait_function_declaration_5_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = trait_function_declaration_5_0_0(builder_, level_ + 1);
    result_ = result_ && trait_function_declaration_5_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // arg|type
  private static boolean trait_function_declaration_5_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "trait_function_declaration_5_0_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = arg(builder_, level_ + 1);
    if (!result_) result_ = type(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (',' (arg|type))*
  private static boolean trait_function_declaration_5_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "trait_function_declaration_5_0_1")) return false;
    int pos_ = current_position_(builder_);
    while (true) {
      if (!trait_function_declaration_5_0_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "trait_function_declaration_5_0_1", pos_)) break;
      pos_ = current_position_(builder_);
    }
    return true;
  }

  // ',' (arg|type)
  private static boolean trait_function_declaration_5_0_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "trait_function_declaration_5_0_1_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COMMA);
    result_ = result_ && trait_function_declaration_5_0_1_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // arg|type
  private static boolean trait_function_declaration_5_0_1_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "trait_function_declaration_5_0_1_0_1")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = arg(builder_, level_ + 1);
    if (!result_) result_ = type(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // ['->' return_type]
  private static boolean trait_function_declaration_7(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "trait_function_declaration_7")) return false;
    trait_function_declaration_7_0(builder_, level_ + 1);
    return true;
  }

  // '->' return_type
  private static boolean trait_function_declaration_7_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "trait_function_declaration_7_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, THIN_ARROW);
    result_ = result_ && return_type(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // ['priv' | 'pub'] 'trait' IDENTIFIER generic_params? [':' type] '{' (trait_function_declaration ';')*
  static boolean trait_internal(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "trait_internal")) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = trait_internal_0(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, KW_TRAIT);
    pinned_ = result_; // pin = 2
    result_ = result_ && report_error_(builder_, consumeToken(builder_, IDENTIFIER));
    result_ = pinned_ && report_error_(builder_, trait_internal_3(builder_, level_ + 1)) && result_;
    result_ = pinned_ && report_error_(builder_, trait_internal_4(builder_, level_ + 1)) && result_;
    result_ = pinned_ && report_error_(builder_, consumeToken(builder_, OPEN_BRACE)) && result_;
    result_ = pinned_ && trait_internal_6(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, null, result_, pinned_, not_close_brace_parser_);
    return result_ || pinned_;
  }

  // ['priv' | 'pub']
  private static boolean trait_internal_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "trait_internal_0")) return false;
    trait_internal_0_0(builder_, level_ + 1);
    return true;
  }

  // 'priv' | 'pub'
  private static boolean trait_internal_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "trait_internal_0_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, KW_PRIV);
    if (!result_) result_ = consumeToken(builder_, KW_PUB);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // generic_params?
  private static boolean trait_internal_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "trait_internal_3")) return false;
    generic_params(builder_, level_ + 1);
    return true;
  }

  // [':' type]
  private static boolean trait_internal_4(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "trait_internal_4")) return false;
    trait_internal_4_0(builder_, level_ + 1);
    return true;
  }

  // ':' type
  private static boolean trait_internal_4_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "trait_internal_4_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COLON);
    result_ = result_ && type(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (trait_function_declaration ';')*
  private static boolean trait_internal_6(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "trait_internal_6")) return false;
    int pos_ = current_position_(builder_);
    while (true) {
      if (!trait_internal_6_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "trait_internal_6", pos_)) break;
      pos_ = current_position_(builder_);
    }
    return true;
  }

  // trait_function_declaration ';'
  private static boolean trait_internal_6_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "trait_internal_6_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = trait_function_declaration(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, SEMICOLON);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // '(' var_name (',' var_name)* ')' [':' type ]
  public static boolean tuple_arg(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "tuple_arg")) return false;
    if (!nextTokenIs(builder_, OPEN_PAREN)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, OPEN_PAREN);
    result_ = result_ && var_name(builder_, level_ + 1);
    result_ = result_ && tuple_arg_2(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, CLOSE_PAREN);
    result_ = result_ && tuple_arg_4(builder_, level_ + 1);
    exit_section_(builder_, marker_, TUPLE_ARG, result_);
    return result_;
  }

  // (',' var_name)*
  private static boolean tuple_arg_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "tuple_arg_2")) return false;
    int pos_ = current_position_(builder_);
    while (true) {
      if (!tuple_arg_2_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "tuple_arg_2", pos_)) break;
      pos_ = current_position_(builder_);
    }
    return true;
  }

  // ',' var_name
  private static boolean tuple_arg_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "tuple_arg_2_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COMMA);
    result_ = result_ && var_name(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // [':' type ]
  private static boolean tuple_arg_4(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "tuple_arg_4")) return false;
    tuple_arg_4_0(builder_, level_ + 1);
    return true;
  }

  // ':' type
  private static boolean tuple_arg_4_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "tuple_arg_4_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COLON);
    result_ = result_ && type(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // '(' type (',' type)*
  public static boolean tuple_body(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "tuple_body")) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<tuple body>");
    result_ = consumeToken(builder_, OPEN_PAREN);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, type(builder_, level_ + 1));
    result_ = pinned_ && tuple_body_2(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, TUPLE_BODY, result_, pinned_, not_close_parenthesis_parser_);
    return result_ || pinned_;
  }

  // (',' type)*
  private static boolean tuple_body_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "tuple_body_2")) return false;
    int pos_ = current_position_(builder_);
    while (true) {
      if (!tuple_body_2_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "tuple_body_2", pos_)) break;
      pos_ = current_position_(builder_);
    }
    return true;
  }

  // ',' type
  private static boolean tuple_body_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "tuple_body_2_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COMMA);
    result_ = result_ && type(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // type_basic | type_tuple | type_closure | function_type | type_proc | type_none
  static boolean type(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "type")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = type_basic(builder_, level_ + 1);
    if (!result_) result_ = type_tuple(builder_, level_ + 1);
    if (!result_) result_ = type_closure(builder_, level_ + 1);
    if (!result_) result_ = function_type(builder_, level_ + 1);
    if (!result_) result_ = type_proc(builder_, level_ + 1);
    if (!result_) result_ = type_none(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // ( ref 'mut'? | lifetime | '*' | 'mut' | '*' 'mut')* ('~' | '@' | '$')? ( type_path | '[' type [',' '.' '.' (IDENTIFIER | numeric_literal)] ']' )
  public static boolean type_basic(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "type_basic")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<type basic>");
    result_ = type_basic_0(builder_, level_ + 1);
    result_ = result_ && type_basic_1(builder_, level_ + 1);
    result_ = result_ && type_basic_2(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, TYPE_BASIC, result_, false, null);
    return result_;
  }

  // ( ref 'mut'? | lifetime | '*' | 'mut' | '*' 'mut')*
  private static boolean type_basic_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "type_basic_0")) return false;
    int pos_ = current_position_(builder_);
    while (true) {
      if (!type_basic_0_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "type_basic_0", pos_)) break;
      pos_ = current_position_(builder_);
    }
    return true;
  }

  // ref 'mut'? | lifetime | '*' | 'mut' | '*' 'mut'
  private static boolean type_basic_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "type_basic_0_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = type_basic_0_0_0(builder_, level_ + 1);
    if (!result_) result_ = lifetime(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, MULTIPLY);
    if (!result_) result_ = consumeToken(builder_, KW_MUT);
    if (!result_) result_ = type_basic_0_0_4(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // ref 'mut'?
  private static boolean type_basic_0_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "type_basic_0_0_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = ref(builder_, level_ + 1);
    result_ = result_ && type_basic_0_0_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // 'mut'?
  private static boolean type_basic_0_0_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "type_basic_0_0_0_1")) return false;
    consumeToken(builder_, KW_MUT);
    return true;
  }

  // '*' 'mut'
  private static boolean type_basic_0_0_4(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "type_basic_0_0_4")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, MULTIPLY);
    result_ = result_ && consumeToken(builder_, KW_MUT);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // ('~' | '@' | '$')?
  private static boolean type_basic_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "type_basic_1")) return false;
    type_basic_1_0(builder_, level_ + 1);
    return true;
  }

  // '~' | '@' | '$'
  private static boolean type_basic_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "type_basic_1_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, BOX);
    if (!result_) result_ = consumeToken(builder_, AT);
    if (!result_) result_ = consumeToken(builder_, DOLLAR);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // type_path | '[' type [',' '.' '.' (IDENTIFIER | numeric_literal)] ']'
  private static boolean type_basic_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "type_basic_2")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = type_path(builder_, level_ + 1);
    if (!result_) result_ = type_basic_2_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // '[' type [',' '.' '.' (IDENTIFIER | numeric_literal)] ']'
  private static boolean type_basic_2_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "type_basic_2_1")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, OPEN_SQUARE_BRACKET);
    result_ = result_ && type(builder_, level_ + 1);
    result_ = result_ && type_basic_2_1_2(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, CLOSE_SQUARE_BRACKET);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // [',' '.' '.' (IDENTIFIER | numeric_literal)]
  private static boolean type_basic_2_1_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "type_basic_2_1_2")) return false;
    type_basic_2_1_2_0(builder_, level_ + 1);
    return true;
  }

  // ',' '.' '.' (IDENTIFIER | numeric_literal)
  private static boolean type_basic_2_1_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "type_basic_2_1_2_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COMMA);
    result_ = result_ && consumeToken(builder_, DOT);
    result_ = result_ && consumeToken(builder_, DOT);
    result_ = result_ && type_basic_2_1_2_0_3(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // IDENTIFIER | numeric_literal
  private static boolean type_basic_2_1_2_0_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "type_basic_2_1_2_0_3")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, IDENTIFIER);
    if (!result_) result_ = numeric_literal(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // '|' type_closure_args '|' ['->' return_type]
  public static boolean type_closure(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "type_closure")) return false;
    if (!nextTokenIs(builder_, BITWISE_OR)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, BITWISE_OR);
    result_ = result_ && type_closure_args(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, BITWISE_OR);
    result_ = result_ && type_closure_3(builder_, level_ + 1);
    exit_section_(builder_, marker_, TYPE_CLOSURE, result_);
    return result_;
  }

  // ['->' return_type]
  private static boolean type_closure_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "type_closure_3")) return false;
    type_closure_3_0(builder_, level_ + 1);
    return true;
  }

  // '->' return_type
  private static boolean type_closure_3_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "type_closure_3_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, THIN_ARROW);
    result_ = result_ && return_type(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // type_basic | standard_arg
  static boolean type_closure_arg(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "type_closure_arg")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = type_basic(builder_, level_ + 1);
    if (!result_) result_ = standard_arg(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // [ type_closure_arg ( ',' type_closure_arg)* ]
  static boolean type_closure_args(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "type_closure_args")) return false;
    type_closure_args_0(builder_, level_ + 1);
    return true;
  }

  // type_closure_arg ( ',' type_closure_arg)*
  private static boolean type_closure_args_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "type_closure_args_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = type_closure_arg(builder_, level_ + 1);
    result_ = result_ && type_closure_args_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // ( ',' type_closure_arg)*
  private static boolean type_closure_args_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "type_closure_args_0_1")) return false;
    int pos_ = current_position_(builder_);
    while (true) {
      if (!type_closure_args_0_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "type_closure_args_0_1", pos_)) break;
      pos_ = current_position_(builder_);
    }
    return true;
  }

  // ',' type_closure_arg
  private static boolean type_closure_args_0_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "type_closure_args_0_1_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COMMA);
    result_ = result_ && type_closure_arg(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // 'type' IDENTIFIER '=' type ';'
  public static boolean type_definition(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "type_definition")) return false;
    if (!nextTokenIs(builder_, KW_TYPE)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, KW_TYPE);
    result_ = result_ && consumeToken(builder_, IDENTIFIER);
    result_ = result_ && consumeToken(builder_, ASSIGN);
    result_ = result_ && type(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, SEMICOLON);
    exit_section_(builder_, marker_, TYPE_DEFINITION, result_);
    return result_;
  }

  /* ********************************************************** */
  // '*'? '(' ')'
  public static boolean type_none(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "type_none")) return false;
    if (!nextTokenIs(builder_, "<type none>", OPEN_PAREN, MULTIPLY)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<type none>");
    result_ = type_none_0(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, OPEN_PAREN);
    result_ = result_ && consumeToken(builder_, CLOSE_PAREN);
    exit_section_(builder_, level_, marker_, TYPE_NONE, result_, false, null);
    return result_;
  }

  // '*'?
  private static boolean type_none_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "type_none_0")) return false;
    consumeToken(builder_, MULTIPLY);
    return true;
  }

  /* ********************************************************** */
  // '::'? IDENTIFIER ('::' IDENTIFIER)* generic?
  static boolean type_path(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "type_path")) return false;
    if (!nextTokenIs(builder_, "", DOUBLE_COLON, IDENTIFIER)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = type_path_0(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, IDENTIFIER);
    result_ = result_ && type_path_2(builder_, level_ + 1);
    result_ = result_ && type_path_3(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // '::'?
  private static boolean type_path_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "type_path_0")) return false;
    consumeToken(builder_, DOUBLE_COLON);
    return true;
  }

  // ('::' IDENTIFIER)*
  private static boolean type_path_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "type_path_2")) return false;
    int pos_ = current_position_(builder_);
    while (true) {
      if (!type_path_2_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "type_path_2", pos_)) break;
      pos_ = current_position_(builder_);
    }
    return true;
  }

  // '::' IDENTIFIER
  private static boolean type_path_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "type_path_2_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, DOUBLE_COLON);
    result_ = result_ && consumeToken(builder_, IDENTIFIER);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // generic?
  private static boolean type_path_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "type_path_3")) return false;
    generic(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // 'proc' '(' type_closure_args ')' ['->' return_type]
  public static boolean type_proc(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "type_proc")) return false;
    if (!nextTokenIs(builder_, KW_PROC)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, KW_PROC);
    result_ = result_ && consumeToken(builder_, OPEN_PAREN);
    result_ = result_ && type_closure_args(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, CLOSE_PAREN);
    result_ = result_ && type_proc_4(builder_, level_ + 1);
    exit_section_(builder_, marker_, TYPE_PROC, result_);
    return result_;
  }

  // ['->' return_type]
  private static boolean type_proc_4(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "type_proc_4")) return false;
    type_proc_4_0(builder_, level_ + 1);
    return true;
  }

  // '->' return_type
  private static boolean type_proc_4_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "type_proc_4_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, THIN_ARROW);
    result_ = result_ && return_type(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // '(' type (',' type)* ')'
  public static boolean type_tuple(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "type_tuple")) return false;
    if (!nextTokenIs(builder_, OPEN_PAREN)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, OPEN_PAREN);
    result_ = result_ && type(builder_, level_ + 1);
    result_ = result_ && type_tuple_2(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, CLOSE_PAREN);
    exit_section_(builder_, marker_, TYPE_TUPLE, result_);
    return result_;
  }

  // (',' type)*
  private static boolean type_tuple_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "type_tuple_2")) return false;
    int pos_ = current_position_(builder_);
    while (true) {
      if (!type_tuple_2_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "type_tuple_2", pos_)) break;
      pos_ = current_position_(builder_);
    }
    return true;
  }

  // ',' type
  private static boolean type_tuple_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "type_tuple_2_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COMMA);
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
  // use_internal ';'
  public static boolean use(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "use")) return false;
    if (!nextTokenIs(builder_, "<use>", KW_PUB, KW_USE)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<use>");
    result_ = use_internal(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, SEMICOLON);
    exit_section_(builder_, level_, marker_, USE, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // '*' | '{' IDENTIFIER ( ',' IDENTIFIER )* '}'
  static boolean use_glob(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "use_glob")) return false;
    if (!nextTokenIs(builder_, "", MULTIPLY, OPEN_BRACE)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, MULTIPLY);
    if (!result_) result_ = use_glob_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // '{' IDENTIFIER ( ',' IDENTIFIER )* '}'
  private static boolean use_glob_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "use_glob_1")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, OPEN_BRACE);
    result_ = result_ && consumeToken(builder_, IDENTIFIER);
    result_ = result_ && use_glob_1_2(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, CLOSE_BRACE);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // ( ',' IDENTIFIER )*
  private static boolean use_glob_1_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "use_glob_1_2")) return false;
    int pos_ = current_position_(builder_);
    while (true) {
      if (!use_glob_1_2_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "use_glob_1_2", pos_)) break;
      pos_ = current_position_(builder_);
    }
    return true;
  }

  // ',' IDENTIFIER
  private static boolean use_glob_1_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "use_glob_1_2_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COMMA);
    result_ = result_ && consumeToken(builder_, IDENTIFIER);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // ['pub'] 'use' (use_glob | use_part ('::' use_part)* ('::' use_glob | '=' path)?)
  static boolean use_internal(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "use_internal")) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = use_internal_0(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, KW_USE);
    pinned_ = result_; // pin = 2
    result_ = result_ && use_internal_2(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, null, result_, pinned_, not_semicolon_parser_);
    return result_ || pinned_;
  }

  // ['pub']
  private static boolean use_internal_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "use_internal_0")) return false;
    consumeToken(builder_, KW_PUB);
    return true;
  }

  // use_glob | use_part ('::' use_part)* ('::' use_glob | '=' path)?
  private static boolean use_internal_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "use_internal_2")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = use_glob(builder_, level_ + 1);
    if (!result_) result_ = use_internal_2_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // use_part ('::' use_part)* ('::' use_glob | '=' path)?
  private static boolean use_internal_2_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "use_internal_2_1")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = use_part(builder_, level_ + 1);
    result_ = result_ && use_internal_2_1_1(builder_, level_ + 1);
    result_ = result_ && use_internal_2_1_2(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // ('::' use_part)*
  private static boolean use_internal_2_1_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "use_internal_2_1_1")) return false;
    int pos_ = current_position_(builder_);
    while (true) {
      if (!use_internal_2_1_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "use_internal_2_1_1", pos_)) break;
      pos_ = current_position_(builder_);
    }
    return true;
  }

  // '::' use_part
  private static boolean use_internal_2_1_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "use_internal_2_1_1_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, DOUBLE_COLON);
    result_ = result_ && use_part(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // ('::' use_glob | '=' path)?
  private static boolean use_internal_2_1_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "use_internal_2_1_2")) return false;
    use_internal_2_1_2_0(builder_, level_ + 1);
    return true;
  }

  // '::' use_glob | '=' path
  private static boolean use_internal_2_1_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "use_internal_2_1_2_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = use_internal_2_1_2_0_0(builder_, level_ + 1);
    if (!result_) result_ = use_internal_2_1_2_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // '::' use_glob
  private static boolean use_internal_2_1_2_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "use_internal_2_1_2_0_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, DOUBLE_COLON);
    result_ = result_ && use_glob(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // '=' path
  private static boolean use_internal_2_1_2_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "use_internal_2_1_2_0_1")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, ASSIGN);
    result_ = result_ && path(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // IDENTIFIER | 'super' | 'self'
  static boolean use_part(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "use_part")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, IDENTIFIER);
    if (!result_) result_ = consumeToken(builder_, KW_SUPER);
    if (!result_) result_ = consumeToken(builder_, KW_SELF);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // ref? 'mut'? IDENTIFIER
  static boolean var_name(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "var_name")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = var_name_0(builder_, level_ + 1);
    result_ = result_ && var_name_1(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, IDENTIFIER);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // ref?
  private static boolean var_name_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "var_name_0")) return false;
    ref(builder_, level_ + 1);
    return true;
  }

  // 'mut'?
  private static boolean var_name_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "var_name_1")) return false;
    consumeToken(builder_, KW_MUT);
    return true;
  }

  final static Parser function_recover_parser_ = new Parser() {
    public boolean parse(PsiBuilder builder_, int level_) {
      return function_recover(builder_, level_ + 1);
    }
  };
  final static Parser generic_recover_parser_ = new Parser() {
    public boolean parse(PsiBuilder builder_, int level_) {
      return generic_recover(builder_, level_ + 1);
    }
  };
  final static Parser not_close_brace_parser_ = new Parser() {
    public boolean parse(PsiBuilder builder_, int level_) {
      return not_close_brace(builder_, level_ + 1);
    }
  };
  final static Parser not_close_parenthesis_parser_ = new Parser() {
    public boolean parse(PsiBuilder builder_, int level_) {
      return not_close_parenthesis(builder_, level_ + 1);
    }
  };
  final static Parser not_close_square_bracket_parser_ = new Parser() {
    public boolean parse(PsiBuilder builder_, int level_) {
      return not_close_square_bracket(builder_, level_ + 1);
    }
  };
  final static Parser not_semicolon_parser_ = new Parser() {
    public boolean parse(PsiBuilder builder_, int level_) {
      return not_semicolon(builder_, level_ + 1);
    }
  };
}
