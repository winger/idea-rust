pub fn main() {
    'c';
    '\'';


    "asdf";
    "asdf \" ffff";
    "aa\t\n";
    "\xFF";
    "\u0A4f";
    "\uBEEFCAFE";
    "soe\\path\\to\\hello.txt";

    "\r";
    "イロハニホヘト チリヌルヲ ワカヨタレソ ツネ";

    r"foo";
    r##"foo #"# bar"##;

    123; 0xff00;                       // type determined by program context
                                       // defaults to int in absence of type
                                       // information

    123u;                              // type uint
    123_u;                             // type uint
    0xff_u8;                           // type u8
    0o70_i16;                          // type i16
    0b1111_1111_1001_0000_i32;         // type i32

    123.0;                             // type f64
    0.1;                               // type f64
    0.1f32;                            // type f32
    12E+99_f64;                        // type f64

    0.;                                 // Uugh

    1e20;                               // Okay...
    1e20f64;                            // Totally valid, barely resembles a number
}