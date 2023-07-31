# stalgcm-model

Text file format for machine definition:


## Number of States
7                  
## Name of States                     
q0 q1 q2 p0 p1 t r
## Number of Input Symbols
2
## Name of Input Symbols
a b
## Number of Transitions
24
## Transitions
q0 < q0 R
q0 a q1 R
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
## Set Start State
q0
## Set Accepting State
t
## Set Rejecting State
r