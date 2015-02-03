trait Graph {
    type Node: Show + Hash = SomeGraphNode;
    type Edge: Show + Hash;
    //...
}

impl Mul<Coord> for DeltaTime {
    type Output = Coord;
    fn mul(self, x: Coord) -> Coord { x * self.float_seconds() }
}

fn do_something_with<I: Iterator<Item=Coord>>(iter: I) {
    //...
}

fn some_func() -> SomeType<Coord, Item=i32> {
    //...
}
