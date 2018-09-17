package simpl.parser.ast;

import simpl.typing.*;

public abstract class EqExpr extends BinaryExpr {

    public EqExpr(Expr l, Expr r) {
        super(l, r);
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        TypeResult r1 = l.typecheck(E);
        TypeResult r2 = r.typecheck(r1.s.compose(E));
        Substitution s;
        s = r2.s.compose(r1.s);
        if (r1.t.isEqualityType() && r2.t.isEqualityType())
        {
            s = (s.apply(r1.t).unify(s.apply(r2.t))).compose(s);
            return TypeResult.of(s,Type.BOOL);
        }
        else throw new TypeMismatchError();
    }
}
