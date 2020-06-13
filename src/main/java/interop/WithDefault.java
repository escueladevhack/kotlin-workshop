package interop;

public interface WithDefault {
    default void welcome(){
        System.out.println("Welcome student");
    }
}
