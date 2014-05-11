struct Point {
    x: f64,
    y: f64
}

pub struct Bar {
    // blah
    #[doc(hidden)]
    field: int,
    #[doc(hidden)]
    field2: int,
}

pub struct NoExpand<'t>(pub &'t str);

// Declare a public enum with two public variants
pub enum State {
    #[doc(hidden)]
    PubliclyAccessibleState,
    // blah
    PubliclyAccessibleState2,
}

pub fn main() {
    let mut mypoint = Point { x: 1.0, y: 1.0 };
    mypoint.y += 1.0; // `mypoint` is mutable, and its fields as well

    // With trailing comma is ok!
    Point { x: 1.0, y: 1.0, };

    match mypoint {
        Point { x: 0.0, y: yy } => println!("{}", yy),
        Point { x: xx,  y: yy } => println!("{} {}", xx, yy)
    }

    match mypoint {
        Point { x, .. } => println!("{}", x)
    }
}