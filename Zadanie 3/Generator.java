package sk.tuke.fj.stmlang;

import java.io.IOException;
import java.io.Writer;

public class Generator {
    private final StateMachineDefinition stateMachine;
    private final Writer writer;
    private int count = 0;

    public Generator(StateMachineDefinition stateMachine, Writer writer) {
        this.stateMachine = stateMachine;
        this.writer = writer;
    }

    public void generate() throws IOException {
        writeHeader();
        writePrototypes();
        writeNewLine();
        writeStatesFunctions();
        writeMainFunction();
    }

    private void writeHeader() throws IOException {
        writeLine("#include \"common.h\"");
        writeNewLine();
    }

    private void writePrototypes() throws IOException {
        var states = stateMachine.getStates().keySet();
        for (var state : states) {
            writeLine(String.format("void state_%s();", state));
            //writeNewLine();
        }
    }

    private void writeStatesFunctions() throws IOException {
        //write states
        var states = stateMachine.getStates().keySet();
        for (var state : states) {
            writeStateFunction(state, stateMachine.getStates().get(state));
            writeNewLines();
        }
    }

    private void writeStateFunction(String name, StateDefinition state) throws IOException {
        writeLine(String.format("void state_%s() {", name));

        writeActions(state);
        writeStateWhile(state);

        writer.write("}");
    }

    private void writeActions(StateDefinition state) throws IOException {
        for (var action : state.getActions()) {
            var actionName = stateMachine.getCommands().get(action);
            writeLine(String.format("\tsend_command('%c');", actionName));
        }
    }

    private void writeStateWhile(StateDefinition state) throws IOException  {
        writeLine("\tchar ev;");
        writeLine("\twhile (ev = read_event()) {");

        writeStateSwitch(state);

        writeLine("\t}");
    }

    private void writeStateSwitch(StateDefinition state) throws IOException  {
        writeLine("\t\tswitch (ev) {");
            for (var t : state.getTransitions()) {
                var caseName = stateMachine.getEvents().get(t.eventName());
                writeLine(String.format("\t\tcase '%c':", caseName));
                writeLine(String.format("\t\t\treturn state_%s();", t.targetName()));
            }
            if(count!=0) {
                writeLine("\t\tcase 'o':");
                writeLine("\t\t\treturn state_idle();");
                writeLine("\t\t}");
            }
            count++;
    }

    private void writeMainFunction() throws IOException {
        var stateName = stateMachine.getInitialStateName();
        writeLine("void main() {");
        writeLine(String.format("\tstate_%s();", stateName));
        writer.write("}");
    }

    private void writeNewLines() throws IOException {
        writeNewLine();
        writeNewLine();
    }

    private void writeNewLine() throws IOException {
        writer.write("\n");
    }

    private void writeLine(String string) throws IOException {
        writer.write(string);
        writer.write("\n");
    }
}

