package com.access;

public class SubOuterSamePackage extends Outer {
    public static void main(String[] args) {
        Outer outer = new Outer();
        SubOuterSamePackage subOuter = new SubOuterSamePackage();

        Outer.InnerDefault def = outer.new InnerDefault();
        Outer.InnerPublic  pub = outer.new InnerPublic();
        Outer.InnerProtected prot = outer.new InnerProtected();
        Outer.InnerPrivate priv = outer.new InnerPrivate();

        SubOuterSamePackage.InnerDefault subdef = subOuter.new InnerDefault();
        SubOuterSamePackage.InnerPublic  subpub = subOuter.new InnerPublic();
        SubOuterSamePackage.InnerProtected subprot = subOuter.new InnerProtected();
        SubOuterSamePackage.InnerPrivate subpriv = subOuter.new InnerPrivate();

    }
}
