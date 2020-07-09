package com.access;

public class Outer{ //cannot be private or protected
    class InnerDefault {}
    public class InnerPublic {}
    protected class InnerProtected { }
    private class InnerPrivate { }
}
