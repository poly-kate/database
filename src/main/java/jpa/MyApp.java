package jpa;

import jpa.repository.*;
import jpa.myentity.*;
import jpa.specification.ClimberByAge;
import jpa.specification.GroupByMountain;
import jpa.specification.GroupByStatus;
import jpa.specification.MountainByCountry;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;


public class MyApp {
    public static void main(String[] args) {

        //Создаем альпинистов
        Climber masha = new Climber("Masha", "Saint-Petersburg", 22);
        Climber gleb = new Climber("Gleb", "Moscow", 40);
        Climber kirill = new Climber("Kirill", "Murmansk", 35);
        Climber anna = new Climber("Anna", "Novgorod", 27);
        Climber dasha = new Climber("Dasha", "Sochi", 31);
        Climber olya = new Climber("Olya", "Anapa", 36);

        //Создаем горы
        Mountain khibiny = new Mountain("khibiny", "Russia", 124);
        Mountain aneto = new Mountain("aneto", "Spain", 270);
        Mountain himalayas = new Mountain("himalayas", "China", 333);

        //создаем группы
        Group groupFirst = new Group(aneto, true, LocalDate.of(2021, Month.JANUARY,23), 20);
        Group groupSecond = new Group(himalayas, true, LocalDate.of(2021, Month.FEBRUARY,5), 12);
        Group groupThird = new Group(aneto, true, LocalDate.of(2021, Month.MAY,14), 5);
        Group groupFourth = new Group(khibiny, true, LocalDate.of(2021, Month.AUGUST,10), 15);

        //Добавляем альпинистов в группы
        groupFirst.addClimber(gleb);
        groupFirst.addClimber(anna);
        groupSecond.addClimber(dasha);
        groupThird.addClimber(masha);
        groupThird.addClimber(kirill);
        groupFourth.addClimber(olya);
        groupThird.setStatus(false);

        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("entityManager");
        EntityManager manager = factory.createEntityManager();

        //создаем прослойку
        GroupRepository groupRepository = new GroupRepository(manager);
        ClimberRepository climberRepository = new ClimberRepository(manager);
        MountainRepository mountainRepository = new MountainRepository(manager);

        manager.getTransaction().begin();

        climberRepository.add(masha);
        climberRepository.add(gleb);
        climberRepository.add(kirill);
        climberRepository.add(anna);
        climberRepository.add(dasha);
        climberRepository.add(olya);

        mountainRepository.add(khibiny);
        mountainRepository.add(aneto);
        mountainRepository.add(himalayas);

        groupRepository.add(groupFirst);
        groupRepository.add(groupSecond);
        groupRepository.add(groupThird);
        groupRepository.add(groupFourth);

        manager.getTransaction().commit();

        //Получение альпинистов по возрасту
        List<Climber> climbers = climberRepository.getBySpecification(new ClimberByAge(20,35));
        System.out.println("\nСписок альпинистов по возрасту:");
        for(Climber cl: climbers){
            System.out.println(cl.getName());
        }

        //получение списка групп по горе
        List<Group> groups = groupRepository.getBySpecification(new GroupByMountain(aneto));
        System.out.println("\nСписок групп по названию горы:");
        for(Group g: groups){
            System.out.println(g.getId());
        }

        //получение списка гор по названию страны
        List<Mountain> mountains = mountainRepository.getBySpecification(new MountainByCountry("Russia"));
        System.out.println("\nСписок гор по названию страны:");
        for(Mountain m: mountains){
            System.out.println(m.getName());
        }

        //получение списка групп, набор в которые еще открыт
        List<Group> groupsOpen = groupRepository.getBySpecification(new GroupByStatus(true));
        System.out.println("\nСписок групп с открытым набором:");
        for(Group g: groupsOpen){
            System.out.println(g.getId());
        }
    }
}
