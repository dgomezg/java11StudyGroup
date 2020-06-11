package dev.dgomezg.sandbox.java11.methods;

import java.util.List;

/**
 * Method overloading is allowed provided that the method signature is different
 *
 * Method signature is composed by the method name + a comma separated list of argument types.
 * (modifiers, return type, argument modifiers, generics or throws clauses does not belong to the method signature and
 * thus, won't be allowed as the unique differeces between method names)
 */
public class MethodOverloading {


    public void doSomething() {}
    //The following methods have the same signature and won't be valid overloaded methods;
    //private void doSomething() {}
    //private int doSomething() { return 0 }
    //public int doSomething() throws Exception { return 0}

    public void doSomething(int seed) {
        System.out.println("doSomething(int)");
    }
    //the argument name does not belong to the method signature;
    //public void doSomething(int number) {};
    //the final modifier does not belong to the method signature;
    //public void doSomething(final int number) {};

    public void doSomething(Integer seed) {
        System.out.println("doSomething(Integer)");
    }
    public void doSomething(Byte encoding) {}
    public void doSomething(int seed, Byte encoding) {}
    public void doSomething(Integer seed, byte encoding) {}

    public void methodSelectionSamples() {
        int seed = 10;
        byte encoding = 3;
        doSomething(seed); //Invokes doSomething(int) -> direct match
        doSomething(encoding);
        //do some more doSomething invocations trying to find out which method will be selected
        //doSomething(seed, encoding); won't compile, why?
    }


    public void iterate(List<Number> numbers) {}
    //The following method won't compile, as generics are erased (type erasure) from runtime
    //public void iterate(List<Integer> integers) {}



}
