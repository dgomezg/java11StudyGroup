package com.access2;

import com.access.Outer;

public class SubOuter extends Outer {
    public static void main(String[] args) {
        Outer outer = new Outer();
        SubOuter subOuter = new SubOuter();

        //Outer.InnerDefault def = outer.new InnerDefault();
        //Outer.InnerPublic  pub = outer.new InnerPublic();
        //Outer.InnerProtected prot = outer.new InnerProtected();
        //Outer.InnerPrivate priv = outer.new InnerPrivate();

        //SubOuter.InnerDefault subdef = subOuter.new InnerDefault();
        //SubOuter.InnerPublic  subpub = subOuter.new InnerPublic();
        //SubOuter.InnerProtected subprot = subOuter.new InnerProtected();
        //SubOuter.InnerPrivate subpriv = subOuter.new InnerPrivate();
    }
}