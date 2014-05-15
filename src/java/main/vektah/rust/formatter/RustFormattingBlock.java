package vektah.rust.formatter;

import com.intellij.formatting.*;
import com.intellij.formatting.alignment.AlignmentStrategy;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.Ref;
import com.intellij.psi.PsiElement;
import com.intellij.psi.TokenType;
import com.intellij.psi.codeStyle.CommonCodeStyleSettings;
import com.intellij.psi.formatter.WrappingUtil;
import com.intellij.psi.formatter.common.AbstractBlock;
import com.intellij.psi.impl.source.tree.LeafPsiElement;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import com.intellij.util.containers.ContainerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import vektah.rust.psi.RustExpr;
import vektah.rust.psi.RustFnItem;
import vektah.rust.psi.RustTokenType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static vektah.rust.psi.RustTokens.*;

public class RustFormattingBlock extends AbstractBlock {
	public static final TokenSet BLOCKS_TOKEN_SET = TokenSet.create(
		STATEMENT_BLOCK,
		EXPR_PAREN,
		CLOSURE_EXPR_ARGS,
		FN_ARGS,
		CLOSURE_PROTOTYPE_ARGS,
		FN_PROTOTYPE_ARGS,
		EXPR_CALL,
		EXTERN_BLOCK
	);
	public static final TokenSet CURLY_CONTAINERS = TokenSet.create(
		STATEMENT_BLOCK, STRUCT_BODY_BLOCK, IMPL_BLOCK, MACRO_BRACE
	);
	public static final TokenSet PARENTHESIS_CONTAINERS = TokenSet.create(
		EXPR_PAREN, LET_ARGS, PROTOTYPE_ARGS, MACRO_PAREN
	);
	public static final TokenSet BRACKETS_CONTAINERS = TokenSet.create(
		MACRO_BRACKET
	);

	private final Indent myIndent;
	private final AlignmentStrategy myAlignmentStrategy;
	private final CommonCodeStyleSettings mySettings;
	private final SpacingBuilder mySpacingBuilder;
	private List<Block> mySubBlocks;

	public RustFormattingBlock(@NotNull ASTNode node,
							 @Nullable Alignment alignment,
							 @Nullable AlignmentStrategy alignmentStrategy,
							 @Nullable Wrap wrap,
							 @NotNull CommonCodeStyleSettings settings,
							 @NotNull SpacingBuilder spacingBuilder,
							 int binaryExpressionIndex) {
		super(node, wrap, alignment);
		myAlignmentStrategy = alignmentStrategy;
		mySettings = settings;
		mySpacingBuilder = spacingBuilder;
		myIndent = new RustIndentProcessor().getChildIndent(node, binaryExpressionIndex);
	}

	@Override
	public Indent getIndent() {
		return myIndent;
	}

	@NotNull
	@Override
	protected List<Block> buildChildren() {
		if (mySubBlocks == null) {
			mySubBlocks = buildSubBlocks();
		}
		return new ArrayList<Block>(mySubBlocks);
	}

	private List<Block> buildSubBlocks() {
		final List<Block> blocks = new ArrayList<Block>();
		final Alignment baseAlignment = Alignment.createAlignment(true);
		final Alignment baseAlignment2 = Alignment.createAlignment(true);
		final AlignmentStrategy alignmentStrategy = createOrGetAlignmentStrategy();
		final Ref<Wrap> chopDownIfLongWrap = new Ref<Wrap>();

			for (ASTNode child = myNode.getFirstChildNode(); child != null; child = child.getTreeNext()) {
				if (!shouldCreateBlockFor(child)) continue;
				blocks.add(createChildBlock(myNode, child, chopDownIfLongWrap, baseAlignment, baseAlignment2, alignmentStrategy, -1));
			}
		return Collections.unmodifiableList(blocks);
	}

	private static boolean shouldCreateBlockFor(ASTNode node) {
		return node.getTextRange().getLength() != 0 && node.getElementType() != TokenType.WHITE_SPACE;
	}

	private RustFormattingBlock createChildBlock(ASTNode parent, ASTNode child, Ref<Wrap> chopDownIfLongWrap, Alignment baseAlignment, Alignment baseAlignment2, @Nullable AlignmentStrategy alignmentStrategy, int binaryExpressionIndex) {
		Alignment alignment = getAlignment(parent, child, baseAlignment, baseAlignment2, binaryExpressionIndex);
		WrapType wrapType = calculateWrapType(parent, child);
		Wrap wrap;
		if (wrapType == WrapType.CHOP_DOWN_IF_LONG) {
			if (chopDownIfLongWrap.isNull()) {
				chopDownIfLongWrap.set(Wrap.createWrap(wrapType, true));
			}
			wrap = chopDownIfLongWrap.get();
		}
		else if (wrapType == null) {
			wrap = null;
		}
		else {
			wrap = Wrap.createWrap(wrapType, true);
		}
		return new RustFormattingBlock(child, alignment, alignmentStrategy, wrap, mySettings, mySpacingBuilder, binaryExpressionIndex);
	}

	@Nullable
	private WrapType calculateWrapType(@NotNull ASTNode parent, @NotNull ASTNode node) {
		IElementType parentType = parent.getElementType();
		PsiElement nodePsi = node.getPsi();

		if (parentType == LET_ARGS && nodePsi instanceof RustExpr) {
			return WrappingUtil.getWrapType(mySettings.CALL_PARAMETERS_WRAP);
		}

		return null;
	}

	@Nullable
	private Alignment getAlignment(@NotNull ASTNode parent, @NotNull ASTNode child, @Nullable Alignment baseAlignment, @Nullable Alignment baseAlignment2, int binaryExpressionIndex) {
		IElementType childType = child.getElementType();
		IElementType parentType = parent.getElementType();
		Alignment fromStrategy = calculateAlignmentFromStrategy(parent, child);
		if (fromStrategy != null) return fromStrategy;

		if (PARENTHESIS_CONTAINERS.contains(parentType)) {
			if (childType != OPEN_PAREN && childType != CLOSE_PAREN && childType != COMMA) {
				return baseAlignment;
			}
		}
		if (CURLY_CONTAINERS.contains(parentType)) {
			if (childType != OPEN_BRACE && childType != CLOSE_BRACE && childType != COMMA) {
				return baseAlignment;
			}
		}
		if (BRACKETS_CONTAINERS.contains(parentType)) {
			if (childType != OPEN_SQUARE_BRACKET && childType != CLOSE_SQUARE_BRACKET) {
				return baseAlignment;
			}
		}

		return null;
	}

	@Nullable
	private Alignment calculateAlignmentFromStrategy(@NotNull ASTNode parent, ASTNode child) {
		@NotNull IElementType childType = child.getElementType();
		@NotNull IElementType parentType = parent.getElementType();
		if (myAlignmentStrategy != null) {
			return myAlignmentStrategy.getAlignment(parentType, childType);
		}
		return null;
	}

	@Nullable
	private AlignmentStrategy createOrGetAlignmentStrategy() {
		PsiElement psi = getNode().getPsi();
		if (psi instanceof RustFnItem) {
			return AlignmentStrategy.createAlignmentPerTypeStrategy(ContainerUtil.list(STATEMENT_BLOCK), FN_ITEM, true);
		}

		return myAlignmentStrategy;
	}

	@Override
	@Nullable
	public Spacing getSpacing(@Nullable Block child1, @NotNull Block child2) {
		return mySpacingBuilder.getSpacing(this, child1, child2);
	}

	@NotNull
	@Override
	public ChildAttributes getChildAttributes(int newChildIndex) {
		Indent childIndent = getChildIndent(myNode.getElementType(), newChildIndex);
		IElementType type = newChildIndex > 0 ? getIElementType(newChildIndex) : null;
		Alignment alignment = getChildAlignment(type);

		if (childIndent != null) {
			return new ChildAttributes(childIndent, alignment);
		}

		if (type != null) {
			childIndent = getChildIndent(type, newChildIndex);
		}

		return new ChildAttributes(childIndent == null ? Indent.getNoneIndent() : childIndent, alignment);
	}

	@Nullable
	private Alignment getChildAlignment(@Nullable IElementType type) {

		return null;
	}

	@Nullable
	private IElementType getIElementType(int newChildIndex) {
		Block block = getSubBlocks().get(newChildIndex - 1);
		while (block instanceof RustFormattingBlock && !block.getSubBlocks().isEmpty()) {
			List<Block> subBlocks = block.getSubBlocks();
			Block childBlock = subBlocks.get(subBlocks.size() - 1);
			if (!(childBlock instanceof RustFormattingBlock)) break;
			else {
				ASTNode node = ((RustFormattingBlock) childBlock).getNode();
				PsiElement psi = node.getPsi();
				IElementType elementType = node.getElementType();
				if (elementType instanceof RustTokenType) break;
				if (psi instanceof LeafPsiElement) break;
			}
			block = childBlock;
		}
		return block instanceof RustFormattingBlock ? ((RustFormattingBlock) block).getNode().getElementType() : null;
	}

	@Nullable
	private Indent getChildIndent(@Nullable IElementType type, int newChildIndex) {

		if (BLOCKS_TOKEN_SET.contains(type)) {
			return Indent.getNormalIndent(false);
		}

		return null;
	}

	@Override
	public boolean isLeaf() {
		return myNode.getFirstChildNode() == null;
	}
}
