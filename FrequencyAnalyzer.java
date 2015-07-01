import java.util.*;

/**
 * TODO: 
 * @author re
 */
public class FrequencyAnalyzer {
    
    private final String ALPHANUMERIC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private Map<Character, Integer> b = new HashMap<>(); 
    
    public void f(String a){
        
        for (int i = 0; i < ALPHANUMERIC.length(); i++) {
            b.put(ALPHANUMERIC.charAt(i), 0);
        }
        System.out.println(a);
        boolean finished;
        
        for (int i = 0;
            i < a.length() && Character.isAlphabetic(a.charAt(i));
            i++) {
            
            finished = false;
            for (int j = 0; j < ALPHANUMERIC.length() && !finished; ++j) {
                if( ALPHANUMERIC.charAt(j) == a.charAt(i) ) {
                    
                    Integer x = b.get(ALPHANUMERIC.charAt(j)); 
                    b.put(ALPHANUMERIC.charAt(j), x + 1);
                    
                    finished = true;
                }
            }
        }

        //ascending sorting by values
        Map<Character, Integer> sortedMap = sortByComparator(b);
        printMap(sortedMap);
        //printMap(b);
        System.out.println("");
        b.clear();
    }
    
    
    /* from http://www.mkyong.com/java/how-to-sort-a-map-in-java/
     * modifications: s/String/Character and use JAVA8 features(netBeans)
     */ 
    private static Map<Character, Integer>
    sortByComparator (Map<Character, Integer> unsortMap) {
        // Convert Map to List
        List<Map.Entry<Character, Integer>> 
        list = new LinkedList<>(unsortMap.entrySet());
 
        // Sort list with comparator, to compare the Map values
        Collections.sort(list, new ComparatorImpl());
 
        // Convert sorted map back to a Map
        Map<Character, Integer> sortedMap = new LinkedHashMap<>();
        list.stream().forEach((entry) -> {
            sortedMap.put(entry.getKey(), entry.getValue());
        });
        return sortedMap;
    }
    
    public static void printMap(Map<Character, Integer> map) {
        map.entrySet().stream().forEach((entry) -> {
            System.out.print(entry.getKey() + " ");
        });
        System.out.println("");
        
        map.entrySet().stream().forEach((entry) -> {
            System.out.print(entry.getValue()+ " ");
        });
        System.out.println("");
    }
    
    public static void main(String[] args) {
        FrequencyAnalyzer fa = new FrequencyAnalyzer();
 
        fa.f("UXLIRNXSOPQAKJBJTXSVTJKRHXKKJKLIQQOEBLXRSNJKWQKQKYSAQKLRVQNLJKQD "+
            "QRPLJRSEWIRSWQWJHQKTRKKQRWIXSODXNXJSNJTLIQBRNLLIXNXNUIRLXYSAQKLR " +
            "VQLJAJTJKEJYKQRAQK ");
        
        fa.f("FXEEMOMGEB ");
        
        fa.f("EVSXTTIWGVRMWIXWDXEMHWXDVCWGVSIKXWIRIQIVQFIVUUFIKGXEMLFVVMRVCFMW "
            + "XTGIWFBZITGIWITGXEBEXEDVTGIWYVCETWDLIBTIZIWKVLIXCTBUCFTGIWIBKEVQ "
            + "FXYIFBOIGVSI");
        
        fa.f("UZIRUKEJJXVMDKADJZXRYHSNOZENDNDKZXJDUIDJHJUAOAYIDUZIEUZNUKKXVOUA "
            + "OXZKUSSTSDUKYJUGSDUZIHDZOUSEUIDMOKCUVDAMDXGBDVAOGDKASOLDIAXKDD");
        
        fa.f("EPOMQDHHXOHXKMOPKTQPNXTPEUCHKSDTUXDQDMDSDHNDQDOKMDQDMKXKQEADWYPK "
            + "TPGNKAPRQXNXOTLWKQOCPEDKOTGXVVXTHPOHSPWHXXTLWXGPOHSXNXKQQXWAGXHN "
            + "DOGXHEDHHKRQXH");
        
        fa.f("CYNCWRBNQNGNYPQANYPJQRCRBNCRBNSFYLQCCWRBNQNIBPXBPQRBNYFRJSFKGFYF "
            + "YLIBPXBRBNQHPSPRIBCLNXPHBNSQRBNG");
    }

    private static class ComparatorImpl implements Comparator<Map.Entry<Character, Integer>> {

        public ComparatorImpl() {
        }

        @Override
        public int compare(Map.Entry<Character, Integer> o1,
            Map.Entry<Character, Integer> o2) {
            return (o1.getValue()).compareTo(o2.getValue());
        }
    }
}