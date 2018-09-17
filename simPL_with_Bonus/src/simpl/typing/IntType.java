package simpl.typing;

final class IntType extends Type {

    protected IntType() {
    }

    @Override
    public boolean isEqualityType() {
        // TODO
        return true;
        //return false;
    }

    @Override
    public Substitution unify(Type t) throws TypeError {
        // TODO
        if (t instanceof IntType)
        {
            return Substitution.IDENTITY;
        }
        else if (t instanceof TypeVar)
        {
            return  Substitution.of((TypeVar) t,this);
        }
        else
        throw new TypeMismatchError();
    }

    @Override
    public boolean contains(TypeVar tv) {
        // TODO
        return false;
    }

    @Override
    public Type replace(TypeVar a, Type t) {
        // TODO
        return this;
    }

    public String toString() {
        return "int";
    }
}
