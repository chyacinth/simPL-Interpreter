package simpl.interpreter;

import simpl.interpreter.lib.hd;
import simpl.interpreter.lib.tl;
import simpl.interpreter.lib.fst;
import simpl.interpreter.lib.snd;
import simpl.interpreter.pcf.iszero;
import simpl.interpreter.pcf.pred;
import simpl.interpreter.pcf.succ;
import simpl.parser.Symbol;

public class InitialState extends State {

    public InitialState() {
        super(initialEnv(Env.empty), new Mem(), new Int(0));
    }

    private static Env initialEnv(Env E) {
        // TODO
        E = new Env(E,Symbol.symbol("fst"),new fst());
        E = new Env(E,Symbol.symbol("snd"),new snd());
        E = new Env(E,Symbol.symbol("hd"),new hd());
        E = new Env(E,Symbol.symbol("tl"),new tl());
        E = new Env(E,Symbol.symbol("iszero"),new iszero());
        E = new Env(E,Symbol.symbol("pred"),new pred());
        E = new Env(E,Symbol.symbol("succ"),new succ());
        return E;
        //return null;
    }
}
