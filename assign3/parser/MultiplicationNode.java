package assign3.parser ;
import assign3.visitor.* ;

public class MultiplicationNode extends Node {

    public LiteralNode left  ;
    public LiteralNode right ;

    public MultiplicationNode () {

    }
    
    public MultiplicationNode (LiteralNode left, LiteralNode right) {

        this.left  = left  ;
        this.right = right ;
    }

    public void accept(ASTVisitor v) {

        v.visit(this);
    }
}
