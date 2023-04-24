package fj;

import java.io.BufferedReader;
import java.io.InputStreamReader;



public class Main {
    public static void main(String[] args) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            Lexer lexer = new Lexer(reader);
            int result = new Parser(lexer).statement();
            System.out.printf("\nResult %d\n", result);
        } catch (Exception e) {
            System.out.printf("\nDont work\n");
        }
    }
}