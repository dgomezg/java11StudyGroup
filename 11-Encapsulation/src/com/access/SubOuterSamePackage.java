package com.access;

public class SubOuterSamePackage extends Outer {
    public static void main(String[] args) {
        // This does not work in an static context
        //Outer.InnerPublic  pub2 = new Outer.InnerPublic();

        Outer outer = new Outer();
        SubOuterSamePackage subOuter = new SubOuterSamePackage();

        /* how to use: uncomment each line, see what IDE says and check */

        //Outer.InnerDefault def = outer.new InnerDefault();
        //Outer.InnerPublic  pub = outer.new InnerPublic();
        //Outer.InnerProtected prot = outer.new InnerProtected();
        //Outer.InnerPrivate priv = outer.new InnerPrivate();

        //SubOuterSamePackage.InnerDefault subdef = subOuter.new InnerDefault();
        //SubOuterSamePackage.InnerPublic  subpub = subOuter.new InnerPublic();
        //SubOuterSamePackage.InnerProtected subprot = subOuter.new InnerProtected();
        //SubOuterSamePackage.InnerPrivate subpriv = subOuter.new InnerPrivate();

    }

    public void buildOuters() {
        //Outer.InnerDefault subdef = new Outer.InnerDefault();
        //Outer.InnerPublic  subpub = new Outer.InnerPublic();
        //Outer.InnerProtected subprot = new Outer.InnerProtected();
        //Outer.InnerPrivate subpriv = new Outer.InnerPrivate();
    }

    public void buildSubOuters() {
        //SubOuterSamePackage.InnerDefault subdef = new SubOuterSamePackage.InnerDefault();
        //SubOuterSamePackage.InnerPublic  subpub = new SubOuterSamePackage.InnerPublic();
        //SubOuterSamePackage.InnerProtected subprot = new SubOuterSamePackage.InnerProtected();
        //SubOuterSamePackage.InnerPrivate subpriv = new SubOuterSamePackage.InnerPrivate();
    }
}
