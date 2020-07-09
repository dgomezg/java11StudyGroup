package com.access2;

import com.access.Outer;

public class SubOuter extends Outer {
    public static void main(String[] args) {
        // This does not work in an static context
        //Outer.InnerPublic  pub2 = new Outer.InnerPublic();

        Outer outer = new Outer();
        SubOuter subOuter = new SubOuter();

        /* how to use: uncomment each line, see what IDE says and check */

        //Outer.InnerDefault def = outer.new InnerDefault();
        //Outer.InnerPublic  pub = outer.new InnerPublic();
        //Outer.InnerProtected prot = outer.new InnerProtected();  // ???
        //Outer.InnerPrivate priv = outer.new InnerPrivate();

        //SubOuter.InnerDefault subdef = subOuter.new InnerDefault();
        //SubOuter.InnerPublic  subpub = subOuter.new InnerPublic();
        //SubOuter.InnerProtected subprot = subOuter.new InnerProtected();  // ???!!!
        //SubOuter.InnerPrivate subpriv = subOuter.new InnerPrivate();
    }

    public void buildOuters() {
        //Outer.InnerPublic  subpub = new Outer.InnerPublic();
        //Outer.InnerDefault subdef = new Outer.InnerDefault();
        //Outer.InnerProtected subprot = new Outer.InnerProtected();
        //Outer.InnerPrivate subpriv =  new Outer.InnerPrivate();
    }

    public void buildSubOuters() {
        //SubOuter.InnerPublic  subpub = new SubOuter.InnerPublic();
        //SubOuter.InnerDefault subdef = new SubOuter.InnerDefault();
        //SubOuter.InnerProtected subprot = new SubOuter.InnerProtected();
        //SubOuter.InnerPrivate subpriv =  new SubOuter.InnerPrivate();
    }
}