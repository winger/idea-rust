trait Graph {
	type Node: Show + Hash = SomeGraphNode;
	type Edge: Show + Hash;
	// ...
}

impl Mul<Coord> for DeltaTime {
	type Output = Coord;
	fn mul(self, x: Coord) -> Coord { x * self.float_seconds() }
}
