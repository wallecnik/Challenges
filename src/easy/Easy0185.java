package easy;

import library.Library;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Wallecnik on 24.10.14.
 */
public class Easy0185 implements Comparator<String> {

    String plainText;

    public Easy0185(String filePath) {
        this.plainText = Library.readFile(filePath);
    }

    /**
     * Substitutes the 'from' String to the 'to' String
     * and returns specially formatted string only with
     * altered words
     *
     * examples:
     * @tendace : attendace (at -> @)
     * loopest : forest (for -> loop)
     *
     * @param from  subString to substituted
     * @param to    String to be placed instead
     * @return      whole text with substitutions
     */
    public String chooseAndSubstitute(String from, String to) {

        //choose words containing 'from' sequence
        ArrayList<String> list = new ArrayList<String>();
        for (String word : plainText.split("\\n")){
            if (word.contains(from)) {
                list.add(word);
            }
        }

        Collections.sort(list, this);

        //replace every occurence of 'from' sequence
        StringBuffer buffer = new StringBuffer();
        Pattern pattern = Pattern.compile(from);
        for (String word : list){
            Matcher matcher = pattern.matcher(word);
            while (matcher.find()){
                matcher.appendReplacement(buffer, to);
            }
            matcher.appendTail(buffer);
            buffer.append(" : " + word + "\n");
        }

        return buffer.toString();

    }


    /**
     * Compares its two arguments for order.  Returns a negative integer,
     * zero, or a positive integer as the first argument is less than, equal
     * to, or greater than the second.<p>
     * <p/>
     * In the foregoing description, the notation
     * <tt>sgn(</tt><i>expression</i><tt>)</tt> designates the mathematical
     * <i>signum</i> function, which is defined to return one of <tt>-1</tt>,
     * <tt>0</tt>, or <tt>1</tt> according to whether the value of
     * <i>expression</i> is negative, zero or positive.<p>
     * <p/>
     * The implementor must ensure that <tt>sgn(compare(x, y)) ==
     * -sgn(compare(y, x))</tt> for all <tt>x</tt> and <tt>y</tt>.  (This
     * implies that <tt>compare(x, y)</tt> must throw an exception if and only
     * if <tt>compare(y, x)</tt> throws an exception.)<p>
     * <p/>
     * The implementor must also ensure that the relation is transitive:
     * <tt>((compare(x, y)&gt;0) &amp;&amp; (compare(y, z)&gt;0))</tt> implies
     * <tt>compare(x, z)&gt;0</tt>.<p>
     * <p/>
     * Finally, the implementor must ensure that <tt>compare(x, y)==0</tt>
     * implies that <tt>sgn(compare(x, z))==sgn(compare(y, z))</tt> for all
     * <tt>z</tt>.<p>
     * <p/>
     * It is generally the case, but <i>not</i> strictly required that
     * <tt>(compare(x, y)==0) == (x.equals(y))</tt>.  Generally speaking,
     * any comparator that violates this condition should clearly indicate
     * this fact.  The recommended language is "Note: this comparator
     * imposes orderings that are inconsistent with equals."
     *
     * @param o1 the first object to be compared.
     * @param o2 the second object to be compared.
     * @return a negative integer, zero, or a positive integer as the
     * first argument is less than, equal to, or greater than the
     * second.
     * @throws NullPointerException if an argument is null and this
     *                              comparator does not permit null arguments
     * @throws ClassCastException   if the arguments' types prevent them from
     *                              being compared by this comparator.
     */
    @Override
    public int compare(String o1, String o2) {
        if (o1 == null || o2 == null) throw new NullPointerException();

        if (o1.length() < o2.length()) return -1;
        if (o1.length() == o2.length()){
            for (int i = 0; i < o1.length(); i++) {
                if (o1.charAt(i) < o2.charAt(i)) return -1;
                if (o1.charAt(i) == o2.charAt(i)) continue;
                if (o1.charAt(i) > o2.charAt(i)) return 1;
            }
        }
        if (o1.length() > o2.length()) return 1;
        return 0;
    }
}
