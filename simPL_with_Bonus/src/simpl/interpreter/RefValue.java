package simpl.interpreter;

public class RefValue extends Value {

    public final int p;
    public final Value v;

    public RefValue(int p, Value v) {
        this.p = p;
        this.v = v;
    }

    public String toString() {
        return "ref@" + v;
    }

    @Override
    public boolean equals(Object other) {
        // TODO
        if (other instanceof RefValue)
        {
            return (p == ((RefValue)other).p);
        }
        else return false;
    }

    @Override
    public void mark(Mem M)
    {
        M.mark[p] = true;
        v.mark(M);
    }
}
