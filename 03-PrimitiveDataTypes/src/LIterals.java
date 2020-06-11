public class LIterals {

    public static void main(String[] args) {

        byte ten = (byte)1000;
        System.out.println(ten);
        int value = 10;
        value = 0xA; //10 in hexadecimal (prefix 0x)
        value = 012; //10 in octal (prefix 0)
        value = 0b1010; //10 in binary (prefix 0b)
        value = 1_000; //use of _ in numeric literals
        value = 10_00; // _ can be used at any point in the numeric literals

        System.out.println(value);

    }
}
