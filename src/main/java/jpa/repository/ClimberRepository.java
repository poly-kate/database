package jpa.repository;

import jpa.myentity.Climber;
import jpa.specification.Specification;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class ClimberRepository implements Repository<Climber, Integer>{

    private EntityManager manager;

    public ClimberRepository(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public void add(Climber climber) {
        manager.persist(climber);
    }

    @Override
    public void update(Climber climber) {
        manager.merge(climber);
    }

    @Override
    public Climber getByPK(Integer integer) {
        return manager.find(Climber.class, integer);
    }

    @Override
    public void delete(Climber climber) {
        if (climber==null) throw new IllegalArgumentException();
        manager.remove(climber);
    }

    @Override
    public void deleteByPK(Integer integer) {
        Climber climber = getByPK(integer);
        if (climber != null) {
            delete(climber);
        }
    }


    @Override
    public List<Climber> getBySpecification(Specification<Climber> specification) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Climber> criteriaQuery = builder.createQuery(Climber.class);
        Root<Climber> root = criteriaQuery.from(Climber.class);
        Predicate condition = specification.getPredicate(root, builder);
        criteriaQuery.where(condition);
        return manager.createQuery(criteriaQuery).getResultList();
    }

}
