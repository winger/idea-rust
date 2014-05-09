use std::task::spawn;

// Takes a closure and prints its result
fn closure(f: || -> int) {
    println!("{}", f())
}

// Takes a closure and calls it
fn typed_closure(f: |int|) {
    f(42);
}

// Takes a closure that takes a closure and calls it with its own closure
fn double_closure(f: |g: |int||) {
    f(|x| println!("{}", x + 1))
}


pub fn main() {
    closure(|| 1);
    typed_closure(|a| println!("typed {}", a));
    double_closure(|g: |int|| g(1));

    // proc is the closure which will be spawned.
    spawn(proc() {
        println!("I'm a new task")
    });
}