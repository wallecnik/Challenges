import easy.Easy0184;
import easy.Easy0185;
import intermediate.Intermediate0186;

/**
 * Created by Wallecnik on 24.10.14.
 */
public class Demo {

    public static void main(String[] args) {

        intermediate0186(3.30085);

    }

    private static void intermediate0186(double time) {

        Intermediate0186 obj = new Intermediate0186();
        obj.syzygyOccurence(time);

    }

    private static void easy0184() {
        Easy0184 object = new Easy0184();

        for (int i = 0; i < ((int) 40 * Math.random()); i++) {
            object.push((int) (2000 * (Math.random() - .5)));
        }

        object.displayOrdered();
        object.displayStack();

        int x = (int) (2000 * (Math.random() - .5));

        System.out.println("\nRemoving anything greater than " + x + ".");
        object.removeGreater(x);

        object.displayOrdered();
        object.displayStack();

        System.out.println("\nPopping half of stack.");
        for (int i = 0; i < object.getSize() / 2; i++) {
            object.pop();
        }

        object.displayOrdered();
        object.displayStack();

    }

    private static void easy0185() {

        Easy0185 object = new Easy0185("/Users/Wallecnik/Dropbox/dokumenty/programming/Java/Challenges/src/enable1.txt");
        String changes = object.chooseAndSubstitute("at", "@");

        String[] lines = changes.split("\\n");

        System.out.println("10 longest descending:");
        for (int i = lines.length-1; i >= lines.length-10; i--) {
            System.out.println(lines[i]);
        }

        System.out.println("10 shortest ascending:");
        for (int i = 0; i < 10; i++) {
            System.out.println(lines[i]);
        }

    }

}
