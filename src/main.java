public class main {
    public static void main(String[] args) {
        View main = new View();

        // Initalize input tape and readhead
        String input = "<aaabb>"; // 'a' is a multiple of 3 and 'b' is even
        int readHead = 0;

        // Create all states
        State q0 = new State("q0", false, false);
        State q1 = new State("q1", false, false);
        State q2 = new State("q2", false, false);
        State p0 = new State("p0", false, false);
        State p1 = new State("p1", false, false);
        State t = new State("t", true, false);
        State r = new State("r", false, true);

        State[] states = {q0, q1, q2, p0, p1, t, r};
        char[] inputAlphabet = {'a', 'b'};


        // Initialize Transitions
        q0.addTransition('<', new StateAndSymbolPair(q0, 'R'));
        q0.addTransition('a', new StateAndSymbolPair(q1, 'R'));
        q0.addTransition('b', new StateAndSymbolPair(q0, 'R'));
        q0.addTransition('>', new StateAndSymbolPair(p0, 'L'));

        q1.addTransition('a', new StateAndSymbolPair(q2, 'R'));
        q1.addTransition('b', new StateAndSymbolPair(q1, 'R'));
        q1.addTransition('>', new StateAndSymbolPair(r, 'L'));

        q2.addTransition('a', new StateAndSymbolPair(q0, 'R'));
        q2.addTransition('b', new StateAndSymbolPair(q2, 'R'));
        q2.addTransition('>', new StateAndSymbolPair(r, 'L'));

        p0.addTransition('<', new StateAndSymbolPair(t, 'R'));
        p0.addTransition('a', new StateAndSymbolPair(p0, 'L'));
        p0.addTransition('b', new StateAndSymbolPair(p1, 'L'));

        p1.addTransition('<', new StateAndSymbolPair(r, 'R'));
        p1.addTransition('a', new StateAndSymbolPair(p1, 'L'));
        p1.addTransition('b', new StateAndSymbolPair(p0, 'L'));

        t.addTransition('<', new StateAndSymbolPair(t, 'R'));
        t.addTransition('a', new StateAndSymbolPair(t, 'R'));
        t.addTransition('b', new StateAndSymbolPair(t, 'R'));
        t.addTransition('>', new StateAndSymbolPair(t, 'L'));

        r.addTransition('<', new StateAndSymbolPair(r, 'R'));
        r.addTransition('a', new StateAndSymbolPair(r, 'R'));
        r.addTransition('b', new StateAndSymbolPair(r, 'R'));
        r.addTransition('>', new StateAndSymbolPair(r, 'L'));


        State currentState = q0;

        while(true) {
            if(currentState.isAcceptState()){
                System.out.println("Accepted");
                return;
            }
            else if(currentState.isRejectState()){
                System.out.println("Rejected");
                return;
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
}