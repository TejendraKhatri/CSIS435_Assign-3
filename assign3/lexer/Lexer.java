package assign3.lexer;

import java.io.* ;
import java.util.* ;


public class Lexer {

    public int line = 1 ;
    private char peek = ' ' ;
    private Hashtable<String, Word> words = new Hashtable<String, Word>() ;
    private FileInputStream fileInputStream;
   // private Hashtable words = new Hashtable() ;

    public Lexer (){
        initFile();
        reserve(new Word("true",  Tag.TRUE)) ;
        reserve(new Word("false", Tag.FALSE)) ;

        reserve( Type.Int  );  reserve( Type.Char  );
        reserve( Type.Bool );  reserve( Type.Float );

    }
    void initFile() 
    {
        try {
            File f=new File("input.txt");     //Creation of File Descriptor for input file
            fileInputStream = new FileInputStream(f);
        } catch (FileNotFoundException e) {
            System.out.println("Found Not Found");
        }
    }

    void reserve (Word w) {
        words.put(w.lexeme, w) ;
    }

    void readch() throws IOException { 
       // peek = (char)System.in.read(); 
      peek = (char) fileInputStream.read();
    }

    public Token scan() throws IOException {

       // System.out.println("scan() in Lexer") ;

        for ( ; ; readch()) {

            if (peek == ' ' || peek == '\t') 
                continue ;
            else if (peek == '\n') 
                {line = line + 1 ;}
            else 
                break ;
        }

        if (Character.isDigit(peek)) {

            int v = 0 ;

            do {

                v = 10 * v + Character.digit(peek, 10) ;
                readch() ;

            } while (Character.isDigit(peek)) ;

        // System.out.println("v: " + v) ;

            return new Num(v) ;
        }

        if (Character.isLetter(peek)) {

            StringBuffer b = new StringBuffer() ;

            do {

                b.append(peek) ;
                readch();

            } while (Character.isLetterOrDigit(peek)) ;

            String s = b.toString() ;
           // System.out.println("s: " + s) ;
            Word w = (Word) words.get(s) ;

             if (w != null)
                 return w ;
            
            w = new Word(s, Tag.ID) ;
            words.put(s, w) ;

           // System.out.println("w: " + w.toString()) ;

            return w ;
        }
        Token t = new Token(peek) ; 
        //System.out.println("t: " + t.toString()) ;
        peek = ' ' ;

        return t ;
    }
}
