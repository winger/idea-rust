pub static ZERO:      u8 = 0;

static foo: &'static [(int, int)] = &[(1, 2), (3, 4),];

pub fn main() {
    foo[0];
    let numbers = [1, 2, 3];
}