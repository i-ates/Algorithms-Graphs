package com.company;

import java.util.LinkedList;

public class Edge {
    private String name;
    private int capacity;
    private LinkedList<String> adj;
    private  boolean situation= false;

    public Edge(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.adj=new LinkedList<>();
    }
    public  Edge(){

    }
    public void cloneEdge(Edge edge){
        name=edge.getName();
        capacity=edge.getCapacity();
        adj=edge.getAdj();
        situation=edge.isSituation();
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public LinkedList<String> getAdj() {
        return adj;
    }

    public void addAdj(String adj) {
        this.adj.add(adj);
    }

    public boolean isSituation() {
        return situation;
    }

    public void setSituation(boolean situation) {
        this.situation = situation;
    }
}