package simpl.parser.ast;

import simpl.interpreter.*;
import simpl.interpreter.lib.fst;
import simpl.interpreter.lib.hd;
import simpl.interpreter.lib.snd;
import simpl.interpreter.lib.tl;
import simpl.interpreter.pcf.iszero;
import simpl.interpreter.pcf.pred;
import simpl.interpreter.pcf.succ;
import simpl.typing.ArrowType;
import simpl.typing.Substitution;
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


        if ((t1.t instanceof ArrowType) && (((ArrowType)t1.t).t1 instanceof TypeVar)) {
            s1 = s.apply(t1.t).unify(new ArrowType(s.apply(t2.t), t3));
            st = s1.compose(s);
            s = (t3.unify(st.apply(t3))).compose(s);
            return TypeResult.of(s,st.apply(t3));
        }
        else
        {
            /* ---original */
            s1 = s.apply(t1.t).unify(new ArrowType(s.apply(t2.t),t3));
            s = s1.compose(s);
            return TypeResult.of(s,s.apply(t3));
           /*---no poly */
        }
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        Value v2 = new ThunkValue(r,s);
        FunValue f = (FunValue) l.forceEval(s);
        if ((f instanceof fst) || (f instanceof snd) || (f instanceof hd) || (f instanceof tl)
                || (f instanceof iszero) || (f instanceof pred) || (f instanceof succ))
        {
            v2 = r.forceEval(s);
        }
        return f.e.eval(State.of(new Env(f.E,f.x,v2),s.M,s.p));
    }
}
