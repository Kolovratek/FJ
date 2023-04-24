package sk.tuke.fj.stmlang;

public enum TokenType {
    // Keywords
    EVENTS, RESET_EVENTS, COMMANDS, STATE, ACTIONS,
    // Symbols
    LBRACE, RBRACE, ARROW,
    // Attributes
    NAME, CHAR,
    // Other
    WHITESPACE, EOF, NEWLINE
}
