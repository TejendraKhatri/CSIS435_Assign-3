package assign3.parser ;

import assign3.visitor.* ;

public class AssignmentNode extends Node {

    public LiteralNode  left  ;
    public AdditionNode addright ;
    public SubtractionNode subright;
    public MultiplicationNode mulright;
    public DivisionNode divright;
    public ValueNode valright;
    public char operator;

    public AssignmentNode () {
        
    }

    public void accept(ASTVisitor v) {

        v.visit(this);
    }
}
