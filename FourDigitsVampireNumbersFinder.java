import java.util.ArrayList;
/*
 *
 * @author re
 */
public class FourDigitsVampireNumbersFinder {

    /* permutation code copied from johk94 http://stackoverflow.com/a/20906510
     */
    static ArrayList<int[]> permutations(int[] lol) {
        ArrayList<int[]> ret = new ArrayList<>();
        permutation(lol, 0, ret);
        return ret;
    }
    
    public static void permutation(int[] arr, int pos, ArrayList<int[]> list){
        if(arr.length - pos == 1)
            list.add(arr.clone());
        else
            for(int i = pos; i < arr.length; i++){
                swap(arr, pos, i);
                permutation(arr, pos+1, list);
                swap(arr, pos, i);
            }
    }

    public static void swap(int[] arr, int pos1, int pos2){
        int h = arr[pos1];
        arr[pos1] = arr[pos2];
        arr[pos2] = h;
    }
    
    public static void main(String[] args) {
        
        
        for (int i = 1000; i < 9999; i++) {
            
            int a = i;
            int c;
            int[] b = new int[4];
            
            for (int j = 3; j >= 0; j--) {
                c = a % 10;
                a = a / 10;
                b[j] = c;
            }
            
            ArrayList<int[]> list = permutations(b);
            
            for(int[] s : list) {
                int x, y;
                
                x = s[0]*10 + s[1];
                y = s[2]*10 + s[3];
                
                if( y != 0 && x * y == i){
                    System.out.printf("%d = %d * %d\n", i, x,y);
                    break;  
                }
            }
        }
    }
	
    
        //}
        /*System.out.println(Integer.toString(b[0]));
        Wszystko w = new Wszystko();
        String x = w.convertToString(1234);
        System.out.println(x + " " + x.charAt(0));*/
        //
        //a[1] = 
        //}
        /*public static void main (String [] args) {
        for (Iterator i = new Permute(args); i.hasNext(); ) {
        final String [] a = (String []) i.next();
        System.out.println (i);
        }
        }*/
}