package simpl.interpreter;


public class ConsValue extends Value {

    public Value v1, v2;
    public int len;

    public ConsValue(Value v1, Value v2) {
        this.v1 = v1;
        this.v2 = v2;
        if (v2 instanceof NilValue)
        {
            len = 1;
        }
        else len = 1 + ((ConsValue)v2).len;
    }

    public String toString() {
        // TODO
        return "list@"+this.len;
        //return null;
    }

    @Override
    public boolean equals(Object other) {
        // TODO
        if (other instanceof ConsValue)
        {
            ConsValue v = (ConsValue) other;
            return (v1.equals(v.v1) && v2.equals(v.v2));
        }
        else return false;
    }
}
