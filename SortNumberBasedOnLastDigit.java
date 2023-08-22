import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/*sort them based on their last digit
* */
public class SortNumberBasedOnLastDigit {
    public static void main(String[] args) {
        ArrayList<Integer> al = new ArrayList<Integer>();
        al.add(205);
        al.add(102);
        al.add(98);
        al.add(275);
        al.add(203);

        System.out.println(Arrays.toString(al.toArray()));

//        Collections.sort(al,new Comparator<Integer>() {
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                return o1%10 > o2%10 ?1:-1;
//            }
//        });

        Collections.sort(al,(a,b)->{
            return (a%10) >(b%10)?1:-1;
        });

        System.out.println(Arrays.toString(al.toArray()));

    }
}
