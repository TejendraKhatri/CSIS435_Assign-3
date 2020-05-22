package assign3.parser ;

import assign3.visitor.* ;
import assign3.lexer.* ;

import java.io.* ;

public class Parser extends ASTVisitor {

    public CompilationUnit cu = null ;
    public Lexer lexer        = null ;    
    public Token lookAhead    = null;
    private LiteralNode x;
       

    public Parser (Lexer lexer) { 

        this.lexer = lexer ;
        readLexem();
        cu = new CompilationUnit() ;
        visit(cu) ;
    }

    void error(String s)
    {
        throw new Error("near line  "+lexer.line+": "+ s);
    }

    void match(int t)
    {
        if(lookAhead.tag==t)
        {
            readLexem();
        }
        else
            error("syntax error");
    }

    void readLexem () {
        try {
            
            lookAhead = lexer.scan() ;
             if((lookAhead.tag == 13))
            {
                readLexem();
            } 
          //  else  System.out.println(lookAhead.toString());
        }
        catch (IOException e) {

            System.out.println("IO Error") ;
        }
    }
    
    // public Parser () {

    //     cu = new CompilationUnit() ;
    //     readLexem();
    //     visit(cu) ;
    // }

    public void visit (CompilationUnit n) {
        
        n.block = new BlockNode() ;
        n.block.accept(this) ;
    }

    public void visit(BlockNode n){
       match('{');
       while(lookAhead.tag == Tag.BASIC){
           DeclarationNode decl = new DeclarationNode();
           decl.accept(this);
           n.decls.add(decl);
       }
       while(lookAhead.tag == Tag.ID){
           StatementNode st = new StatementNode();
           st.accept(this);
           n.stmts.add(st);
       }
       match('}');
    }

    public void visit(DeclarationNode d){
        d.type = new TypeNode((Type) lookAhead);
        match(Tag.BASIC);
        d.type.accept(d.type.type.lexeme);
        d.id = new LiteralNode();
        x = new LiteralNode(lookAhead.toString());
        match(Tag.ID);
        d.id.store(x.literal);
        match(';');
    }

    public void visit(StatementNode s){
        s.assign = new AssignmentNode();
        s.assign.accept(this);
    }



    public boolean isNumeric(String s) {
		if (s == null || s.equals("")) {
			return false;
		}

		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c < '0' || c > '9') {
				return false;
			}
		}
		return true;
    }
    
    public void visit(AssignmentNode a){
        a.left = new LiteralNode();
        x = new LiteralNode(lookAhead.toString());
        match(Tag.ID);
        a.left.store(x.literal);
        Token t = new Token('=');
        match(t.tag);

        x = new LiteralNode(lookAhead.toString());
        if(isNumeric(lookAhead.toString())){
            match(Tag.NUM);
        }
        else match(Tag.ID);
        
        Token add = new Token('+');
        Token sub = new Token('-');
        Token mul = new Token('*');
        Token div = new Token('/');
        Token end = new Token(';');

        char op = lookAhead.toString().charAt(0);
        if(op == '+')
        {
            match(add.tag);
            a.addright = new AdditionNode();
            a.addright.accept(this);
            a.operator = '+';
        }
        else if(op == '-')
        {
            match(sub.tag);
            a.subright = new SubtractionNode();
            a.subright.accept(this);
            a.operator = '-';
        }
        else if(op == '*')
        {
            match(mul.tag);
            a.mulright = new MultiplicationNode();
            a.mulright.accept(this);
            a.operator = '*';
        }
        else if(op == '/')
        {
            match(div.tag);
            a.divright = new DivisionNode();
            a.divright.accept(this);
            a.operator = '/';
        }
        else if(op == ';')
        {
            match(';');
            a.operator = ';';
            a.valright = new ValueNode();
            a.valright.accept(this);
        }
    }

    public void visit(ValueNode n) {
        n.value = new LiteralNode() ;
       // x= new LiteralNode(lookAhead.toString());
        n.value.store(x.literal);
    }
    
    public void visit (AdditionNode n) {
        n.left = new LiteralNode() ;
        n.left.store(x.literal);
        
        n.right = new LiteralNode();
        x = new LiteralNode(lookAhead.toString());
        if(isNumeric(lookAhead.toString())){
            match(Tag.NUM);
        }
        else match(Tag.ID);
        n.right.store(x.literal) ;
        match(';');
    }

    public void visit (SubtractionNode n) {
        n.left = new LiteralNode() ;
        n.left.store(x.literal);
        
        n.right = new LiteralNode();
        x = new LiteralNode(lookAhead.toString());
        if(isNumeric(lookAhead.toString())){
            match(Tag.NUM);
        }
        else match(Tag.ID);
        n.right.store(x.literal) ;
        match(';');
    }

    public void visit (MultiplicationNode n) {
        n.left = new LiteralNode() ;
        n.left.store(x.literal);
        
        n.right = new LiteralNode();
        x = new LiteralNode(lookAhead.toString());
        if(isNumeric(lookAhead.toString())){
            match(Tag.NUM);
        }
        else match(Tag.ID);
        n.right.store(x.literal) ;
        match(';');
    }

    public void visit (DivisionNode n) {
        n.left = new LiteralNode() ;
        n.left.store(x.literal);
        
        n.right = new LiteralNode();
        x = new LiteralNode(lookAhead.toString());
        if(isNumeric(lookAhead.toString())){
            match(Tag.NUM);
        }
        else match(Tag.ID);
        n.right.store(x.literal) ;
        match(';');
    }

    public void visit (LiteralNode n) {
        // What should visit(LiteralNode) do? 
        // One part of the next assignment.
        n.accept(this);
    }

  
}