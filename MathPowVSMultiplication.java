/**
 * 
 * @author re
 */
public class NewClass {
    public static void main(String[] args) {
/* 
 * Be careful with Math.pow() for it is very slow and inefficient for smaller powers.
 * Here it does not matter, but in some loop difference between a*a*a*a and Math.pow(a,4)
 * can be quite significant. Or at least it was this way last time I checked, not 
 * sure whether compiler approach changed in java 8. –  Vojtěch Kaiser Jun 9 at 19:34
 */
        int a = 2;
        int b = 0;
        final int range = 12345678;
        
        //warm up
        for (int i = 0; i < 10000; i++) {
            b = a*a*a*a;
        }
        
        long start = System.nanoTime();
        
        for (int i = 0; i < range; i++) {
            Math.pow(a,4);
        }
        long end = System.nanoTime() - start;
        System.out.println(range + " times Math.pow(a,4) executed in: " + end);
        
        for (int i = 0; i < range; i++) {
            b = a*a*a*a;
        }
        end = System.nanoTime() - start;
        System.out.println(range + " times b = a*a*a*a executed in:   " + end);
    }
}