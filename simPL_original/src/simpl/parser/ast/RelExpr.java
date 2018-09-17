package simpl.parser.ast;

import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public abstract class RelExpr extends BinaryExpr {

    public RelExpr(Expr l, Expr r) {
        super(l, r);
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        TypeResult r1 = l.typecheck(E);
        TypeResult r2 = l.typecheck(r1.s.compose(E));
        Substitution s;
        s = r2.s.compose(r1.s);
        s = (s.apply(r1.t).unify(Type.INT)).compose(s);
        s = (s.apply(r2.t).unify(Type.INT)).compose(s);
        return TypeResult.of(s, Type.BOOL);
    }
}
