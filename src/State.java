import java.util.HashMap;
import java.util.Map;


public class State {
    private String name;
    private boolean isFinalState;
    private boolean isRejectState;
    private Map<Character, StateAndSymbolPair> transitions;

    public State(String name, boolean isFinalState, boolean isRejectState) {
        this.name = name;
        this.isFinalState = isFinalState;
        this.isRejectState = isRejectState;
        this.transitions = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isFinalState() {
        return isFinalState;
    }

    public boolean isRejectState() {
        return isRejectState;
    }


    public void setFinalState(boolean finalState) {
        isFinalState = finalState;
    }

    public void addTransition(Character currInput, StateAndSymbolPair next) {
        transitions.put(currInput, next);
    }

    public StateAndSymbolPair getTransition(char input) {
        return transitions.get(input);
    }
}


