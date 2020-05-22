package assign3.visitor ;

import java.util.Iterator;

import assign3.parser.* ;
import assign3.lexer.*;

public class ASTVisitor {

    public void visit (CompilationUnit n) {
        n.block.accept(this) ;
    }

    public void visit (BlockNode n) {
    }

    public void visit (DeclarationNode n) {
        n.type.accept(n.type.type.lexeme) ;
        n.id.accept(this) ;
    }

    public void visit (TypeNode n) {
        n.accept(n.type.lexeme);
    }


    public void visit (StatementNode n)
    {
        n.assign.accept(this);
    }

    public void visit (AssignmentNode n) {
        n.left.accept(this) ;
       // n.right.accept(this) ;
    }

    public void visit (AdditionNode n) {

        n.left.accept(this) ;
        n.right.accept(this) ;
    }

    public void visit (SubtractionNode n) {

        n.left.accept(this) ;
        n.right.accept(this) ;
    }

    public void visit (MultiplicationNode n) {

        n.left.accept(this) ;
        n.right.accept(this) ;
    }

    public void visit (DivisionNode n) {

        n.left.accept(this) ;
        n.right.accept(this) ;
    }

    public void visit (LiteralNode n) {
       n.store(n.literal);
    }

    public void visit (ValueNode n)
    {
        n.value.accept(this);
    }
}
