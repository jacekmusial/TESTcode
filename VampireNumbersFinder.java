import java.util.ArrayList;
import java.util.Arrays;

/**
 * 
 * @author re
 */
public class VampireNumbersFinder {
    
    /**
     *
     * @param numberOfDigits {int}
     * @return ArrayList of Integer
     */
    public ArrayList<Integer> vdf(int numberOfDigits) {
        
        if ((numberOfDigits % 2) == 1) { //if numberOfDigits its odd
            //or throw Exception of unrecognised format/variable?
            System.out.println("cant operate on odd argument");
            return new ArrayList<>();
        }
        //dodaj potem zakresy różne, range, np od 1000 do 5555
        //albo 1234 - 6666
        final int saintValue = 9; //is this even necessary ;d
        long maxRange = 9;
        
        for (int i = 1; i < numberOfDigits; i++) {
            maxRange *= 10;
            maxRange += 9;
        }//numberOfDigits==4 then maxRange==9999, nOD==5 then maxRange==99999,..
        
        int value = saintValue - (saintValue-1); //value=1
        long minRange = value;
        
        for (int i = 1; i < numberOfDigits; i++) {
            minRange *= 10;
        }//nOD==4 then minRange==1000, nOD==5 then minRange==10000, ..
        /*
        
            BigInteger
        
        */
        ArrayList<Integer> ret = new ArrayList<>();
        for (long i = minRange; i < maxRange; i++) {
            
            long a = i;
            System.out.println(i);
            
            long[] b = new long[numberOfDigits];
            
            for (int j = numberOfDigits-1; j >= 0 ; j--) {
                long c = a % 10;
                a = a / 10;
                b[j] = c;
            }
            
            int x = 0;
            int y = 0;
            ArrayList<long[]> list = permutations(b);
            b = null; //dont need now
            
            for(long[] s : list) {
                for (int j = 0; j < numberOfDigits/2; j++) {
                    x += s[(numberOfDigits/2)-j-1] * Math.pow(10, j);
                    y += s[numberOfDigits-j-1] * Math.pow(10, j);
                }
                StringBuilder builder = new StringBuilder();
                for (long t : s) {
                    builder.append(t);
                }
                String v = builder.toString();
                
                if ((v.charAt((v.length()/2)-1) != '0'||
                    v.charAt(v.length()-1) != '0') &&
                    x * y == i) {
                    ret.add(x);
                    ret.add(y);
                    System.out.println(x*y+"  "+x+" "+y);
                    break;
                }
                x = y = 0;
            }
        }
        System.out.printf("%d vampire numbers found\n", ret.size()/2);
        return ret;
    }
    
    /**
     * 
     *@return vdf(4)
     */
    public ArrayList<Integer> vdf() {
        return vdf(4);//without trailing zeros
    }
    
    /* permutation code copied from  
     * johk95
     * http://stackoverflow.com/a/20906510
     */
    private static ArrayList<long[]> permutations(long[] lol) {
        ArrayList<long[]> ret = new ArrayList<>();
        permutation(lol, 0, ret);
        return ret;
    }
    
    private static void permutation(long[] arr, int pos, ArrayList<long[]> list){
        if(arr.length - pos == 1)
            list.add(arr.clone());
        else
            for(int i = pos; i < arr.length; i++){
                swap(arr, pos, i);
                permutation(arr, pos+1, list);
                swap(arr, pos, i);
            }
    }

    private static void swap(long[] arr, int pos1, int pos2){
        long h = arr[pos1];
        arr[pos1] = arr[pos2];
        arr[pos2] = h;
    }
    
    public static void main(String[] args) {
        vampireNumbersFinder a = new vampireNumbersFinder();
        try{
            a.vdf(8); // try only with 4,6,8. bigger arguments creating heap space problem.
        }catch (java.lang.OutOfMemoryError e){
            System.err.println(e.getMessage());
        }
    }
}