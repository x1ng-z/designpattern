package basejava.innercalss;

/**
 * @author zzx
 * @version 1.0
 * @date 2021/3/6 9:10
 */
public class outerclass {

    protected class inerclass{
        private ThreadLocal<outerclass> threadLocal=new ThreadLocal<outerclass>(){
            @Override
            protected outerclass initialValue() {
                return new outerclass();
            }
        };
        private String name;
        private int age;

        private int  next(){
            age=age+1;
            return age;
        }
        public outerclass  getouter(){
            return outerclass.this;
        }
    }


    public class inerclass1{
        private String name;
        private int age;

        private int  next(){
            age=age+1;
            return age;
        }
        public outerclass  getouter(){
            return outerclass.this;
        }
    }

    public inerclass newinerclass(){
        return new inerclass();
    }



}



