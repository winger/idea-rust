pub struct Reader<'a, T: 'a> {
  position: uint,
  data: &'a [T]
}

pub struct Change<'a, T: 'a> {
  pub add_nodes: &'a [Node<'a, T>],
  pub modify_nodes: &'a [Node<'a, T>],
  pub delete_nodes: &'a [Node<'a, T>],
}

struct RendererWithModelMatrix<'a, 'b: 'a> {
  backend: &'a mut (Renderer + 'b),
  matrix: Matrix,
  something_else: &'a (Renderer + Send),
}

trait A<'a, T: 'a> {
  fn ke(self) -> T;
}
