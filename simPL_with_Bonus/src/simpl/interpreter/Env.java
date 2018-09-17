package simpl.interpreter;

import simpl.parser.Symbol;
import simpl.parser.ast.Ref;


public class Env {

    private final Env E;
    private final Symbol x;
    private final Value v;

    private Env() {
        E = null;
        x = null;
        v = null;
    }

    public static Env empty = new Env() {
        public Value get(Symbol y) {
            return null;
        }

        public Env clone() {
            return this;
        }
        public void mark(Mem M)
        {
            return;
        }
    };

    public Env(Env E, Symbol x, Value v) {
        this.E = E;
        this.x = x;
        this.v = v;
    }

    public Value get(Symbol y) {
        // TODO
        if (y.toString().equals(x.toString())) {
            return v;
        }
        else return E.get(y);
        //return null;
    }

    public void mark(Mem M)
    {
        v.mark(M);
        E.mark(M);
    }

    public Env clone() {
        // TODO
        return new Env(E,x,v);
    }

}
/*
public class Env {
    public final Vector<Value> ValList;
    public final Vector<Symbol> SymList;
    public final int p;
    private Env() {
        ValList = new Vector<>();
        SymList = new Vector<>();
        p = 0;
    }
    public static Env empty = new Env();

    public Env(Env E, Symbol x, Value v) {
        this.ValList = (Vector<Value>) E.ValList.clone();
        this.SymList = (Vector<Symbol>) E.SymList.clone();
        //this.SymList = E.SymList;
        this.p = E.p + 1;
        ValList.add(p - 1,v);
        SymList.add(p - 1,x);
    }
    public Value get(Symbol y) {
        for (int i = p - 1; i >= 0; i--)
        {
            if (SymList.get(i).toString().equals(y.toString()))
            {
                return ValList.get(i);
            }
        }
        return null;
    }
}*/