package jpa.myentity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "my_group")
public class Group extends BaseIdentify{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false) //несколько групп на одной горе
    private Mountain mount;//гора

    //одна группа на несколько альпинистов
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "groupAndClimber",
            joinColumns = @JoinColumn(name = "groups_id"),
            inverseJoinColumns = @JoinColumn(name = "climbers_id"))
    private List<Climber> climbers = new ArrayList<>();//коллекция альпинистов

    @Column(nullable = false)
    private Boolean status;//набор в группу - идет или нет

    @Column(nullable = false)
    private LocalDate date; //дата восхождения

    @Column(nullable = false)
    private int duration; //продолжительность восхождения

    public Group(){
    }

    
    public Group(Mountain mount, Boolean status, LocalDate date, int duration) {

        setMount(mount);
        setStatus(status);
        setDate(date);
        setDuration(duration);
    }

   
    public void addClimber(Climber newClimber)
    {
        if(status==false || newClimber==null)
            throw new IllegalArgumentException("невозможно добавить альпиниста");
        else {
            climbers.add(newClimber);
        }
    }


    public void setMount(Mountain mount) {
        this.mount = mount;
    }

    public void setClimbers(List<Climber> climbers) {
        this.climbers = climbers;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Mountain getMount() {
        return mount;
    }

    public List<Climber> getClimbers() {
        return climbers;
    }

    public Boolean getSetFlag() {
        return status;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return "Group{" +
                "mount=" + mount +
                ", setFlag=" + status +
                ", date=" + date +
                ", duration=" + duration +
                '}';
    }

}
