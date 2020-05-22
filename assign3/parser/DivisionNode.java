package assign3.parser ;
import assign3.visitor.* ;

public class DivisionNode extends Node {

    public LiteralNode left  ;
    public LiteralNode right ;

    public DivisionNode () {

    }
    
    public DivisionNode (LiteralNode left, LiteralNode right) {

        this.left  = left  ;
        this.right = right ;
    }

    public void accept(ASTVisitor v) {

        v.visit(this);
    }
}
