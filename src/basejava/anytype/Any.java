package basejava.anytype;

/**
 * @author zzx
 * @version 1.0
 * @date 2021/3/11 10:56
 */
public class Any {
    @AnyTag(value = 12,ienum = AnyEnum.TWO,haha = @AnyTag2)
    private int property;

    public int getProperty() {
        return property;
    }

    public void setProperty(int property) {
        this.property = property;
    }
}
