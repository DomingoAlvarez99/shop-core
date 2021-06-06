package org.dalvarez.shop.core.article.application.erase;

import org.dalvarez.shop.core.article.domain.ArticleRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ArticleEraserConfig {

    @Bean
    public ArticleEraser articleEraser(final ArticleRepository articleRepository) {
        return new ArticleEraser(articleRepository);
    }

}
