package simpl.typing;

import simpl.parser.Symbol;
import simpl.parser.ast.Sub;

public class TypeVar extends Type {

    private static int tvcnt = 0;

    private boolean equalityType;
    private Symbol name;

    public TypeVar(boolean equalityType) {
        this.equalityType = equalityType;
        name = Symbol.symbol("tv" + ++tvcnt);
    }

    @Override
    public boolean isEqualityType() {
        return equalityType;
    }

    @Override
    public Substitution unify(Type t) throws TypeCircularityError {
        // TODO
        if (t instanceof TypeVar)
        {
            //if (this.name.toString().equals(((TypeVar) t).name.toString()))
            if (this.equals(t))
                return Substitution.IDENTITY;
            else if (t.contains(this)) throw new TypeCircularityError();
            //else if (!this.isEqualityType()) return Substitution.of((TypeVar) t,this);
        }
        else if (t.contains(this)) throw new TypeCircularityError();
        return Substitution.of(this,t);
    }

    public String toString() {
        return "" + name;
    }

    @Override
    public boolean contains(TypeVar tv) {
        // TODO
        return (this.equals(tv));
        //return false;
    }

    @Override
    public Type replace(TypeVar a, Type t) {
        // TODO
        if (name.toString().equals(a.name.toString())) return t;
        else return this;
    }
}
