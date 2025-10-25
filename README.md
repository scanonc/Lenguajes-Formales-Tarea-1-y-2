Assignment 1 - DFA Minimization

Author: Sebastian Cañon Cuartas
Class Number: Si2002-5730

------------------------------------------------------------
Environment and Tools
------------------------------------------------------------
Operating System: Windows 10
Programming Language: Java 17
Compiler/Tool: javac and java (from JDK 17)
Editor: Visual Studio Code

------------------------------------------------------------
How to Compile and Run
------------------------------------------------------------
1. Open a terminal in the folder where Main.java is located.
2. Compile the program:
   javac Main.java
3. Run the program:
   java Main
4. Provide the input following the exact format described in the assignment.

Example Input:
1
6
a b
1 2 5
0 1 2
1 3 4
2 4 3
3 5 5
4 5 5
5 5 5

Expected Output:
(1, 2) (3, 4)

------------------------------------------------------------
Algorithm Explanation
------------------------------------------------------------
This program implements the DFA minimization algorithm described in Kozen (1997), Lecture 14.

The algorithm identifies equivalent states in a deterministic finite automaton (DFA)
through iterative partition refinement.

1. Initial Partition:
   - Final states (F)
   - Non-final states (Q − F)

2. Refinement:
   For each group, states are compared based on their transitions.
   States that transition to different groups under the same input symbol
   are separated into new subgroups.

3. Stabilization:
   The process repeats until no further refinement occurs (the partition is stable).

4. Equivalence Detection:
   States that remain in the same group after stabilization are equivalent.
   The program prints all pairs (p, q) of equivalent states in lexicographic order.

Output format:
- One line per case.
- Pairs are shown as (p, q) separated by spaces.
- No additional text or lines are printed.

------------------------------------------------------------
Reference
------------------------------------------------------------
Kozen, Dexter C. (1997). Automata and Computability.
Berlin, Heidelberg: Springer-Verlag.
ISBN: 0387949070.
DOI: https://doi.org/10.1007/978-1-4612-1844-9
