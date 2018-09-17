package simpl.parser.ast;

import simpl.typing.*;

public abstract class ArithExpr extends BinaryExpr {

    public ArithExpr(Expr l, Expr r) {
        super(l, r);
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        TypeResult t1 = l.typecheck(E);
        TypeResult t2 = r.typecheck(t1.s.compose(E));
        Substitution s = t2.s.compose(t1.s);
        Substitution s1 = s.apply(t1.t).unify(Type.INT);
        s = s1.compose(s);
        Substitution s2 = s.apply(t2.t).unify(Type.INT);
        s = s2.compose(s);
        return TypeResult.of(s,Type.INT);
    }
}
