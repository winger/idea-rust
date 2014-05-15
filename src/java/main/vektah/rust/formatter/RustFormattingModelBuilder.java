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

import com.intellij.formatting.FormattingModel;
import com.intellij.formatting.FormattingModelBuilder;
import com.intellij.formatting.FormattingModelProvider;
import com.intellij.formatting.SpacingBuilder;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.codeStyle.CommonCodeStyleSettings;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;
import vektah.rust.RustLanguage;

import static vektah.rust.psi.RustTokens.*;

public class RustFormattingModelBuilder implements FormattingModelBuilder {
	@NotNull
	@Override
	public FormattingModel createModel(PsiElement element, CodeStyleSettings settings) {
		CommonCodeStyleSettings commonSettings = settings.getCommonSettings(RustLanguage.INSTANCE);
		SpacingBuilder spacingBuilder = createSpacingBuilder(commonSettings);
		RustFormattingBlock block = new RustFormattingBlock(element.getNode(), null, null, null, commonSettings, spacingBuilder, -1);
		return FormattingModelProvider.createFormattingModelForPsiFile(element.getContainingFile(), block, settings);
	}

	private static SpacingBuilder createSpacingBuilder(@NotNull CommonCodeStyleSettings settings) {
		TokenSet keywords = TokenSet.create(
			KW_AS, KW_BREAK, KW_CRATE, KW_ELSE, KW_ENUM, KW_EXTERN, KW_FALSE, KW_FN, KW_FOR, KW_IF, KW_IMPL,
			KW_IN, KW_LET, KW_LOOP, KW_MATCH, KW_MOD, KW_PRIV, KW_PROC, KW_PUB, KW_RETURN, KW_CONTINUE, KW_MACRO_RULES,
			KW_SELF, KW_STATIC, KW_STRUCT, KW_SUPER, KW_TRUE, KW_TRAIT, KW_TYPE, KW_UNSAFE, KW_USE, KW_WHILE
		);

		CodeStyleSettings codeStyleSettings = new CodeStyleSettings();

		return new SpacingBuilder(codeStyleSettings, RustLanguage.INSTANCE)
			.before(COMMA).spaceIf(settings.SPACE_BEFORE_COMMA)
			.after(COMMA).spaceIf(settings.SPACE_AFTER_COMMA)
			.before(SEMICOLON).none()


			.after(OPEN_SQUARE_BRACKET).none()
			.before(CLOSE_SQUARE_BRACKET).none()
			.after(OPEN_SQUARE_BRACKET).none()
			.before(CLOSE_BRACE).none()

			.withinPair(OPEN_PAREN, CLOSE_PAREN).spaceIf(settings.SPACE_WITHIN_METHOD_CALL_PARENTHESES)

			.around(keywords).spaces(1)

			// &mut and *mut
			.between(MULTIPLY, KW_MUT).spaces(0)
			.between(BITWISE_AND, KW_MUT).spaces(0)
			;
	}

	@Override
	public TextRange getRangeAffectingIndent(PsiFile psiFile, int i, ASTNode astNode) {
		return null;
	}
}