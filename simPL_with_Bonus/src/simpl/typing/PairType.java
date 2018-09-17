package simpl.typing;

public final class PairType extends Type {

    public Type t1, t2;

    public PairType(Type t1, Type t2) {
        this.t1 = t1;
        this.t2 = t2;
    }

    @Override
    public boolean isEqualityType() {
        // TODO
        return t1.isEqualityType()&&t2.isEqualityType();
    }

    @Override
    public Substitution unify(Type t) throws TypeError {
        // TODO
        Substitution s1,s2;
        if (t instanceof PairType)
        {
            s1 = this.t1.unify(((PairType) t).t1);
            s2 = s1.apply(this.t2).unify(s1.apply(((PairType) t).t2));
            return s2.compose(s1);
        }
        else if (t instanceof TypeVar)
        {
            return t.unify(this);
        }
        else throw new TypeMismatchError();
    }

    @Override
    public boolean contains(TypeVar tv) {
        // TODO
        return (this.t1.contains(tv)||this.t2.contains(tv));
    }

    @Override
    public Type replace(TypeVar a, Type t) {
        // TODO
        return new PairType(this.t1.replace(a,t),this.t2.replace(a,t));
    }

    public String toString() {
        return "(" + t1 + " * " + t2 + ")";
    }
}
