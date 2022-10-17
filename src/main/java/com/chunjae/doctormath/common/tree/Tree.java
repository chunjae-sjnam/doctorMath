package com.chunjae.doctormath.common.tree;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Getter
public class Tree {
    protected Logger logger = LoggerFactory.getLogger(Tree.class);

    private Node root = null;

    public Tree(){
        root = new Node();
    }

    public void addNode(List<Node> nodes){
        Node currentNode = this.root;
        for(int i = 0; i < nodes.size(); i++){
            Node parentNode = currentNode;
            currentNode = currentNode.searchChild(nodes.get(i).getKey());
            if(currentNode == null){
                currentNode = parentNode.addChild(nodes.get(i));
            }
        }
    }
}
