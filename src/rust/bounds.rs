struct LifetimeBounds<'a> {
    a: Box<SomeTrait + 'a>,
    b: [SomeTrait + 'a; 4],
    c: SomeTrait + 'a,
    d: (SomeTrait + 'a, i32),
}

struct KindBounds {
    a: Box<SomeTrait + Send>,
    b: [SomeTrait + Send; 4],
    c: SomeTrait + Send,
    d: (SomeTrait + Send, i32),
}

struct MultiBounds<'a> {
    a: Box<SomeTrait + Send + 'a>,
    b: [SomeTrait + 'a + Send; 4],
    c: SomeTrait + Send + 'a,
    d: (SomeTrait + 'a + Send, i32),
}

struct Tuple(SomeTrait + Send + 'a);

fn bounded_arg<'a>(arg: SomeTrait + 'a + Send,
                   (a, b): SomeTrait + Send + 'a) {
    //...
}
