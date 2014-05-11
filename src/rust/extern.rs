extern crate libc;
use libc::{c_char, FILE};

#[allow(dead_code)]
extern {
    fn fopen(filename: *c_char, mode: *c_char) -> *FILE;
}

extern "stdcall" { }

#[link(name = "crypto")]
extern { }


pub fn main() {
}