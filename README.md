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

 - Intellij 13.1.5
 - Gradle

### Building
 * Add a properties.gradle file to the root directory of the project, with the following properties
    * libraries_intellij : the root directory of your intelliJ installation
    * rust_source : the root directory of the rust project (required to run tests)
 * Run ```gradle grammar``` to generate the grammar and lexer. 
 * Run ```gradle runIdea``` or ```gradle debugIdea``` to start the plugin.
 
### Contributing
We use the following IntelliJ plugins to develop this plugin:
 - Plugin Devkit
 - Grammar-Kit
 - JFlex Support
 - Gradle plugin
 
