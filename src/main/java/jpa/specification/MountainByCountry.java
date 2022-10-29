package jpa.specification;

import jpa.myentity.Group;
import jpa.myentity.Group_;
import jpa.myentity.Mountain;
import jpa.myentity.Mountain_;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class MountainByCountry implements Specification<Mountain>{

    private String nameCountry;

    public MountainByCountry(String nameCountry) {
        this.nameCountry = nameCountry;
    }

    //получение горы по названию страны
    @Override
    public Predicate getPredicate(Root<Mountain> mountainRoot, CriteriaBuilder builder) {
        return builder.equal(mountainRoot.get(Mountain_.country), nameCountry);
    }
}

