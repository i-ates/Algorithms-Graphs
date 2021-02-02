package com.company;


import java.io.*;
import java.util.*;
import java.util.LinkedList;


public class Graph
{

    private int V; // No. of vertices
    boolean check= false;

    private LinkedList<Integer> adj[];
    int time = 0;
    static final int NIL = -1;


    Graph(int v)
    {
        V = v;
        adj = new LinkedList[v];
        for (int i=0; i<v; ++i)
            adj[i] = new LinkedList();
    }


    void addEdge(int v, int w)
    {
        adj[v].add(w); // Add w to v's list.
        adj[w].add(v); //Add v to w's list
    }

    void bridgeUtil(int u, boolean visited[], int disc[],
                    int low[], int parent[], Writer myWritter) throws IOException {
        boolean check= false;
        // Mark the current node as visited
        visited[u] = true;

        disc[u] = low[u] = ++time;

        Iterator<Integer> i = adj[u].iterator();
        while (i.hasNext())
        {
            int v = i.next(); // v is current adjacent of u

            if (!visited[v])
            {
                parent[v] = u;
                bridgeUtil(v, visited, disc, low, parent, myWritter);

                low[u] = Math.min(low[u], low[v]);

                if (low[v] > disc[u])
                    myWritter.write(u+" "+v+"\n");
                    setCheck(true);
            }
            else if (v != parent[u])
                low[u] = Math.min(low[u], disc[v]);
        }

    }

    void bridge(Writer myWriter) throws IOException {
        myWriter.write("Q2\n");

        boolean visited[] = new boolean[V];
        int disc[] = new int[V];
        int low[] = new int[V];
        int parent[] = new int[V];

        for (int i = 0; i < V; i++)
        {
            parent[i] = NIL;
            visited[i] = false;
        }

        for (int i = 0; i < V; i++) {
            if (visited[i] == false)
                bridgeUtil(i, visited, disc, low, parent,myWriter);
        }
        if (check){
            myWriter.write("Graph is two-edge connected\n");
        }
    }
    void findParallelEdge(Writer myWriter) throws IOException {
        LinkedList<Integer> temp = new LinkedList<>();
        int counter = 0;
        int total = 0;
        for (int i = 0; i < adj.length; i++) {
            counter = 0;
            temp.clear();
            for (int j = 0; j < adj[i].size(); j++) {
                if (!temp.contains(adj[i].get(j))) {
                    counter++;
                    temp.add(adj[i].get(j));
                }
            }
            counter = adj[i].size() - counter;
            total = total + counter;
        }
        myWriter.write("Q1\n");
        myWriter.write(total/2+"\n");
    }
    public void setCheck(boolean check1){
        check=check1;
    }

}
