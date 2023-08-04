The src folder contains the source code for the implementation of a 
two-way accepter. 

The machine-sample-1 file is a machine definition file for a machine that accepts
input that accepts the language
	L = {x ∈ {a, b}∗ | #a(x) is a multiple of 3 and #b(x) is even}.

The machine-sample-2 file is a machine definition file for a machine that accepts
an input the third letter from the back is ’a’.

An example of a machine definition file is the following:

7 /* number of states */                                
q0 q1 q2 p0 p1 t r /* name of states */  
2 /* number of input symbols */               
a b /* list of input symbols */     
24 /* number of transitions */            
q0 < q0 R /* transitions in the format q s q' direction */
q0 a q1 R /* such that f(q,s) = (q’, direciton) */
q0 b q0 R
q0 > p0 L
q1 a q2 R
q1 b q1 R
q1 > r L
q2 a q0 R
q2 b q2 R
q2 > r L
p0 < t R
p0 a p0 L
p0 b p1 L
p1 < r R
p1 a p1 L
p1 b p0 L
t < t R
t a t R
t b t R
t > t L
r < r R
r a r R
r b r R
r > r L
q0 /* start state */            
t /* accept state */    
r /* reject state */    