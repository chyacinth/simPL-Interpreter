package simpl.interpreter.lib;

import simpl.interpreter.*;
import simpl.parser.Symbol;
import simpl.parser.ast.Expr;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class tl extends FunValue {

    public tl() {
        // TODO
        super(Env.empty, Symbol.symbol("tlx"), new Expr() {
            @Override
            public TypeResult typecheck(TypeEnv E) throws TypeError {
                return null;
            }

            @Override
            public Value eval(State s) throws RuntimeError {
                //ConsValue v = (ConsValue) s.E.get(Symbol.symbol("tlx"));
                Value v = s.E.get(Symbol.symbol("tlx"));
                if (v instanceof NilValue)
                {
                    throw new RuntimeError("hd parameter not illegal!");
                }
                return ((ConsValue)v).v2;
            }
        });

    }

    public String toString() {
        return "fun";
    }
}
