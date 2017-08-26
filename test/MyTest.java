import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangfugui on 2017/7/22.
 */
public class MyTest {
    public static void main(String[] args){
        List<Integer> list = new ArrayList<Integer>();
        for(int i=0;i<10;i++){
            list.add(i);
        }
        System.out.println(list);

        String s = list.toString();
        s = s.substring(1,s.length()-1);
        String[] strings = s.split(", ");
        for(String s1:strings){
            System.out.println(s1);
        }
    }
}
