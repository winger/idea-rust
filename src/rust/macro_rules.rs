macro_rules! say_hello {
	() => (
		println!("Hello!");
	)
}

macro_rules! another_hello [
	() => (
		println!("Hello!");
	)
];


macro_rules! check_gl_err(
	() => (
		{
			let err = gl::GetError();
			assert!(err == gl::NO_ERROR, format!("{} or 0x{:x}", err, err));
		}
	)
);
