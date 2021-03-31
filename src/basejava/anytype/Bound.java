package basejava.anytype;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zzx
 * @version 1.0
 * @date 2021/3/20 8:38
 */

interface SuperPower {}

interface XRayVision extends SuperPower {
    void seeThrouhWalls();
}

interface SuperHearing extends SuperPower {
    void hearSubtleNoise();
}

interface SuperSmell extends SuperPower {
    void trackBySmell();
}


class SuperHero<T extends SuperPower> {
    protected T power;

    public SuperHero(T power) {
        this.power = power;
    }

    public T getPower() {
        return power;
    }
}//要操作的类型

class SuperSleuth<T extends XRayVision> extends SuperHero<T>{
    public SuperSleuth(T power) {
        super(power);
    }
    void see(){
        power.seeThrouhWalls();
    }
}//要操作类型边界扩展到XRayVision

class CanineHero<T extends SuperHearing & SuperSmell> extends SuperHero<T>{
    public CanineHero(T power) {
        super(power);
    }
    void hear(){
        power.hearSubtleNoise();
    }
    void smell(){
        power.trackBySmell();
    }
}//要操作类型边界扩展到SuperHearing和SuperSmell


class SuperHearSmell implements SuperHearing , SuperSmell{
    @Override
    public void hearSubtleNoise() {

    }

    @Override
    public void trackBySmell() {

    }
}//实现SuperHearing和SuperSmell的类
class SuperVisionHearSmell implements XRayVision ,SuperHearing , SuperSmell{
    @Override
    public void hearSubtleNoise() {

    }

    @Override
    public void trackBySmell() {

    }

    @Override
    public void seeThrouhWalls() {

    }
}//实现XRayVision、SuperHearing和SuperSmell的类

class SuperHear implements SuperHearing{
    @Override
    public void hearSubtleNoise() {

    }
}//实现SuperHearing的类

class DogBoy extends CanineHero<SuperHearSmell>{
    public DogBoy(SuperHearSmell power) {
        super(power);
    }
}//继承指定操作SuperHearSmell类的类

class EricBattle{
    //一个泛型方法，他的输入参数是要操作继承了SuperHearing类的SuperHero类；
    static <T extends SuperHearing> void useSuperHear(SuperHero<T> hero){
        hero.power.hearSubtleNoise();
    }
    //泛型方法，他的输出参数是要操作继承SuperHearing和SuperSmell的类的SuperHero类
    static <T extends SuperHearing&SuperSmell> void useSuperHearSmell(SuperHero<T> hero){
            hero.power.hearSubtleNoise();
            hero.power.trackBySmell();
    }
}



public class Bound {

    public static void main(String[] args) {
        DogBoy boy=new DogBoy(new SuperHearSmell());
        EricBattle.useSuperHear(boy);
        EricBattle.useSuperHearSmell(boy);
        List<? extends SuperHearing> list=new ArrayList<>();
        SuperHearing superHearing= list.get(0);
//        list.add(0,superHearing);
        //List<? extends SuperHearing & SuperSmell>
//        list.add(new SuperHear());

    }

}
