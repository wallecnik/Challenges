import easy.Easy0185;

/**
 * Created by Wallecnik on 24.10.14.
 */
public class Demo {

    public static void main(String[] args) {

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
