package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("output.txt")));

        ArrayList<Edge> edgeList= new ArrayList<>();
        List<List<String>> adjList = new ArrayList<List<String>>();
        List<List<String>> broadcastList = new ArrayList<List<String>>();
        List<String> rootList = new ArrayList<>();
        String root= "";
        ReadFile file = new ReadFile();
        rootList.add(file.read(args[0],edgeList,adjList,root));
        ArrayList<String> usedNodes= new ArrayList<>();
        Worker worker= new Worker();
        worker.printStructere(writer,edgeList);


        worker.broadCast(writer,rootList,broadcastList,edgeList,usedNodes);
        for(List<String> element: broadcastList){
            System.out.println(element);
        }
        worker.printBroadCast(writer,broadcastList);
        worker.printMessagePassing(writer,broadcastList,edgeList);


        /*for (Edge edge: edgeList){
            System.out.println("**"+edge.getName());
            for (int i=0;i<edge.getAdj().size();i++){
                System.out.println(edge.getAdj().get(i));
            }
        }
        for (int i=0; i<adjList.size();i++){
            System.out.println(adjList.get(i)+" "+i);
        }*/

       /* for (Edge edge: edgeList){
            System.out.println(edge.getName()+" "+edge.isSituation());
        }*/
        writer.close();
    }
}
