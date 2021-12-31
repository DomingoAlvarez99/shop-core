package org.dalvarez.shop.shop_core.article.infrastructure.hibernate_persistence;

import org.dalvarez.shop.shop_core.shop_common.persistence.domain.criteria.CriteriaConverter;
import org.dalvarez.shop.shop_core.article.domain.model.Article;
import org.dalvarez.shop.shop_core.article.domain.port.ArticleRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
public class ArticleRepositoryConfig {

    @Bean
    public ArticleRepository articleRepository(final EntityManager entityManager,
                                               final CriteriaConverter<Article> hibernateCriteriaConverter) {
        return new HibernateArticleRepository(entityManager, hibernateCriteriaConverter);
    }

}

