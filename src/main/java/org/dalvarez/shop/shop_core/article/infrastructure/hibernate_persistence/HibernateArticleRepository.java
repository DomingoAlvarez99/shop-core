package org.dalvarez.shop.shop_core.article.infrastructure.hibernate_persistence;

import org.dalvarez.shop.shop_core.article.domain.Article;
import org.dalvarez.shop.shop_core.article.domain.ArticleRepository;
import org.dalvarez.shop.shop_common.persistence.domain.criteria.CountResult;
import org.dalvarez.shop.shop_common.persistence.domain.criteria.Criteria;
import org.dalvarez.shop.shop_common.persistence.domain.criteria.CriteriaConverter;
import org.dalvarez.shop.shop_common.persistence.domain.criteria.QueryResult;
import org.dalvarez.shop.shop_common.persistence.infrastructure.hibernate.HibernateRepository;

import javax.persistence.EntityManager;
import java.util.stream.Collectors;

public class HibernateArticleRepository extends HibernateRepository<ArticleEntity> implements ArticleRepository {

    public HibernateArticleRepository(final EntityManager entityManager,
                                      final CriteriaConverter<ArticleEntity> hibernateCriteriaConverter) {
        super(entityManager, hibernateCriteriaConverter, ArticleEntity.class);
    }

    @Override
    public Article getById(final Long id) {
        return findById(id).toArticle();
    }

    @Override
    public Article getByUuid(final String uuid) {
        return findByUuid(uuid).toArticle();
    }

    @Override
    public QueryResult<Article> getByCriteria(final Criteria criteria) {
        final QueryResult<ArticleEntity> result = findByCriteria(criteria);

        return new QueryResult<>(
                result.getTotalElements(),
                result.getFirstElement(),
                result.getResult()
                      .stream()
                      .map(ArticleEntity::toArticle)
                      .collect(Collectors.toList())
        );
    }

    @Override
    public CountResult countByCriteria(final Criteria criteria) {
        return super.countByCriteria(criteria);
    }

    @Override
    public Article create(final Article article) {
        return save(ArticleEntity.fromArticle(article))
                .toArticle();
    }

    @Override
    public Article update(final Article article) {
        return update(article.getId(), ArticleEntity.fromArticle(article))
                .toArticle();
    }

    @Override
    public void deleteById(final Long id) {
        removeById(id);
    }

    @Override
    public void deleteByCriteria(final Criteria criteria) {
        removeByCriteria(criteria);
    }

    @Override
    public Integer sumStockByCriteria(final Criteria criteria) {
        return sumByCriteria(
                criteria,
                ArticleEntity.FieldNames.STOCK,
                Integer.class
        );
    }

    @Override
    public Double sumPriceByCriteria(final Criteria criteria) {
        return sumByCriteria(
                criteria,
                ArticleEntity.FieldNames.PRICE,
                Double.class
        );
    }

}
