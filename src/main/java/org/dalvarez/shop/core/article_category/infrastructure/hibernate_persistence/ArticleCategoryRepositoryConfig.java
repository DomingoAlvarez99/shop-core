package org.dalvarez.shop.core.article_category.infrastructure.hibernate_persistence;

import org.dalvarez.shop.core.article_category.domain.ArticleCategoryRepository;
import org.dalvarez.shop.core.shared.domain.criteria.CriteriaConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
public class ArticleCategoryRepositoryConfig {

    @Bean
    public ArticleCategoryRepository articleCategoryRepository(final EntityManager entityManager,
                                                               final CriteriaConverter<ArticleCategoryEntity> hibernateCriteriaConverter) {
        return new HibernateArticleCategoryRepository(entityManager, hibernateCriteriaConverter);
    }

}

