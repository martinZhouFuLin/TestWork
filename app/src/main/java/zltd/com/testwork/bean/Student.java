package zltd.com.testwork.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by q on 2017/6/28.
 */

@DatabaseTable
public class Student extends BaseVO {
    @DatabaseField(id = true)
    private String name;
    @DatabaseField
    private String age;
    @DatabaseField
    private String weight;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
}
