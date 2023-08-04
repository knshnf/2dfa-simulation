/**
 *  STALGCM Term 3 AY 2022 - 2023
 *  DIGNO, Kenneth Clark
 *  FETALVERO, Kenshin
 *  LIOBING, Aldwin
 *  S14
 */

/**
 * The StateAndIndexPair class represents a combination of a State and an index in the context
 * of a finite automaton. It is used to keep track of all the states that the machine went through.
 */
public class StateAndIndexPair {
    String stateName;
    int index; // 0 for left endmarker, n + 1 for right endmarker

    /**
     * Creates a new instance of StateAndIndexPair
     */
    public StateAndIndexPair(String stateName, int index) {
        this.stateName = stateName;
        this.index = index;
    }

    /**
     * Returns the StateName of this StateAndIndexPair
     * @return the StateName of this StateAndIndexPair
     */
    public String getStateName() {
        return stateName;
    }

    /**
     * Returns the index of this StateAndIndexPair
     * @return the index of this StateAndIndexPair
     */
    public int getIndex() {
        return index;
    }

    @Override
    public String toString() {
        return stateName + index;
    }
}
