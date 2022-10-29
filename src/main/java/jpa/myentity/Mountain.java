package jpa.myentity;



import javax.persistence.*;

@Entity
public class Mountain extends BaseIdentify{

    @Column(nullable = false)
    private String name;//название

    @Column(nullable = false)
    private String country; //страна

    @Column(nullable = false)
    private int height; //высота

    public Mountain(){
    }

    public Mountain(String name, String country, int height){
        setName(name);
        setCountry(country);
        setHeight(height);
    }

    public void setName(String name){
        if(name==null || name.trim().length()<4)
            throw new IllegalArgumentException("name должен быть не менее 4");
        this.name=name;
    }

    public void setCountry(String country){
        if(country==null || country.trim().length()<4)
            throw new IllegalArgumentException("country должен быть не менее 4");
        this.country=country;
    }

    public void setHeight(int height){
        if(height<100)
            throw new IllegalArgumentException("height должен быть не менее 100");
        this.height=height;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return "Mountain{" +
                "name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", height=" + height +
                '}';
    }
}