const KEY_WORDS    : uint =  8; // 8 words for the 256-bit key

pub const ZERO:      uint = 0;

const FOO: &'static [(uint, uint)] = &[(1, 2), (3, 4),];

pub fn main() {
    const VAL : uint = 34;
    let numbers = [KEY_WORDS, VAL, ZERO, FOO[0].val0()];
    println!("{}", numbers[0]);
}