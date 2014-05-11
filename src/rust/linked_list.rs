#[deriving(Clone)]
enum List<T> {
    Cons(T, Box<List<T>>),
    Nil
}

fn prepend<T>(xs: List<T>, value: T) -> List<T> {
    Cons(value, box xs)
}

impl<T: Eq> Eq for List<T> {
    /// Compare two lists
    fn eq(&self, ys: &List<T>) -> bool {
        // Match on the next node in both lists.
        match (self, ys) {
            // If we have reached the end of both lists, they are equal.
            (&Nil, &Nil) => true,
            // If the current elements of both lists are equal, keep going.
            (&Cons(ref x, box ref next_xs), &Cons(ref y, box ref next_ys))
                    if x == y => next_xs == next_ys,
            // If the current elements are not equal, the lists are not equal.
            _ => false
        }
    }
}

pub fn main() {
    let list: List<int> = Cons(1, box Cons(2, box Cons(3, box Nil)));

    let _x: List<int> = Cons(5, box Nil);
    let _y = _x.clone();

    let mut xs: List<int>  = Nil;
    xs = prepend(xs, 1);
    xs = prepend(xs, 1);
    xs = prepend(xs, 1);

    assert!(xs != list);

    let xs = Cons('c', box Cons('a', box Cons('t', box Nil)));
    let ys = Cons('c', box Cons('a', box Cons('t', box Nil)));
    assert!(xs == ys);
}