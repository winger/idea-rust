extern crate libc;
use libc::{c_char, FILE};

#[allow(dead_code)]
extern {
    fn fopen(filename: *const c_char, mode: *const c_char) -> *mut FILE;
}

extern "stdcall" { }

#[link(name = "crypto")]
extern { }


pub fn main() {
}