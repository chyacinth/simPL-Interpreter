package simpl.interpreter.pcf;

import simpl.interpreter.Env;
import simpl.interpreter.FunValue;
import simpl.parser.Symbol;
import simpl.parser.ast.IntegerLiteral;
import simpl.parser.ast.Name;
import simpl.parser.ast.Sub;

public class pred extends FunValue {

    public pred() {
        // TODO
        super(Env.empty, Symbol.symbol("pdx"),
                new Sub(new Name(Symbol.symbol("pdx")), new IntegerLiteral(1)));
    }
}
