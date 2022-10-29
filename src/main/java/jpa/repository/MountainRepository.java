package jpa.repository;

import jpa.myentity.Mountain;
import jpa.specification.Specification;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class MountainRepository implements Repository<Mountain, Integer>{

    private EntityManager manager;

    public MountainRepository(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public void add(Mountain mountain) {
        manager.persist(mountain);
    }

    @Override
    public void update(Mountain mountain) {
        manager.merge(mountain);
    }

    @Override
    public Mountain getByPK(Integer integer) {
        return manager.find(Mountain.class, integer);
    }

    @Override
    public void delete(Mountain mountain) {
        if (mountain==null) throw new IllegalArgumentException();
        manager.remove(mountain);
    }

    @Override
    public void deleteByPK(Integer integer) {
        Mountain mountain = getByPK(integer);
        if (mountain != null) {
            delete(mountain);
        }
    }


    @Override
    public List<Mountain> getBySpecification(Specification<Mountain> specification) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Mountain> criteriaQuery = builder.createQuery(Mountain.class);
        Root<Mountain> root = criteriaQuery.from(Mountain.class);
        Predicate condition = specification.getPredicate(root, builder);
        criteriaQuery.where(condition);
        return manager.createQuery(criteriaQuery).getResultList();
    }

}
