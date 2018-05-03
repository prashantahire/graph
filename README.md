# graph
Problem Statement:
Implement a directed graph of people.
The graph should conform to the following properties :
       - The graph should contain at least 10 people
       - Each person should have at least 3, first level "connections" to other people in the graph.
          --> i.e. Node.children() >= 3 should hold true for all nodes in the graph.
        - The graph should be cyclic
Build a unit test that builds a graph of people and demonstrates
that all three properties described above are satisfied.

Solution:
Solving this using BFS and DFS graph traversal algos...
