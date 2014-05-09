struct MyTup(int, int, f64);

struct Inches(int);

fn main() {
    let tup: (int, int, f64) = (10, 20, 30.0);
    match tup {
      (a, b, c) => println!("{}", a + b + (c as int))
    }

    let mytup: MyTup = MyTup(10, 20, 30.0);
    match mytup {
      MyTup(a, b, c) => println!("{}", a + b + (c as int))
    }

    let length_with_unit = Inches(10);
    let Inches(integer_length) = length_with_unit;
    println!("length is {} inches", integer_length);
}