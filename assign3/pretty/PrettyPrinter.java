package assign3.pretty;
import assign3.parser.*;
import assign3.visitor.* ;
import assign3.lexer.* ;
import java.io.* ;
import java.util.Iterator;

public class PrettyPrinter extends ASTVisitor{
    public Parser parser = null;
    public CompilationUnit cu = null;
    public Token lookAhead = null;
    public BufferedWriter out;

    

    public PrettyPrinter(Parser parser)
    {
        try{
            out = new BufferedWriter(new FileWriter("output.txt"));
    
   }
        catch(IOException e){ System.out.println("Error while writing to file");}
        
        this.parser = parser;
        cu = parser.cu;
        visit(cu);
    }

    public void visit (CompilationUnit n) {

        visit(n.block);
    }

    public void visit(BlockNode d){
        try {
            out.write("{\n");
            Iterator<DeclarationNode> x = d.decls.iterator();
            Iterator<StatementNode> y =  d.stmts.iterator();
            while(x.hasNext())
            {
                visit(x.next());
            }
            while(y.hasNext())
            {
                visit(y.next());
            }
            out.write("}\n");
            out.close();
        
        } catch(IOException e)
        { System.out.println("Error while writing to file");}    
        
    
    }

    public void visit(DeclarationNode d){
        try {
            out.write("\t");
            visit(d.type);
            out.write(" ");
            visit(d.id);
            out.write(";\n");
        
        } catch(IOException e)
        { System.out.println("Error while writing to file");}

        
    }

    public void visit(StatementNode d){
        visit(d.assign);
    }

    public void visit(AssignmentNode a){
        try {
            out.write("\t");
            visit(a.left);
            out.write(" = ");
            char op = a.operator;
            switch(op){
                case '+':visit(a.addright);break;
                case '-':visit(a.subright);break;
                case '*':visit(a.mulright);break;
                case '/':visit(a.divright);break;
                case ';':visit(a.valright);break;
            }
        
        } catch(IOException e)
        { System.out.println("Error while writing to file");}
        
    }

    public void visit(AdditionNode a){
        try {
            visit(a.left);
            out.write(" + ");
            visit(a.right);
            out.write(";\n");
        
        } catch(IOException e)
        { System.out.println("Error while writing to file");}
       
    }

    public void visit(SubtractionNode a){
        try {
            visit(a.left);
            out.write(" - ");
            visit(a.right);
            out.write(";\n");
        
        } catch(IOException e)
        { System.out.println("Error while writing to file");}
        
    }

    public void visit(MultiplicationNode a){
        try {
            visit(a.left);
            out.write(" * ");
            visit(a.right);
            out.write(";\n");
        
        } catch(IOException e)
        { System.out.println("Error while writing to file");}
        
    }

    public void visit(DivisionNode a){
        try {
            visit(a.left);
            out.write(" / ");
            visit(a.right);
            out.write(";\n");
        
        } catch(IOException e)
        { System.out.println("Error while writing to file");}
       
    }

    public void visit(ValueNode a){
        try {
            visit(a.value);
            out.write(";\n");
        
        } catch(IOException e)
        { System.out.println("Error while writing to file");}
       
    }

    public void visit(TypeNode x){
        try {
            out.write(x.type.lexeme);
        
        } catch(IOException e)
        { System.out.println("Error while writing to file");}
        
    }

    public void visit(LiteralNode l){
        try {
            out.write(l.literal);
        
        } catch(IOException e)
        { System.out.println("Error while writing to file");}
        
    }
}