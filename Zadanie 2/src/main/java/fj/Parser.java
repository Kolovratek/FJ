package fj;

public class Parser {
    private Token symbol;
    private final Lexer lexer;


    public Parser(Lexer lexer) {
        this.lexer = lexer;
    }

    public int statement(){
        consume();
        return expr();
    }

    private int expr(){
        int value = mul();

        while (symbol == Token.PLUS || symbol == Token.MINUS) {
            Token oldToken = symbol;
            consume();
            switch (oldToken) {
                case PLUS -> value += mul();
                case MINUS -> value -= mul();
            }
        }

        return value;
    }
    private int mul(){
        int value = term();
        while (symbol == Token.MUL || symbol == Token.DIV) {
            Token oldToken = symbol;
            consume();
            switch (oldToken) {
                case MUL -> value *= term();
                case DIV -> value /= term();

            }
        }
        return value;
    }
    private int term(){
        if(symbol == Token.MINUS){
            consume();
            return -S();
        }

        return S();
    }

    private int S() {
        int value = lexer.getValue();
        Token oldToken = symbol;
        if (symbol == Token.LPAR)
            value = statement();
        if (oldToken == Token.RPAR)
            return value;
        consume();

        return value;
    }

    private void consume(){
        this.symbol = lexer.nextToken();
    }
}