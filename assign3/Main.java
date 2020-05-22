package assign3 ;

import java.io.IOException;

import assign3.lexer.* ;
import assign3.parser.* ;
import assign3.pretty.*;
    
public class Main {

    public static void main (String[] args) {
        Lexer lexer = new Lexer() ;
        Parser parser = new Parser(lexer) ;
        PrettyPrinter pretty = new PrettyPrinter(parser);
    }
}
