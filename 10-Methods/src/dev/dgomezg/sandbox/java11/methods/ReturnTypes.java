package dev.dgomezg.sandbox.java11.methods;

public class ReturnTypes {

    // A method can return nothing (void)
    public void doSomething() {
        //invalid:
        // return null;

        //valid
        return;
    }

    // A method can return a primitive type
    public int sum(int a, int b) {
        return a+b;
    }

    // implicit widening is applied for return primitive types
    public long sumAsLong(int a, int b) {
        return a + b; //int fits in a long
    }

    public byte sumSmallNumbers(int a, int b) {
        //won't compile as integer may not fit into a byte
        //return a + b;

        //If we are sure that numbers are small, and sum fits into a byte,
        //an implicit cast is required (not a good practice, though)
        return (byte) (a + b);
    }

    public byte sum(byte a, byte b) {
        //Why the following line does not compile?
        //return a + b;
        return (byte) (a + b);
    }

}
