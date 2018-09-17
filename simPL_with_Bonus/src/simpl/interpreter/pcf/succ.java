package simpl.interpreter.pcf;

import simpl.interpreter.Env;
import simpl.interpreter.FunValue;
import simpl.parser.Symbol;
import simpl.parser.ast.Add;
import simpl.parser.ast.IntegerLiteral;
import simpl.parser.ast.Name;

public class succ extends FunValue {

    public succ() {
        // TODO
        super(Env.empty, Symbol.symbol("sucx"),
                new Add(new Name(Symbol.symbol("sucx")), new IntegerLiteral(1)));
    }
}
