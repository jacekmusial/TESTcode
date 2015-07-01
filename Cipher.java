/*
TODO: 
* add enum class to easy handle string/text
* add rest of algorithm
*/
class Cipher
{
    char unsignedRotCipher(int shift, char ret) {
        //rot of UNSIGNED shift; rollback or WTVR
        
        while(shift < 0 && Character.isLetter(ret)) {
  
            if(ret == 'A') {
                ret = 'Z';
            }else {
                ret = (char) ((int)ret - 1);
            }
            
            shift++;         
        }
        return ret;
    }
    
    char signedRotCipher(int shift, char ret) {
        //rot of SIGNED shift
        
        while(shift > 0 && Character.isLetter(ret)) {
  
            if(ret == 'Z') {
                ret = 'A';
            }else {
                ret = (char) ((int)ret + 1);
            }
            
            shift--;         
        }
        return ret;
    }
    
    String rotCipher(int shift, String c) {
        String ret = "";
        
        for (int i = 0; i < c.length(); i++) {
            ret += 
                (shift > 0) 
                ? signedRotCipher(shift, c.charAt(i)) 
                : unsignedRotCipher(shift, c.charAt(i))
            ;
        }
        return ret;
    }

    char atBashCipher(char ret) {
        
        ret = Character.toUpperCase(ret);
        
        String x = "ABCDEFGHIJKLM";
        String y = "ZYXWVUTSRQPON";
        int i;
        
        if( (i = x.indexOf(ret)) != -1 ) {
            return y.charAt(i);
        }else
        if( (i = y.indexOf(ret)) != -1 ) {
            return x.charAt(i);
        }
        
        return '?';
        /*if( (int)ret >= (int)'A' &&
            (int)ret <= (int)'M') {
            
            int a = 77 - ((int)ret);    //difference
            return (char) ((int)90 - a);
        }
        else
        if( (int)ret >= (int)'N' &&
            (int)ret <= (int)'Z') {
            
            int a = 90 - ((int)ret);    //difference
            return (char) ((int)90 - a);
        }
        //hopefully never executed
        return '?';*/
    }
    
    
    Cipher() {
//        this.Ideone(13);
    }
        
    public static void main (String[] args) throws java.lang.Exception
    {
        Cipher i = new Cipher();
        
        String ret = "FXEEMOMGEB";
        //for (int j = 0; j < ret.length(); j++) {
            System.out.printf("%s", i.rotCipher(-3, ret));
        //}
        
    }
}