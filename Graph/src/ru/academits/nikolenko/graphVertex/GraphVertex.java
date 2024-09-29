package ru.academits.nikolenko.graphVertex;

import java.util.LinkedList;

public class GraphVertex<E> {
    private E data;
    private LinkedList<E> verticesConnected;

    public GraphVertex (E data ){
        this.data = data;
    }

    public E getData(){
        return data;
    }

    public void setData(E data){
        this.data = data;
    }

    public LinkedList<E> getVerticesConnected (){
        return verticesConnected;
    }

    public void setVerticesConnected (LinkedList<E> verticesConnected) {
        this.verticesConnected = verticesConnected;
    }

    @Override
    public String toString() {
        if (data == null){
            return "[]";
        }
        return String.format("[DATA: [%3s], verticesConnected : {%s} ]%n", data, verticesConnected.toString());
    }
}