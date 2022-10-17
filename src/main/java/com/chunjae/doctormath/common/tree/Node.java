package com.chunjae.doctormath.common.tree;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Node {

    private long key;
    private Object text;
    private List<Node> children;
    private int depth;

    public Node(){
        this.key = 0L;
        this.text = null;
        this.children = new ArrayList<>();
        this.depth = 0;
    }

    public Node(long key,Object value,int depth){
        this.key = key;
        this.text = value;
        this.children = new ArrayList<>();
        this.depth = depth;
    }

    public Node searchChild(long key){
        if(children != null){
            for(int i = 0; i < children.size(); i++){
                if(children.get(i).getKey() == key){
                    return children.get(i);
                }
            }
            return null;
        }else{
            return null;
        }
    }

    public Node addChild(Node node){
        this.children.add(node);
        return node;
    }
}
