package sk.tuke.fj.stmlang;

import java.util.Set;

import static sk.tuke.fj.stmlang.TokenType.*;

/**
 * Parser for the state machine language
 * <p>
 * StateMachine -> { Statement }
 * Statement    -> Events | ResetEvents | Commands | State
 * Events       -> "events" "{" { NAME CHAR } "}"
 * Commands     -> "commands" "{" { NAME CHAR } "}"
 * ResetEvents  -> "resetEvents" "{" { NAME } "}"
 * State        -> "state" "{" [Actions] { Transition } "}"
 * Actions      -> "actions" "{" { NAME } "}"
 * Transition   -> NAME "->" NAME
 */
public class Parser {
    private final Lexer lexer;
    private Token symbol;
    private StateMachineDefinition definition;

    public Parser(Lexer lexer) {
        this.lexer = lexer;
    }

    public StateMachineDefinition stateMachine() {
        definition = new StateMachineDefinition();
        var first = Set.of(EVENTS, COMMANDS, RESET_EVENTS, STATE);
        consume();

        while (first.contains(symbol.type())) {
            switch (symbol.type()) {
                case EVENTS -> events();
                case COMMANDS -> commands();
                case RESET_EVENTS -> resetEvents();
                case STATE -> state();
            }
        }
        return definition;
    }

    // here is an example how to implement events
    private void events() {
        match(EVENTS);
        match(LBRACE);
        while (symbol.type() == NAME) {
            var name = symbol.attribute();
            match(NAME);

            var value = symbol.attribute();
            match(CHAR);

            definition.addEvent(name, value.charAt(0));
        }
        match(RBRACE);
    }

    private void commands() {
        match(COMMANDS);
        match(LBRACE);
        while (symbol.type() == NAME) {
            var name = symbol.attribute();
            match(NAME);

            var value = symbol.attribute();
            match(CHAR);

            definition.addCommand(name, value.charAt(0));
        }
        match(RBRACE);
    }

    private void resetEvents() {
        match(RESET_EVENTS);
        match(LBRACE);
        while (symbol.type() == NAME) {
            var name = symbol.attribute();
            match(NAME);

            definition.addResetEvent(name);
        }
        match(RBRACE);
    }

    private void state() {
        match(STATE);

        var stateName = symbol.attribute();
        match(NAME);

        match(LBRACE);

        var stateDefinition = new StateDefinition();
        actions(stateDefinition);
        transitions(stateDefinition);

        definition.addState(stateName, stateDefinition);

        match(RBRACE);
    }

    private void actions(StateDefinition stateDefinition) {
        if (symbol.type() == ACTIONS) {
            match(ACTIONS);
            match(LBRACE);
            while (symbol.type() == NAME) {
                var actionName = symbol.attribute();
                stateDefinition.addAction(actionName);
                match(NAME);
            }
            match(RBRACE);
        }
    }

    private void transitions(StateDefinition stateDefinition) {
        while (symbol.type() == NAME) {
            stateDefinition.addTransition(transition());
        }
    }

    private TransitionDefinition transition() {
        var eventName = symbol.attribute();
        match(NAME);

        match(ARROW);

        var targetName = symbol.attribute();
        match(NAME);

        return new TransitionDefinition(eventName, targetName);
    }

    private void match(TokenType expectedSymbol) throws StateMachineException {
        if (symbol == null || symbol.type() != expectedSymbol) {
            throw new StateMachineException("Non valid input");
        }
        consume();
    }

    private void consume() {
        this.symbol = this.lexer.nextToken();
    }
}
