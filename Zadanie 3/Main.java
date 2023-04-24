package sk.tuke.fj.stmlang;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws IOException {
        var inputPath = "src/main/resources/input.txt";
        var outputPath = "src/main/resources/output.c";

        //input
        var inputFile = new File(inputPath);
        var input = Files.readString(Path.of(inputFile.getAbsolutePath()));
        var lexer = new Lexer(new StringReader(input));
        var parser = new Parser(lexer);

        //output
        var outputFile = new File(outputPath);
        var writer = new FileWriter(outputFile.getAbsolutePath());
        var generator = new Generator(parser.stateMachine(), writer);
        generator.generate();
        writer.flush();
    }
}
