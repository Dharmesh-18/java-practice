import java.util.function.Consumer;  // accept() One input, no output, only for some operation on the single input
import java.util.function.Function;  // apply() One input, one output, type dono ka dena hoga
import java.util.function.Predicate; // test() One input, boolean output
import java.util.function.Supplier;  // get() No input, but one output

public class FunctionalInterface {
    
    public static void main(String[] args) {
        
        Consumer<String> consumer = (String val) -> {
            System.out.println(val.toUpperCase());
        };

        consumer.accept("Dharmesh");

        Supplier<String> supplier = () -> {
            return "Hello";
        };

        System.out.println(supplier.get());

        Predicate<String> predicate = (String val) -> {
            return val.length() > 0;
        };

        System.out.println(predicate.test("D"));

        Function<Integer, String> function = (Integer val) -> {
            return val >= 5 ? "Greater or equal to 5" : "Less than 5";
        };

        System.out.println(function.apply(4));


    }
}
