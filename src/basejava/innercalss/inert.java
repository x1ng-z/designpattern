package basejava.innercalss;

/**
 * @author zzx
 * @version 1.0
 * @date 2021/3/20 9:11
 */


class parent{
    protected void test(){
        System.out.println(this.getClass()+"");
    }

    private void ptest(){
        System.out.println(this.getClass()+"");
    }
}


public class inert extends parent{
    private void ptest(){
        System.out.println(this.getClass()+"");
    }

    @Override
    protected void test() {
        System.out.println(this.getClass()+"");
    }

    public static void main(String[] args) {
        parent p=(parent)new inert();
        p.test();
    }
}
