package assign3.parser ;
import java.util.*;

import assign3.parser.DeclarationNode;
import assign3.visitor.* ;

public class BlockNode extends Node {

    public Vector<DeclarationNode> decls = new Vector<DeclarationNode>();
    public Vector<StatementNode> stmts = new Vector<StatementNode>();

    public BlockNode () {

    }

    public void accept(ASTVisitor v) {

        v.visit(this);
    }
}
