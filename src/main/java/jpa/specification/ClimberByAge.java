package jpa.specification;

import jpa.myentity.Climber;
import jpa.myentity.Climber_;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class ClimberByAge implements Specification<Climber>{

    private int fromAge;
    private int toAge;

    public ClimberByAge(int fromAge, int toAge) {
        this.fromAge = fromAge;
        this.toAge = toAge;
    }

    //Получение всех участников в возрасте [from; to)
    @Override
    public Predicate getPredicate(Root<Climber> climberRoot, CriteriaBuilder builder) {

        Predicate condition1 = builder.greaterThanOrEqualTo(climberRoot.get(Climber_.age), fromAge);
        Predicate condition2 = builder.lessThan(climberRoot.get(Climber_.age), toAge);

        return builder.and(condition1, condition2);
    }
}


