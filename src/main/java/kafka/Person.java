package kafka;

/**
 * Copyright @ 2018 lihao.com
 * All right reserved.
 *
 * @author Li Hao
 * @since 2018/11/11  11:44
 */
public class Person {
    private Integer id;
    private String name;
    private String sex;

    public Person(Integer id, String name,String sex) {
        this.id = id;
        this.name = name;
        this.sex = sex;
    }
    public Integer getId() {
        return id;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setId(Integer id) {
        this.id = id;

    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
