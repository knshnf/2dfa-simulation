import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class State {
    private String name;
    private boolean isAcceptState;
    private boolean isRejectState;
    private Map<Character, StateAndSymbolPair> transitions;

    public State(String name, boolean isFinalState, boolean isRejectState) {
        this.name = name;
        this.isAcceptState = isFinalState;
        this.isRejectState = isRejectState;
        this.transitions = new HashMap<>();
    }

    public State(String name) {
        this.name = name;
        this.isAcceptState = false;
        this.isRejectState = false;
        this.transitions = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAcceptState() {
        return isAcceptState;
    }

    public boolean isRejectState() {
        return isRejectState;
    }


    public void setAcceptState(boolean finalState) {
        isAcceptState = finalState;
    }

    public void addTransition(Character currInput, StateAndSymbolPair next) {
        transitions.put(currInput, next);
    }

    public StateAndSymbolPair getTransition(char input) {
        return transitions.get(input);
    }

    public static State findStateByName(String targetName, List<State> states) {
        for (State state : states) {
            if (state.getName().equals(targetName)) {
                return state;
            }
        }
        return null;
    }

    public void setRejectState(boolean rejectState) {
        isRejectState = rejectState;
    }

    // This will only be used for debugging
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("").append(name);
        return sb.toString();
    }
}



