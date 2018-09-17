package simpl.parser.ast;

import simpl.interpreter.*;
import simpl.parser.Symbol;
import simpl.typing.ArrowType;
import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;
import simpl.typing.TypeVar;

public class App extends BinaryExpr {

    public App(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(" + l + " " + r + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        TypeResult t1 = l.typecheck(E), t2;
        Substitution s, s1, st;
        t2 = r.typecheck(t1.s.compose(E));
        s = t2.s.compose(t1.s);
        TypeVar t3 = new TypeVar(true);


        /* ---original */
        s1 = s.apply(t1.t).unify(new ArrowType(s.apply(t2.t),t3));
        s = s1.compose(s);
        return TypeResult.of(s,s.apply(t3));
        /*---no poly */


    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        Value v2 = r.eval(s);
        FunValue f = (FunValue) l.eval(s);
        return f.e.eval(State.of(new Env(f.E,f.x,v2),s.M,s.p));
    }
}
