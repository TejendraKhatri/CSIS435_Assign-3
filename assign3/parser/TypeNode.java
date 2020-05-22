package assign3.parser ;
import assign3.visitor.* ;
import assign3.lexer.*;

public class TypeNode extends Node {

    public Type type;

    public TypeNode () {

    }
    
    public TypeNode (Type type) {

        this.type = type;
    }

    public void accept(String v) {

        type.lexeme = v;
    }
}
