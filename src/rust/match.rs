enum List<X> { Nil, Cons(X, Box<List<X>>) }

#[allow(unreachable_code)]
#[allow(unused_variable)]
pub fn main() {
    let x: List<int> = Cons(10, box Cons(11, box Nil));

    // Enum matching
    match x {
        Cons(_, box Nil) => fail!("singleton list"),
        Cons(..)      => return,
        Nil           => fail!("empty list")
    }

    match x {
        Cons(a, box Cons(b, _)) => {
            1;
        }
        Cons(10, _) => {
            2;
        }, // This comma is allowed!
        Nil => {
            return;
        }
        _ => {
            fail!();
        }
    }

    // Literal scalar matching
    let a = 5;
    let message = match a {
      0 | 1  => "not many",
      2 ... 9 => "a few",
      _      => "lots"
    };

    let maybe_digit = Some(10);

    let message = match maybe_digit {
      Some(x) if x < 10 => "Digit",
      Some(x) => "Other",
      None => fail!()
    };
}

// vector matching
#[allow(dead_code)]
fn is_symmetric(list: &[uint]) -> bool {
    match list {
        [] | [_]                   => true,
        [x, inside.. , y] if x == y => is_symmetric(inside),
        _                          => false
    }
}

#[allow(dead_code)]
fn is_sorted(list: &List<int>) -> bool {
    match *list {
        Nil | Cons(_, box Nil) => true,
        Cons(x, ref r @ box Cons(y, _)) => (x <= y) && is_sorted(*r)
    }
}