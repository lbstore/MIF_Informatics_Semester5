package com.company;

public class Main {

    public static void main(String[] args) {
        Tree a = new Tree("root");
        Tree b = new Tree("n1");
        Tree c = new Tree("n2");
        Tree d = new Tree("n3");
        Tree e = new Tree("n4");
        Tree f = new Tree("n5");

        a.addAChild(b);
        a.addAChild(c);
        a.addAChild(d);

        b.addAChild(e);
        e.addAChild(f);

        a.print(0);
    }
}
