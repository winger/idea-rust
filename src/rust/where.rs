pub fn unite<I>(mut boxes: I) -> BoundingBox
        where I: Iterator<Item=BoundingBox> {
    Unknown
}

pub fn with_loaded<F>(shape: &Shape, cache: &mut ShapeCache, f: F)
        where F: Fn {
    //...
}

impl Foo<A, B> where (A, B): SomeTrait + Send {
    //...
}

struct Foo<A, B> where A: Shape, B: SomeTrait + Send, {
    some_field: A,
    //...
}

trait Foo<A,B> : C where A: Iterator, A::Item: Color, B: SomeTrait + Send {
    //...
}
