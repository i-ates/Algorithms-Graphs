package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadFile {
    public String read(String fileName, ArrayList<Edge> edgeList, List<List<String>> adjList,String root){
        try {
            File myObj = new File(fileName);
            Scanner myReader = new Scanner(myObj);
            String[] firstline = myReader.nextLine().split(" ");
            for (int i=0;i<firstline.length;i=i+2){
                edgeList.add(new Edge(firstline[i],Integer.parseInt(firstline[i+1])));
            }
            root= myReader.nextLine();
            while (myReader.hasNextLine()) {
                String data= myReader.nextLine();
                String[] str= data.split(" ");
                ArrayList<String> adj=new ArrayList<>(2);
                adj.add(str[0]);
                adj.add(str[1]);
                adjList.add(adj);

                for (Edge edge: edgeList){
                    if (edge.getName().equals(str[0])){
                        edge.addAdj(str[1]);
                    }
                    if (edge.getName().equals(str[1])){
                        edge.addAdj(str[0]);
                    }
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return  root;
    }
}