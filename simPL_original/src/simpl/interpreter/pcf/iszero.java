package simpl.interpreter.pcf;

import simpl.interpreter.Env;
import simpl.interpreter.FunValue;
import simpl.parser.Symbol;
import simpl.parser.ast.BooleanLiteral;
import simpl.parser.ast.Cond;
import simpl.parser.ast.Eq;
import simpl.parser.ast.IntegerLiteral;
import simpl.parser.ast.Name;

public class iszero extends FunValue {

    public iszero() {
        // TODO
        super(Env.empty, Symbol.symbol("izx"),
                new Cond(new Eq(new Name(Symbol.symbol("izx")), new IntegerLiteral(0)),
                new BooleanLiteral(true),new BooleanLiteral(false)));
        //this.e = fe;
    }
}
