struct Point {
    x: f64,
    y: f64
}

enum Shape {
    Circle(Point, f64),
    Rectangle(Point, Point)
}

impl Shape {
    fn draw(&self) {
        match *self {
            Circle(..) => 1,
            Rectangle(..) => 2
        };
    }

    fn foo() -> f64 { 0.1 }
}

pub fn main() {
    let s = Circle(Point { x: 1.0, y: 2.0 }, 3.0);
    s.draw();

    Shape::foo();
}