struct Point {
    x: f64,
    y: f64
}

pub fn main() {
    let mut mypoint = Point { x: 1.0, y: 1.0 };
    mypoint.y += 1.0; // `mypoint` is mutable, and its fields as well

    match mypoint {
        Point { x: 0.0, y: yy } => println!("{}", yy),
        Point { x: xx,  y: yy } => println!("{} {}", xx, yy)
    }

    match mypoint {
        Point { x, .. } => println!("{}", x)
    }
}