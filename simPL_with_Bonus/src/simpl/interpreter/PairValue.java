package simpl.interpreter;

import simpl.parser.ast.Pair;

public class PairValue extends Value {

    public final Value v1, v2;

    public PairValue(Value v1, Value v2) {
        this.v1 = v1;
        this.v2 = v2;
    }

    public String toString() {
        return "pair@" + v1 + "@" + v2;
    }

    @Override
    public boolean equals(Object other) {
        // TODO
        if (other instanceof PairValue)
        {
            PairValue oth = (PairValue) other;
            return  ((v1.equals(oth.v1) && (v2.equals(oth.v2))));
        }
        else return false;
    }

    @Override
    public void mark(Mem M)
    {
        v1.mark(M);
        v2.mark(M);
    }
}
