fn line(a: int, b: int, x: int) -> int {
    return a * x + b;
}

fn line2(a: int, b: int, x: int) -> int {
    a * x + b
}

fn do_nothing_the_hard_way() -> () { return (); }

fn do_nothing_the_easy_way() { }

fn line3(a: int, b: int, x: int) -> int { a * x + b  }
fn oops(a: int, b: int, x: int) -> ()  { a * x + b; }

fn first((value, _): (int, f64)) -> int { value }

pub fn main() {
    line(1, 2, 3);
    line2(2, 3, 4);
    do_nothing_the_hard_way();
    do_nothing_the_easy_way();
    first((1, 2.0));

    assert!(8 == line3(5, 3, 1));
    assert!(() == oops(5, 3, 1));
}
