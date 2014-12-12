idea-rust
=========

Language support for the Rust Language

PLEASE NOTE: This does not yet do any auto completion.
It is JUST the lexer and highlighting for .rs files.

The rest is coming soon!

Plugin page
===========
http://plugins.jetbrains.com/plugin/7438

Development
========

### Prerequisites
If you want to live on the bleeding edge (or help out!) you will need:
 - Intellij
 - Plugin Devkit (plugin)
 - Grammar-Kit (plugin)
 - JFlex Support (plugin)
 - UI Designer (plugin)

### Building
 - Open up the project file included in the repo
 - Navigate to src/flex/vektah/rust/RustLexer.flex, Right Click -> Run Jflex generator
 - Remove the RustLexerAdapter - jflex outputs in
 - Navigate to src/bnf/RustGrammar.bnf, Right Click -> Generate Parser Code
 - Run 'idea-rust'
