# Thist must be intellijs build of jflex as found in the community edition source tree.
IDEA_JFLEX_DIR=~/projects/intellij-community/tools/lexer/
GRAMMAR_KIT_DIR=~/projects/Grammar-Kit

default:
	$(IDEA_JFLEX_DIR)/jflex-1.4/bin/jflex -d gen/vektah/rust --nobak -charat --skel $(IDEA_JFLEX_DIR)/idea-flex.skeleton src/flex/RustLexer.flex
