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

Also implemented test functions for graph:

Function that traverses the graph and returns a map that consists of all people in the graph as "keys" that map to a list of all associated connections up to a certain depth.

Function that traverses the graph and returns whether or not two people are "connected" and what "level" (or depth) the connection is within the graph.
