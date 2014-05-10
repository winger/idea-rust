#![feature(struct_variant)]

use std::f64;

struct Point {
    x: f64,
    y: f64
}

enum Direction {
    North,
    East,
    South,
    West
}

enum Color {
  Red = 0xff0000,
  Green = 0x00ff00,
  Blue = 0x0000ff
}

enum Shape {
    Circle(Point, f64),
    Rectangle(Point, Point)
}

enum Shape2 {
    Circle2 { center: Point, radius: f64 },
    Rectangle2 { top_left: Point, bottom_right: Point }
}

fn area1(sh: Shape) -> f64 {
    match sh {
        Circle(_, size) => f64::consts::PI * size * size,
        Rectangle(Point { x, y }, Point { x: x2, y: y2 }) => (x2 - x) * (y2 - y)
    }
}

fn area2(sh: Shape2) -> f64 {
    match sh {
        Circle2 { radius: radius, .. } => f64::consts::PI * radius * radius,
        Rectangle2 { top_left: top_left, bottom_right: bottom_right } => {
            (bottom_right.x - top_left.x) * (top_left.y - bottom_right.y)
        }
    }
}

fn point_from_direction(dir: Direction) -> Point {
    match dir {
        North => Point { x:  0.0, y:  1.0 },
        East  => Point { x:  1.0, y:  0.0 },
        South => Point { x:  0.0, y: -1.0 },
        West  => Point { x: -1.0, y:  0.0 }
    }
}

pub fn main() {
    println!( "{:?} => {}", North, North as int );
    let dir = North;
    let _col = Red;
    let shape = Circle(Point {x: 1.0, y: 2.0}, 3.0);
    let shape2 = Circle2{center: Point {x: 1.0, y: 2.0}, radius: 3.0};

    area1(shape);
    area2(shape2);
    point_from_direction(dir);
}