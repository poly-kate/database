package jpa.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import jpa.myentity.Group;
import jpa.myentity.Group_;
import jpa.myentity.Mountain;

public class GroupByMountain implements Specification<Group>{

    private Mountain mountain;

    public GroupByMountain(Mountain mountain) {
        this.mountain = mountain;
    }


    //Получение списка групп по горе
    @Override
    public Predicate getPredicate(Root<Group> groupRoot, CriteriaBuilder builder) {

        return builder.equal(groupRoot.get(Group_.mount), mountain);
    }
}


