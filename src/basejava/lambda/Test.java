package basejava.lambda;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * @author zzx
 * @version 1.0
 * @date 2021/4/1 10:32
 */

public class Test {


    public static void main(String[] args) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar ca = Calendar.getInstance();
        ca.setFirstDayOfWeek(Calendar.MONDAY);
        ca.set(Calendar.DAY_OF_WEEK,Calendar.SUNDAY);
        ca.set(ca.get(Calendar.YEAR),
                ca.get(Calendar.MONTH),
                ca.get(Calendar.DATE),23,59,59);
        String end = sdf.format(ca.getTime());
        ca.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        ca.set(Calendar.HOUR_OF_DAY,0);
        ca.set(Calendar.MINUTE, 0);
        ca.set(Calendar.SECOND,0);
        String start = sdf.format(ca.getTime());
        System.out.println(start+"|"+end);



        int a=Stream.of(1, 2, 4, 5).collect(Collectors.reducing(0, (x, y) -> x + y));

        //9、 /*stream是链式的，这些功能是可以一起使用的，例如：计算每个年级每个班的及格人数*/
        //List<TestStreamModel> list = null;
        //list.stream().filter(t -> t.getClasses() != 0).collect(toList()).stream().collect(Collectors.toList());

        //Map<Integer/*年级*/, Map<Integer/*班级*/, Long/*人数*/>> integerMap = list.stream().filter(t -> t.getScore() >= 60).collect(Collectors.groupingBy(t -> t.getGrade(), Collectors.groupingBy(t -> t.getClasses(), Collectors.counting())));


        /*使用Collectors.toMap形式*/
        User user1=new User();
        user1.setAge(1);
        user1.setName("user1");
        User user2=new User();
        user2.setAge(1);
        user2.setName("user2");


        Map<Integer,String> result = Arrays.asList(user1,user2).stream().collect(Collectors.toMap(User::getAge, User::getName, (oldvalue, newvalue) -> {System.out.println(oldvalue);System.out.println(newvalue);return  newvalue;}));
//其中Collectors.toMap方法的第三个参数为键值重复处理策略，如果不传入第三个参数，当有相同的键时，会抛出一个IlleageStateException。
////或者
        //Map<Integer, String> result1 = Arrays.asList(user1,user2).stream().collect(Collectors.toMap(User::getAge, User::getName));
//List<People> -> Map<String,Object>
        List<User> peopleList = new ArrayList<>();
        peopleList.add(new User());
        peopleList.add(new User());
        Map result2 =  Arrays.asList(user1,user2).stream().collect(HashMap::new, (map, p) -> map.put(p.getAge(),p.getName()), Map::putAll);


        IFuntionSum<Long> function = lista -> {
            Long sum = 0L;
            for (Long i : lista) {
                sum += i;
            }
            return sum;
        };

        function.sum(Arrays.asList(1L, 2L));

    }

}
