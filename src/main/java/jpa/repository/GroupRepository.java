package jpa.repository;

import jpa.myentity.Group;
//import jpa.myentity.Group_;
import jpa.specification.Specification;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.List;

public class GroupRepository implements Repository<Group, Integer>{

    private EntityManager manager;

    public GroupRepository(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public void add(Group group) {
        manager.persist(group);
    }

    @Override
    public void update(Group group) {
        manager.merge(group);
    }

    @Override
    public Group getByPK(Integer integer) {
        return manager.find(Group.class, integer);
    }

    @Override
    public void delete(Group group) {
        if (group==null) throw new IllegalArgumentException();
        manager.remove(group);
    }

    @Override
    public void deleteByPK(Integer integer) {
        Group group = getByPK(integer);
        if (group != null) {
            delete(group);
        }
    }


    @Override
    public List<Group> getBySpecification(Specification<Group> specification) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Group> criteriaQuery = builder.createQuery(Group.class);
        Root<Group> root = criteriaQuery.from(Group.class);
        Predicate condition = specification.getPredicate(root, builder);
        criteriaQuery.where(condition);
        return manager.createQuery(criteriaQuery).getResultList();
    }


}
