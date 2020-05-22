package assign3.parser;
import assign3.visitor.*;

public class StatementNode extends Node{

    public AssignmentNode assign;

    public StatementNode(){

    }

    public StatementNode(AssignmentNode assign) {

        this.assign = assign ;
    }

    public void accept(ASTVisitor v) {
        v.visit(this);
    }
}