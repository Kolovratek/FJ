package fj;

import javax.naming.LimitExceededException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

public class Lexer {
    private int current;
    private int value;
    private final Reader input;
    boolean First = true;
    boolean Plus = true;
    int bar = 0;
    Token lastToken = Token.EOF;

    public Lexer(BufferedReader input) {
        this.input = input;
        consume();
    }

    public int getValue() {
        return this.value;
    }

    private void consume() {
        try {
            this.current = input.read();
        } catch (IOException e) {
            System.out.println("Error");
        }
    }

    public Token nextToken() {
        switch (current) {
            case ' ' -> {
                consume();
                return nextToken();
            }
            case '+' -> {
                if(Plus == true && lastToken != Token.MINUS) {
                    consume();
                    Plus = false;
                    lastToken = Token.PLUS;
                    return Token.PLUS;
                }else{
                    throw new CalculatorException("Bad Input");
                }
            }
            case '-' -> {
                consume();
                lastToken = Token.MINUS;
                return Token.MINUS;
            }
            case '/' -> {
                consume();
                lastToken = Token.DIV;
                return Token.DIV;
            }
            case '*' -> {
                consume();
                lastToken = Token.MUL;
                return Token.MUL;
            }
            case '(' -> {
                bar++;
                consume();
                return Token.LPAR;
            }
            case ')' -> {
                consume();
                bar--;
                return Token.RPAR;
            }
            case '\n' -> {
                if(bar != 0 || lastToken == Token.PLUS || lastToken == Token.MINUS || lastToken == Token.DIV || lastToken == Token.MUL || lastToken == Token.EOF){
                    throw new CalculatorException("Bad Input");
                }
                return Token.EOF;
            }
        }

        Plus = true;
        while ((current - 48) <= 9 && (current - 48) >= 0) {
            if (First) {
                value = (current - 48);
                First = false;
                consume();
            } else {
                value *= 10;
                value += (current - 48);
                consume();
            }
        }
        First = true;
        lastToken = Token.NUMBER;
        return Token.NUMBER;


    }
}
