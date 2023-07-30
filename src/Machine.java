import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class Machine {
    List<State> states;
    List<Character> inputAlphabet;
    State startState;
    State acceptState;
    State rejectState;
    int readHead;


    public Machine(File file) throws CustomException{
        // Initialize Reader
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));

            // Read number of states
            int numStates = Integer.parseInt(reader.readLine().trim());

            // Read each state
            String[] stateNames = reader.readLine().trim().split(" ");

            if (numStates <= 0 || (numStates != stateNames.length)) {
                throw new CustomException("Invalid number of states");
            }

            this.states = new ArrayList<>();
            for (int i = 0; i < numStates; i++) {
                this.states.add(new State(stateNames[i]));
            }

            // Read number of inputs
            int numInputs = Integer.parseInt(reader.readLine().trim());

            // Read each input
            String[] inputSymbols = reader.readLine().trim().split(" ");
            this.inputAlphabet = new ArrayList<>();

            for (int i = 0; i < inputSymbols.length; i++) {
                if (inputSymbols[i].length() > 1 || (inputSymbols.length != numInputs)) {
                    throw new CustomException("Invalid input symbols");
                }
                this.inputAlphabet.add(inputSymbols[i].charAt(0));
            }

            // Read number of transitions
            int numTransitions = Integer.parseInt(reader.readLine().trim());

            // Add each transition to corresponding state
            for(int i = 0; i < numTransitions; i++) {
                String[] transition = reader.readLine().trim().split(" ");
                if(transition.length != 4){
                    throw new CustomException("Invalid transitions");
                }
                State currState = State.findStateByName(transition[0], this.states);
                State nextState = State.findStateByName(transition[2], this.states);
                if(currState != null && nextState != null && (this.inputAlphabet.contains(transition[1].charAt(0)) || transition[1].charAt(0) == '<' || transition[1].charAt(0) == '>') && (transition[3].charAt(0) == 'R'
                        || transition[3].charAt(0) == 'L')) {
                    currState.addTransition(transition[1].charAt(0), new StateAndSymbolPair(nextState, transition[3].charAt(0)));
                }
                else {
                    throw new CustomException("Invalid transitions");
                }
            }

            // Read start state
            State startState = State.findStateByName(reader.readLine(), this.states);
            if(startState == null) {
                throw new CustomException("Invalid start state");
            }
            this.startState = startState;

            // Read accept state
            State acceptState = State.findStateByName(reader.readLine(), this.states);
            if(acceptState == null) {
                throw new CustomException("Invalid accept state");
            }
            acceptState.setAcceptState(true);
            this.acceptState = acceptState;

            // Read reject state
            State rejectState = State.findStateByName(reader.readLine(), this.states);
            if(rejectState == null) {
                throw new CustomException("Invalid reject state");
            }
            rejectState.setRejectState(true);
            this.rejectState = rejectState;

//            System.out.println("Start state: \n" + startState.getName());
//            System.out.println("Accept state: \n" + acceptState.getName());
//            System.out.println("Reject state: \n" + rejectState.getName());

        } catch (Exception e) {
            throw new CustomException("Invalid file: " + e);
        }
    }

    public boolean fastRun(String input){
        StringBuilder sb = new StringBuilder();
        sb.append("<");
        sb.append(input);
        sb.append(">");

        input = sb.toString();

        State currentState = this.startState;
        readHead = 0;

        while(true) {
            if(currentState.isAcceptState()){
                System.out.println("Accepted");
                return true;
            }
            else if(currentState.isRejectState()){
                System.out.println("Rejected");
                return false;
            }
            else{
//                System.out.println("Current State: " + currentState.getName());
//                System.out.println("Current Input: " + input.charAt(readHead));
//                System.out.println();

                StateAndSymbolPair transition = currentState.getTransition(input.charAt(readHead));
                currentState = transition.getState();

                if(transition.getSymbol() == 'R'){
                    readHead++;
                }
                else if(transition.getSymbol() == 'L'){
                    readHead--;
                }
            }
        }
    }

    public List<StateAndIndexPair> step(String input){
        List<StateAndIndexPair> list = new ArrayList<StateAndIndexPair>();

        StringBuilder sb = new StringBuilder();
        sb.append("<");
        sb.append(input);
        sb.append(">");

        input = sb.toString();

        State currentState = this.startState;
        readHead = 0;

        while(true) {
            list.add(new StateAndIndexPair(currentState.getName(), readHead));

            if(currentState.isAcceptState()){
                System.out.println("Accepted");
                return list;
            }
            else if(currentState.isRejectState()){
                System.out.println("Rejected");
                return list;
            }
            else{
//                System.out.println("Current State: " + currentState.getName());
//                System.out.println("Current Input: " + input.charAt(readHead));
//                System.out.println();

                StateAndSymbolPair transition = currentState.getTransition(input.charAt(readHead));
                currentState = transition.getState();

                if(transition.getSymbol() == 'R'){
                    readHead++;
                }
                else if(transition.getSymbol() == 'L'){
                    readHead--;
                }
            }
        }
    }

    // This will only be used for debugging
    public String toString(){
            StringBuilder sb = new StringBuilder();
            sb.append("Q = ").append(states).append("\n");
            sb.append("Σ = ").append(inputAlphabet).append("\n");
            sb.append("Start State: ").append(startState).append("\n");
            sb.append("Accept State: ").append(acceptState).append("\n");
            sb.append("Reject State: ").append(rejectState).append("\n");
            return sb.toString();
    }
}
