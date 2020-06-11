package dev.dgomezg.sandbox.java11.methods;

import java.util.List;
import java.util.Optional;

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

    //Method can return reference Types
    public String concatNumbers(int a, int b) {
        return "" + a + b; //<-- are we still using this trick?
    }

    // Polymorphism is allowed in return types
    public Object concatChars(char a, char b) {
        return new StringBuilder(2).append(a).append(b).toString();
    }

    public String find(List<String> strings, String value) {
        return null; //can't return null;
    }

    //IF we are going to return null, we may consider Optionals.
    public Optional<String> findWithOptional(List<String> strings, String value) {
        return strings.stream().filter(s -> s.equalsIgnoreCase(value)).findFirst();
    }

    

}
