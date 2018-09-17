package simpl.interpreter.lib;

import simpl.interpreter.ConsValue;
import simpl.interpreter.Env;
import simpl.interpreter.FunValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.parser.ast.Expr;
import simpl.interpreter.NilValue;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class hd extends FunValue {

    public hd() {
        // TODO
        //class
        //Fst fe();
        super(Env.empty, Symbol.symbol("hdx"), new Expr() {
            @Override
            public TypeResult typecheck(TypeEnv E) throws TypeError {
                return null;
            }

            @Override
            public Value eval(State s) throws RuntimeError {

                Value v = s.E.get(Symbol.symbol("hdx"));
                if (v instanceof NilValue)
                {
                    throw new RuntimeError("hd parameter not illegal!");
                }
                return ((ConsValue)v).v1;
            }
        });

    }

    public String toString() {
        return "fun";
    }
}
