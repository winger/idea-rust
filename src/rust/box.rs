enum List<X> { Nil, Cons(X, Box<List<X>>) }

pub fn main() {
    let _a = box 10;
    let _x: List<int> = Cons(10, box Cons(11, box Nil));
}