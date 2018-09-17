package simpl.interpreter.lib;

import simpl.interpreter.Env;
import simpl.interpreter.FunValue;
import simpl.interpreter.PairValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.parser.ast.Expr;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;
public class snd extends FunValue {

    public snd() {
        // TODO
        //class
        //Fst fe();
        super(Env.empty, Symbol.symbol("sndx"), new Expr() {
            @Override
            public TypeResult typecheck(TypeEnv E) throws TypeError {
                return null;
            }

            @Override
            public Value eval(State s) throws RuntimeError {
                PairValue v = (PairValue) s.E.get(Symbol.symbol("sndx"));
                return v.v2;
            }
        });
        //super(Env.empty, smb, new Name(smb));*/

    }

    public String toString() {
        return "fun";
    }
}