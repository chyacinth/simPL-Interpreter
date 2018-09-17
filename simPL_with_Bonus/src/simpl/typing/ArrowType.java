package simpl.typing;

public final class ArrowType extends Type {

    public Type t1, t2;

    public ArrowType(Type t1, Type t2) {
        this.t1 = t1;
        this.t2 = t2;
    }

    @Override
    public boolean isEqualityType() {
        // TODO
        return false;
    }

    @Override
    public Substitution unify(Type t) throws TypeError {
        // TODO
        Substitution s1, s2;
        if (t instanceof ArrowType)
        {
            s1 = t1.unify(((ArrowType) t).t1);
            s2 = s1.apply(t2).unify(s1.apply(((ArrowType) t).t2));
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
        return (t1.contains(tv)||t2.contains(tv));
        //return false;
    }

    @Override
    public Type replace(TypeVar a, Type t) {
        // TODO
        return new ArrowType(t1.replace(a,t),t2.replace(a,t));
        //return null;
    }

    public String toString() {
        return "(" + t1 + " -> " + t2 + ")";
    }
}
