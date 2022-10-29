package jpa.specification;

import jpa.myentity.Group;
import jpa.myentity.Group_;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class GroupByStatus implements Specification<Group>{

    private boolean status;

    public GroupByStatus(boolean status) {
        this.status = status;
    }

    //получение группы по статусу
    @Override
    public Predicate getPredicate(Root<Group> groupRoot, CriteriaBuilder builder) {
        return builder.equal(groupRoot.get(Group_.status), status);
    }
}




