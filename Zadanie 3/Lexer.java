package sk.tuke.fj.stmlang;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static sk.tuke.fj.stmlang.TokenType.*;

/**
 * Lexical analyzer of the state machine language.
 */
public class Lexer {
    private static final Map<String, TokenType> keywords = Map.of(
            "events", EVENTS,
            "resetEvents", RESET_EVENTS,
            "commands", COMMANDS,
            "state", STATE,
            "actions", ACTIONS,
            "{", LBRACE,
            "}", RBRACE,
            "->", ARROW
    );

    private final Reader input;

    private final List<String> parsedWords = new ArrayList<>();
    private int currentIndex = 0;
    private boolean isParsed = false;

    private int current;
    private String word;
    private String currentWord = "";

    public Lexer(Reader input) {
        this.input = input;
        consume();
        System.out.println(parsedWords);
    }

    public Token nextToken() {
        if (currentIndex < parsedWords.size()) {
            currentWord = parsedWords.get(currentIndex);
            currentIndex++;
        }

        if (keywords.containsKey(currentWord)) {
            return new Token(keywords.get(currentWord));
        }

        return readNameOrKeyword();
    }

    private Token readNameOrKeyword() {
        var type = NAME;
        var attribute = currentWord;

        if (currentWord.contains("'")) {
            type = CHAR;
            attribute = Character.toString(currentWord.charAt(1));
        }
        return new Token(type, attribute);
    }

    private void consume() throws StateMachineException {
        try {
            if (isParsed) return;

            consumeChar();
            word = "";
            while (current != -1) {
                // skip whitespace
                if (consumeWhitespace()) continue;

                word += (char) current;
                consumeLeftBracket();
                consumeChar();
                consumeRightBracket();

                if (current == -1) parsedWords.add(word);
            }
        } catch (Exception e) {
            throw new StateMachineException("Unknown character exception!");
        } finally {
            isParsed = true;
        }
    }

    private boolean consumeWhitespace() throws java.io.IOException {
        if (Character.isWhitespace(current)) {
            if (isEmpty(word)) consumeWord();
            consumeChar();
            return true;
        }

        return false;
    }

    private void consumeLeftBracket() {
        if (isEmpty(word) && (char) current == '{') {
            consumeWord();
        }
    }

    private void consumeRightBracket() {
        if (isEmpty(word) && (char) current == '}') {
            consumeWord();
        }
    }

    private Boolean isEmpty(String word) {
        return !word.equals("");
    }

    private void consumeChar() throws java.io.IOException {
        current = input.read();
    }

    private void consumeWord() {
        parsedWords.add(word);
        word = "";
    }
}
