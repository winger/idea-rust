fn some_func(state: StateOfSomething) {

    if let Simple(Text{ref mut text, ..}) = state.content {
        *text = self.update_and_get_value_text(dt);
    };

    if let Some(x) = foo() {
        do_something_with(x)
    };

    if let Some(x) = foo() {
        do_something_with(x)
    } else {
        default_behavior()
    };

    if cond() {
        doSomething()
    } else if let Some(x) = foo() {
        do_something_with(x)
    } else {
        default_behavior()
    };

    if let Some(x) = foo() {
        do_something_with(x)
    } else if cond() {
        do_something()
    } else if other_cond() {
        do_something_else()
    };

    while let Some(x) = state.iter.next() {
        do_something_with(x)
    };

}
