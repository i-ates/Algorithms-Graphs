package com.company;

import java.io.IOException;
import java.io.Writer;
import java.util.*;

public class Worker {
    public Worker() {
    }

    public void printStructere(Writer writer, ArrayList<Edge> edgeList) throws IOException {
        writer.write("Graph structure:\n");
        for (Edge edge: edgeList){
            String str="";
            for (int i=0;i<edge.getAdj().size();i++){
                str=str+edge.getAdj().get(i)+" ";
            }
            str=str.substring(0,str.length()-1);
            writer.write(edge.getName()+"("+edge.getCapacity()+")-->"+str+"\n");
        }
    }
    public void broadCast(Writer writer, List<String> rootList, List<List<String>> broadcastList, ArrayList<Edge> edgeList, ArrayList<String> usedNodes){
        boolean check=false;
        List<String> tempRootList= new ArrayList<>();
        for (String root: rootList){
            for (Edge edge: edgeList){
                if (edge.getName().equals(root)){
                    if (!edge.isSituation()){
                        check=true;
                        tempRootList.add(root);
                    }
                }
            }
        }

        if(check){
            for (int i=0;i<tempRootList.size();i++){
                for (Edge edge: edgeList){
                    if (edge.getName().equals(tempRootList.get(i))){
                        for (int j=0;j<edge.getAdj().size();j++){
                            for (Edge edge1: edgeList){
                                if(edge1.getName().equals(edge.getAdj().get(j))){
                                    if(!edge1.isSituation()){
                                        if (!usedNodes.contains(edge1.getName())){
                                            ArrayList<String> tempList= new ArrayList<>();
                                            tempList.add(edge.getName());
                                            tempList.add(edge1.getName());
                                            broadcastList.add(tempList);
                                            usedNodes.add(edge1.getName());

                                        }
                                    }
                                }
                            }
                        }
                        edge.setSituation(true);
                    }

                }

            }
        }
        List<String> newRootList= new ArrayList<>();
        for (String tempRoot: tempRootList){
            for (Edge edge: edgeList){
                if (edge.getName().equals(tempRoot)){
                    for (String temp: edge.getAdj()){
                        newRootList.add(temp);
                    }
                }
            }
        }

        if (newRootList.size()!=0){
            broadCast(writer,newRootList,broadcastList,edgeList,usedNodes);
        }



    }
    public void printBroadCast(Writer writer,List<List<String>> broadcastList) throws IOException {
        Map<String,List<String> > broadcastMap= new LinkedHashMap<>();
        for (List<String> element: broadcastList){
            if(!broadcastMap.containsKey(element.get(0))){
                broadcastMap.put(element.get(0),new ArrayList<String>());
            }
            broadcastMap.get(element.get(0)).add(element.get(1));
        }
        System.out.println(broadcastMap);
        writer.write("Broadcast Steps:\n");
        for(String  key: broadcastMap.keySet()){
            String str="";
            for (int i=broadcastMap.get(key).size()-1; i>=0;i--){
                str=broadcastMap.get(key).get(i)+ " "+str;
            }
            str=str.substring(0,str.length()-1);
            writer.write(key+"-->"+str+"\n");
        }
    }
    public void printMessagePassing(Writer writer, List<List<String>> broadcasList, List<Edge> edgeList) throws IOException {
       List<List<String>> capacityList= new ArrayList<>();
       String oldCapacity = null;
       int capacityEdgeSent=0;
       int capacityEdgeGet=0;
       for (int i=broadcasList.size()-1; i>=0;i--){
           for (Edge edge:edgeList){
               if(edge.getName().equals(broadcasList.get(i).get(1))){
                   capacityEdgeSent= edge.getCapacity();
               }
               if(edge.getName().equals(broadcasList.get(i).get(0))){
                   capacityEdgeGet=edge.getCapacity();
               }
               for (int x=0;x<=capacityList.size();x++){
                   try{
                       if (capacityList.get(x).contains(broadcasList.get(i).get(1))){
                           oldCapacity=capacityList.get(x).get(2);
                       }
                       else{
                           oldCapacity=null;
                       }

                   }catch (Exception e){

                   }

               }


           }
           if (oldCapacity!=null){/*
                   List<String> templist= new ArrayList<>();
                   String temp=oldCapacity.substring(1 ,1);
                   int tempCapacity;
                   if (capacityEdgeSent>capacityEdgeGet){
                       tempCapacity=capacityEdgeSent;

                   }else if(capacityEdgeGet==capacityEdgeSent){
                       tempCapacity=capacityEdgeGet;
                   }else{
                        tempCapacity=capacityEdgeGet;
                   }
                   if (Integer.parseInt(temp)>){

                   }*/

           }else{
               System.out.println( broadcasList.get(i).get(1)+" " +capacityEdgeSent+" "+""+broadcasList.get(i).get(0)+" "+capacityEdgeGet);
               if (capacityEdgeSent>capacityEdgeGet){

                   List<String> templist= new ArrayList<>();
                   templist.add(broadcasList.get(i).get(1));
                   templist.add(broadcasList.get(i).get(0));
                   //String str=broadcasList.get(i).get(1)+edge.getCapacity();
                   capacityList.add(templist);


               }else if(capacityEdgeGet==capacityEdgeSent){

               }else{

               }

           }
       }
        System.out.println(capacityList);
    }
}
