#[deriving(Clone)]
enum List {
    Cons(u32, Box<List>),
    Nil
}

fn prepend(xs: List, value: u32) -> List {
    Cons(value, box xs)
}

fn eq(xs: &List, ys: &List) -> bool {
    // Match on the next node in both lists.
    match (xs, ys) {
        // If we have reached the end of both lists, they are equal.
        (&Nil, &Nil) => true,
        // If the current elements of both lists are equal, keep going.
        (&Cons(x, box ref next_xs), &Cons(y, box ref next_ys))
                if x == y => eq(next_xs, next_ys),
        // If the current elements are not equal, the lists are not equal.
        _ => false
    }
}

pub fn main() {
    let list = Cons(1, box Cons(2, box Cons(3, box Nil)));

    let _x = Cons(5, box Nil);
    let _y = _x.clone();

    let mut xs = Nil;
    xs = prepend(xs, 1);
    xs = prepend(xs, 1);
    xs = prepend(xs, 1);
    
    eq(&xs, &list);
}