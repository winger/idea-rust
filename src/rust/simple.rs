pub fn main() {
    let a = true;
    let b = false;

    if a {
        print!("a");
    } else if b {
        print!("b");
    } else {
        print!("c")
    }

    let mut a: int = 0;
    let b: bool = true;
    let c: bool = false;
    let _numbers = [1, 2, 3];
    let t = _numbers[a..];

    // As a naked if
    if b || c {
        a = 1;
    }

    a << 32 > a << 16;
    a >> 32 < a >> 16;

    a >>= 1;

    println!("{}", a);

    // As an expression
    a = if !c {
        10
    } else {
        20
    };

    'foo: loop {
        println!("{}", a);
        break 'foo;
    }

    println!("{}", a);

    // Tuple expressions. They are not at all ambiguious.
    (0,);
    (0.0, 4.5);
    ("a", 4u, true);

    // Tuple destructuring let with a statement block!
    let (_cap, _cap_name, _oldflags) = {
        (1, 2, 3)
    };
}