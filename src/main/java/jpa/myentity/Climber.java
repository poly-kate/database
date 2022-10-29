package jpa.myentity;

import javax.persistence.*;


@Entity
public class Climber extends BaseIdentify{

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private int age;


    public Climber() {
    }

    public Climber(String name, String address, int age)
    {
        setName(name);
        setAddress(address);
        setAge(age);
    }


    public void setName(String name) {
        if (name == null || name.trim().length() < 3)
            throw new IllegalArgumentException("name должен быть не меньше 3");
        this.name = name;
    }

    public void setAddress(String address) {
        if (address == null || address.trim().length() < 5)
            throw new IllegalArgumentException("address должен быть не меньше 5");
        this.address = address;
    }

    public void setAge(int age)
    {
        //проверка возраста
        if (age < 18)
            throw new IllegalArgumentException("age должен быть не меньше 18 лет");
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getAge() {
        return age;
    }


    @Override
    public String toString() {
        return "Climber{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", age=" + age +
                '}';
    }
}
