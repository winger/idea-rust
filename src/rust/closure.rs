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

// Takes a reference to a closure
fn closure_ref(f: &Fn(Figure) -> String + Send) {
    //...
}

// Takes boxed closure
fn boxed_closure(f: Box<Fn(Figure) -> String + Send>) {
    //...
}

// FIXME:
// Not so simple closure signature
//fn some_func<F>(f: F) where F: Fn(&Figure, (&Shape, char)) -> [String; 4] {
//    //...
//}

// Some typedefs
type MyFn1<'a> = FnMut(u32) -> String + 'a;
type MyFn2<'a> = FnOnce(Figure) + 'a;


pub fn main() {
    closure(|| 1);
    typed_closure(|a| println!("typed {}", a));
    double_closure(|g| g(1));

    // move || is the closure which will be spawned.
    spawn(move || {
        println!("I'm a new task")
    });
}