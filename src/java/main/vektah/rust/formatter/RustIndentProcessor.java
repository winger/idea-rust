/*
 * Copyright 2012-2013 Sergey Ignatov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package vektah.rust.formatter;

import com.intellij.formatting.Indent;
import com.intellij.lang.ASTNode;
import com.intellij.psi.formatter.FormatterUtil;
import com.intellij.psi.tree.IElementType;

import static vektah.rust.psi.RustTokens.*;

public class RustIndentProcessor {
	public RustIndentProcessor() {
	}

	public Indent getChildIndent(ASTNode node, int binaryExpressionIndex) {
		if (binaryExpressionIndex > 0) return Indent.getNormalIndent();

		IElementType elementType = node.getElementType();
		ASTNode parent = node.getTreeParent();
		IElementType parentType = parent != null ? parent.getElementType() : null;
		ASTNode grandfather = parent != null ? parent.getTreeParent() : null;
		IElementType grandfatherType = grandfather != null ? grandfather.getElementType() : null;
		ASTNode prevSibling = FormatterUtil.getPreviousNonWhitespaceSibling(node);
		IElementType prevSiblingType = prevSibling != null ? prevSibling.getElementType() : null;

		if (parent == null || parent.getTreeParent() == null) {
			return Indent.getNoneIndent();
		}

		if (
			parentType == EXPR_PAREN ||
			parentType == FN_ARGS ||
			parentType == FN_PROTOTYPE_ARGS ||
			parentType == CALL_PARAMS ||
			parentType == EXPR_TUPLE_BODY ||
			parentType == MACRO_PAREN ||
			parentType == STRUCT_BODY_TUPLE
		) {
			return (elementType == OPEN_PAREN || elementType == CLOSE_PAREN) ?
				Indent.getNoneIndent() : Indent.getNormalIndent();
		}

		if (parentType == CLOSURE_EXPR_ARGS) {
			return (elementType == BITWISE_OR) ? Indent.getNoneIndent() : Indent.getNormalIndent();
		}

		if (parentType == EXPR_VECTOR || parentType == MACRO_BRACKET) {
			return (elementType == OPEN_SQUARE_BRACKET || elementType == CLOSE_SQUARE_BRACKET) ?
				Indent.getNoneIndent() : Indent.getNormalIndent();
		}

		if (
			parentType == STATEMENT_BLOCK ||
			parentType == STRUCT_BODY_BLOCK ||
			parentType == ENUM_BODY ||
			parentType == IMPL_BLOCK ||
			parentType == MACRO_BRACE ||
			parentType == TRAIT_BLOCK
		) {
			return (elementType == OPEN_BRACE || elementType == CLOSE_BRACE) ?
				Indent.getNoneIndent() : Indent.getNormalIndent();
		}

		if (parentType == MATCH_BODY || parentType == MATCH_ARM) {
			return (elementType == MATCH_FILTER) ? Indent.getNoneIndent() : Indent.getNormalIndent();
		}

		if (
			elementType == CLOSURE_BODY ||
			elementType == STRUCT_INITIALIZER_LIST ||
			elementType == STRUCT_DEFAULT
		) {
			return Indent.getNormalIndent();
		}

		if (
			elementType == DOT || prevSiblingType == DOT ||
			elementType == ASSIGN || prevSiblingType == ASSIGN ||
			elementType == WHERE_CLAUSE || prevSiblingType == KW_WHERE
		) {
			return Indent.getContinuationIndent();
		}

		return Indent.getNoneIndent();
	}
}
