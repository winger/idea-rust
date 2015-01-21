use std::task::spawn;

// Takes a closure and prints its result
fn closure<F>(f: F) where F: Fn() -> int {
    println!("{}", f())
}

// Takes a closure and calls it
fn typed_closure<F>(f: F) where F: Fn(int) {
    f(42);
}

// Takes a closure that takes a closure and calls it with its own closure
fn double_closure<F, G>(f: F) where F: Fn(G), G: Fn(int) {
    f(|x| println!("{}", x + 1))
}


pub fn main() {
    closure(|| 1);
    typed_closure(|a| println!("typed {}", a));
    double_closure(|g| g(1));

    // move || is the closure which will be spawned.
    spawn(move || {
        println!("I'm a new task")
    });
}