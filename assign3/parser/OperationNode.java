package assign3.parser ;
import assign3.parser.AdditionNode;
import assign3.parser.DivisionNode;
import assign3.parser.SubtractionNode;
import assign3.visitor.* ;

public class OperationNode extends Node {

    public AdditionNode add;
    public SubtractionNode sub;
    public MultiplicationNode mul;
    public DivisionNode div;

    public OperationNode () {

    }
    
    public OperationNode (AdditionNode add) {

        this.add  = add  ;
    }

    public OperationNode (SubtractionNode sub) {

        this.sub  = sub  ;
    }

    public OperationNode (MultiplicationNode mul) {

        this.mul  = mul  ;
    }

    public OperationNode (DivisionNode div) {

        this.div  = div  ;
    }

    public void accept(ASTVisitor v) {

        v.visit(this);
    }
}
