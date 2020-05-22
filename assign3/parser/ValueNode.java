package assign3.parser ;

import assign3.visitor.* ;

public class ValueNode extends Node {

    public LiteralNode value  ;

    public ValueNode () {

    }
    
    public ValueNode (LiteralNode value) {

        this.value  = value  ;
    }

    public void accept(ASTVisitor v) {

        v.visit(this);
    }
}
