/**
 *  STALGCM Term 3 AY 2022 - 2023
 *  DIGNO, Kenneth Clark
 *  FETALVERO, Kenshin
 *  LIOBING, Aldwin
 *  S14
 */

/**
 * The StateAndSymbolPair class represents a combination of a State and a Symbol in the context
 * of a finite automaton. It is used to define transitions in the machine.
 */
public class StateAndSymbolPair {
    State state;
    char symbol; // Can be input character or a direction to move the head

    /**
     * Creates a new instance of StateAndSymbolPair
     */
    public StateAndSymbolPair(State state, char symbol) {
        this.state = state;
        this.symbol = symbol;
    }

    /**
     * Returns the State of this StateAndSymbolPair
     * @return the State of this StateAndSymbolPair
     */
    public State getState() {
        return state;
    }

    /**
     * Returns the symbol of this StateAndSymbolPair
     * @return the symbol of this StateAndSymbolPair
     */
    public char getSymbol() {
        return symbol;
    }


}

