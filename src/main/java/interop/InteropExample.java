package interop;

import java.io.IOException;

public class InteropExample {
    public static void main(String[] args) {
        DevHack.sayWelcome();
        DevHackStudent student = new DevHackStudent();


        CoinValue bitcoin = new CoinValue("Bitcoin");
        try {
            bitcoin.calculateValue();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Foo implements WithDefault {
    private void foo(){
        welcome();
    }

    public void accessFiles() {

    }
}

class Bar {

}
