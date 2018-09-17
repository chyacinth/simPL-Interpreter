package simpl.parser.ast;

import simpl.interpreter.Env;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.typing.Substitution;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class Let extends Expr {

    public Symbol x;
    public Expr e1, e2;

    public Let(Symbol x, Expr e1, Expr e2) {
        this.x = x;
        this.e1 = e1;
        this.e2 = e2;
    }

    public String toString() {
        return "(let " + x + " = " + e1 + " in " + e2 + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        TypeResult t1 = this.e1.typecheck(E);
        TypeResult t2 = this.e2.typecheck(TypeEnv.of(t1.s.compose(E),x,t1.t));
        Substitution s = t2.s.compose(t1.s);
        return TypeResult.of(s,s.apply(t2.t));
        //return null;
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        Value v1 = e1.eval(s);
        Value v2 = e2.eval(State.of(new Env(s.E,x,v1),s.M,s.p));
        return v2;
        //return null;
    }
}
