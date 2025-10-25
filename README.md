

Author: Sebastian Cañon Cuartas   
Class Number: Si2002-5730

Assignment 1 - DFA Minimization
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
------------------------------------------------------------
------------------------------------------------------------


Assignment 2 - left recursion elimination
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
1   
S -> Sa b   

Expected Output:   
S -> bZ   
Z -> aZ e   

------------------------------------------------------------
Algorithm Explanation
------------------------------------------------------------
This program implements the left recursion elimination algorithm described in 
Aho et al. (2006), Section 4.3.3.

The algorithm transforms a context-free grammar with left recursion into an 
equivalent grammar without left recursion, making it suitable for top-down parsing.

1. Input Assumptions:
   - Initial symbol is S
   - Nonterminals: uppercase letters
   - Terminals: lowercase letters  
   - e represents epsilon (ε)
   - No cycles: A →⁺ A
   - No ε-productions: A → ε

2. Algorithm Steps:
   a) Order nonterminals: A₁, A₂, ..., Aₙ
   b) For each nonterminal Aᵢ (from first to last):
      - Eliminate indirect left recursion by substituting productions
        Aᵢ → Aⱼα with Aᵢ → δα for all δ in Aⱼ's productions (where j < i)
      - Eliminate direct left recursion:
        If Aᵢ → Aᵢα₁ | ... | Aᵢαₘ | β₁ | ... | βₙ
        Transform to:
        Aᵢ → β₁A' | ... | βₙA'
        A' → α₁A' | ... | αₘA' | ε

3. Output:
   - Equivalent grammar without left recursion
   - New nonterminals generated as needed (single uppercase letters)
   - Same input format used for output

Output format:
- One production per line in "X -> Y Z" format
- Empty line between different test cases
- No additional text or lines are printed.
------------------------------------------------------------
------------------------------------------------------------
------------------------------------------------------------

Reference
------------------------------------------------------------
Kozen, Dexter C. (1997). Automata and Computability.
DOI: https://doi.org/10.1007/978-1-4612-1844-9
