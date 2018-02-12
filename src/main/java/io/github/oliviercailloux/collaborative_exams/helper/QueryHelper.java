package io.github.oliviercailloux.collaborative_exams.helper;

import io.github.oliviercailloux.collaborative_exams.model.entity.SameAbility;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.Question;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

@RequestScoped
public class QueryHelper {

	@PersistenceUnit
	private EntityManagerFactory emf;

	public <T> CriteriaQuery<T> selectAll(Class<T> type) {
		final CriteriaBuilder criteriaBuilder = emf.getCriteriaBuilder();
		final CriteriaQuery<T> query = criteriaBuilder.createQuery(type);
		final Root<T> from = query.from(type);
		query.select(from);
		return query;
	}


	public <T> CriteriaQuery<SameAbility> selectSameAbility(int question1, int question2 ,int author) {
		final CriteriaBuilder criteriaBuilder = emf.getCriteriaBuilder();
		final CriteriaQuery<SameAbility> query = criteriaBuilder.createQuery(SameAbility.class);
		final Root<SameAbility> from = query.from(SameAbility.class);
		query.select(from);
		ParameterExpression<Integer> idquestion1 = criteriaBuilder.parameter(Integer.class);

		query.where(
				criteriaBuilder.or(
						criteriaBuilder.equal(from.get("idQuestion1"), idquestion1)
				)
		);
		return query;
	}



}