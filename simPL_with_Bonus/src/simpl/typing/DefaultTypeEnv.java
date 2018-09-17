package simpl.typing;

import simpl.parser.Symbol;
import simpl.parser.ast.Pair;

public class DefaultTypeEnv extends TypeEnv {

    private TypeEnv E;

    public DefaultTypeEnv() {
        // TODO
        E = empty;
        TypeVar t1 = new TypeVar(true), t2 = new TypeVar(true);
        E = TypeEnv.of(E,Symbol.symbol("fst"), new ArrowType(new PairType(t1,t2),t1));
        TypeVar t3 = new TypeVar(true), t4 = new TypeVar(true);
        E = TypeEnv.of(E,Symbol.symbol("snd"), new ArrowType(new PairType(t3,t4),t4));
        TypeVar t5 = new TypeVar(true);
        E = TypeEnv.of(E,Symbol.symbol("hd"), new ArrowType(new ListType(t5),t5));
        TypeVar t6 = new TypeVar(true);
        E = TypeEnv.of(E,Symbol.symbol("tl"), new ArrowType(new ListType(t6),new ListType(t6)));
        E = TypeEnv.of(E,Symbol.symbol("iszero"), new ArrowType(Type.INT,Type.BOOL));
        E = TypeEnv.of(E,Symbol.symbol("pred"), new ArrowType(Type.INT,Type.INT));
        E = TypeEnv.of(E,Symbol.symbol("succ"), new ArrowType(Type.INT,Type.INT));
    }

    @Override
    public Type get(Symbol x) {
        return E.get(x);
    }
}
