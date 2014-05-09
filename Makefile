# Thist must be intellijs build of jflex as found in the community edition source tree.
IDEA_JFLEX_DIR=$(shell echo ~/projects/intellij-community/tools/lexer)
GRAMMAR_KIT_DIR=$(shell echo ~/projects/Grammar-Kit)
IDEA_HOME=/opt/idea
RUSTC=~/projects/rust/x86_64-unknown-linux-gnu/stage2/bin/rustc

JFLEX_BIN=$(IDEA_JFLEX_DIR)/jflex-1.4/bin/jflex
GRAMMAR_KIT_JAR=grammar-kit.jar
IDEA_LIB=$(IDEA_HOME)/lib

SAMPLES=$(shell find src/rust -type f -iname "*.rs")

default: grammar lexer

grammar: src/java/gen/vektah/rust/RustParser.java
src/java/gen/vektah/rust/RustParser.java: src/bnf/RustGrammar.bnf
	java -cp '$(GRAMMAR_KIT_JAR):$(IDEA_LIB)/*' org.intellij.grammar.Main src/java/gen src/bnf/RustGrammar.bnf

lexer: src/java/gen/vektah/rust/RustLexer.java grammar
src/java/gen/vektah/rust/RustLexer.java: src/flex/RustLexer.flex
	$(JFLEX_BIN) -d src/java/gen/vektah/rust --nobak -charat --skel $(IDEA_JFLEX_DIR)/idea-flex.skeleton src/flex/RustLexer.flex

clean:
	rm -rf src/java/gen
	$(MAKE) grammar lexer

test: verify_samples


# Ensure all rust samples compile cleanly
verify_samples: $(SAMPLES)
	@for sample in $(SAMPLES) ; do \
		echo VERIFY $$sample ; \
		$(RUSTC) $$sample -o /tmp/rust_sample; \
	done

.PHONY: grammar lexer test
