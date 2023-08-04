import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The State class represents a state in the context a two-way accepter.
 * It is used to keep track of all the states that the machine went through.
 */
public class State {
    private String name;
    private boolean isAcceptState;
    private boolean isRejectState;
    private Map<Character, StateAndSymbolPair> transitions;

    /**
     * Creates a new instance of State
     */
    public State(String name, boolean isFinalState, boolean isRejectState) {
        this.name = name;
        this.isAcceptState = isFinalState;
        this.isRejectState = isRejectState;
        this.transitions = new HashMap<>();
    }

    /**
     * Creates a new instance of State
     */
    public State(String name) {
        this.name = name;
        this.isAcceptState = false;
        this.isRejectState = false;
        this.transitions = new HashMap<>();
    }

    /**
     * Returns the name of this State
     * @return the name of this State
     */
    public String getName() {
        return name;
    }

    /**
     * Returns true if this state is an accept state, otherwise false
     * @return true if this state is an accept state, otherwise false
     */
    public boolean isAcceptState() {
        return isAcceptState;
    }

    /**
     * Returns true if this state is an reject state, otherwise false
     * @return true if this state is an reject state, otherwise false
     */
    public boolean isRejectState() {
        return isRejectState;
    }

    /**
     * Sets the isAcceptState property of this state
     * @param finalState a boolean value whether this state is an accept state
     */
    public void setAcceptState(boolean finalState) {
        isAcceptState = finalState;
    }

    /**
     * Adds a new transition the list of transitions of this state
     * @param  currInput the input for the transition to happen
     * @param next the next state when currInput is read
     */
    public void addTransition(Character currInput, StateAndSymbolPair next) {
        transitions.put(currInput, next);
    }

    /**
     * Returns the transition of this state based on the current input symbol
     * @param input the input symbol for a transition
     * @return the transition of this state based on the current input symbol
     */
    public StateAndSymbolPair getTransition(char input) {
        return transitions.get(input);
    }

    /**
     * Returns a state in a list of state based on the given name
     * @param targetName the name of the state that this function is searching for
     * @param states the list of states where the target state will be searched
     * @return the instance of the state with the name matching targetName
     */
    public static State findStateByName(String targetName, List<State> states) {
        for (State state : states) {
            if (state.getName().equals(targetName)) {
                return state;
            }
        }
        return null;
    }

    /**
     * Sets the isRejectState property of this state
     * @param rejectState a boolean value whether this state is a reject state
     */
    public void setRejectState(boolean rejectState) {
        isRejectState = rejectState;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("").append(name);
        return sb.toString();
    }
}



