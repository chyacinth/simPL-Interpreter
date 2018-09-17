package simpl.interpreter;

import simpl.parser.ast.Expr;

public class ThunkValue extends Value {

    Value val;
    boolean evaluated;
    State state;
    Expr expr;

    public ThunkValue(Expr e, State s) {
        expr = e;
        //env = E;
        state = s;
        evaluated = false;
    }

    public Value value() throws RuntimeError
    {
        if (!this.evaluated)
        {
            val = expr.forceEval(state);
            evaluated = true;
        }
        return val;
    }
    public String toString() {
        return "thunk";
    }

    @Override
    public boolean equals(Object other) {
        // TODO
        return false;
    }

    @Override
    public void mark(Mem M)
    {
        if (evaluated)
        {
            if (val instanceof RefValue)
            {
                M.mark[((RefValue)val).p] = true;
            }
            val.mark(M);
        }
    }
}

