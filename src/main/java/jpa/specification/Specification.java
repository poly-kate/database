package jpa.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public interface Specification<T> { // T - тип корневого объекта
    Predicate getPredicate(Root<T> tRoot, CriteriaBuilder builder);
}
