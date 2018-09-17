package simpl.parser.ast;

import simpl.interpreter.*;
import simpl.typing.*;

public class Ref extends UnaryExpr {

    public Ref(Expr e) {
        super(e);
    }

    public String toString() {
        return "(ref " + e + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        TypeResult r = e.typecheck(E);
        return TypeResult.of(r.s,new RefType(r.t));
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        int tmp = s.M.GetFreeSpace(s.E);
        s.p.set(tmp+1);
        Value v = e.forceEval(s);
        s.M.put(tmp,v);
        //s.M.print();
        return new RefValue(tmp,v);
    }
}
