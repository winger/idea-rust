# Thist must be intellijs build of jflex as found in the community edition source tree.
IDEA_JFLEX_DIR=./src/flex
GRAMMAR_KIT_DIR=$(shell echo ~/projects/Grammar-Kit)
IDEA_HOME=/opt/idea
RUSTC=~/projects/rust/x86_64-unknown-linux-gnu/stage2/bin/rustc

JFLEX_BIN=java -jar $(IDEA_JFLEX_DIR)/JFlex.jar
GRAMMAR_KIT_JAR=grammar-kit.jar
IDEA_LIB=$(IDEA_HOME)/lib

SAMPLES=$(shell find src/rust -type f -iname "*.rs")

default: lexer

lexer: src/flex/vektah/rust/RustLexer.java
src/flex/vektah/rust/RustLexer.java: src/flex/vektah/rust/RustLexer.flex
	$(JFLEX_BIN) --nobak -charat --skel $(IDEA_JFLEX_DIR)/idea-flex.skeleton src/flex/vektah/rust/RustLexer.flex

clean:
	rm -rf gen src/flex/vektah/rust/*.java
	$(MAKE) grammar lexer

test: verify_samples


# Ensure all rust samples compile cleanly
verify_samples: $(SAMPLES)
	@for sample in $(SAMPLES) ; do \
		echo VERIFY $$sample ; \
		$(RUSTC) $$sample -o /tmp/rust_sample; \
	done

.PHONY: grammar lexer test
