package fj;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.*;

class zadanie2Test {

    @Test
    public void plusTest(){
        BufferedReader reader = new BufferedReader(new StringReader("2+3"));

        try {
            Lexer lexer = new Lexer(reader);
            int result = new Parser(lexer).statement();
            assertEquals(5,result);

        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void plus2Test(){
        BufferedReader reader = new BufferedReader(new StringReader("25+20"));

        try {
            Lexer lexer = new Lexer(reader);
            int result = new Parser(lexer).statement();
            assertEquals(45,result);

        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void minusTest(){
        BufferedReader reader = new BufferedReader(new StringReader("5-2"));

        try {
            Lexer lexer = new Lexer(reader);
            int result = new Parser(lexer).statement();
            assertEquals(3,result);

        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void minus2Test(){
        BufferedReader reader = new BufferedReader(new StringReader("25-20"));

        try {
            Lexer lexer = new Lexer(reader);
            int result = new Parser(lexer).statement();
            assertEquals(5,result);

        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void mulTest(){
        BufferedReader reader = new BufferedReader(new StringReader("5*10"));

        try {
            Lexer lexer = new Lexer(reader);
            int result = new Parser(lexer).statement();
            assertEquals(50,result);

        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void mul2Test(){
        BufferedReader reader = new BufferedReader(new StringReader("25*20"));

        try {
            Lexer lexer = new Lexer(reader);
            int result = new Parser(lexer).statement();
            assertEquals(500,result);

        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void divTest(){
        BufferedReader reader = new BufferedReader(new StringReader("10/2"));

        try {
            Lexer lexer = new Lexer(reader);
            int result = new Parser(lexer).statement();
            assertEquals(5,result);

        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void div2Test(){
        BufferedReader reader = new BufferedReader(new StringReader("100/25"));

        try {
            Lexer lexer = new Lexer(reader);
            int result = new Parser(lexer).statement();
            assertEquals(4,result);

        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void barTest(){
        BufferedReader reader = new BufferedReader(new StringReader("2+(5)-2-(3)"));

        try {
            Lexer lexer = new Lexer(reader);
            int result = new Parser(lexer).statement();
            assertEquals(2,result);

        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void bar2Test(){
        BufferedReader reader = new BufferedReader(new StringReader("20+(50)-20-(30)"));

        try {
            Lexer lexer = new Lexer(reader);
            int result = new Parser(lexer).statement();
            assertEquals(20,result);

        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void randomTest(){
        BufferedReader reader = new BufferedReader(new StringReader("2*5+(1+4)-(3*7)"));

        try {
            Lexer lexer = new Lexer(reader);
            int result = new Parser(lexer).statement();
            assertEquals(-6,result);

        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void random2Test(){
        BufferedReader reader = new BufferedReader(new StringReader("10*90+(20-10)-(30*70)"));

        try {
            Lexer lexer = new Lexer(reader);
            int result = new Parser(lexer).statement();
            assertEquals(-1190,result);

        } catch (Exception e) {
            fail();
        }
    }
}