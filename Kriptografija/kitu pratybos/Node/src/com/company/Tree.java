package com.company;

import java.lang.reflect.Array;
import java.util.*;


public class Tree {
    String data;
    Tree parent = null;
    List<Tree> children = new ArrayList<Tree>();

    public Tree(String nodeName) {
        this.data = nodeName;
    }

    public void parent(Tree parent) {
        this.parent = parent;
    }

    public void addAChild(Tree child) {
        this.children.add(child);
        child.parent(this);
    }

    public void print(int indent) {
        for (int i = 0; i < indent; i++) {
            System.out.print("\t");
        }
        System.out.println(data);
        for (Tree i : children) {
            i.print(indent + 1);
        }
    }

}
