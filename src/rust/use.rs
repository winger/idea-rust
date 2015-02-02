use std::iter::range_step;
use std::option::Option::{Some, None};
use std::collections::hash_map::{self, HashMap};

use foo::baz::foobaz as FOOBAZ;

mod foo {
extern crate core;

use foo::core::iter;
use self::baz::foobaz;
use foo::bar::foobar;

pub mod bar {
pub fn foobar() { }
}

pub mod baz {
use super::bar::foobar;
pub fn foobaz() { }
}
}


fn main() {
	FOOBAZ();
}