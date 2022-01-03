package org.dalvarez.ddd_example.article.infrastructure.rest_api.put;

import org.dalvarez.ddd_example.article.application.ArticleRequest;
import org.dalvarez.ddd_example.article.application.ArticleResponse;
import org.dalvarez.ddd_example.article.domain.model.Article;
import org.dalvarez.ddd_example.article.domain.repository.ArticleRepository;
import org.dalvarez.ddd_example.article.infrastructure.ArticleInfrastructureRestApiModuleTestCase;
import org.dalvarez.ddd_example.shared.domain.IdMother;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

final class ArticlePutControllerShouldItTestCase extends ArticleInfrastructureRestApiModuleTestCase {

    private static final Integer STOCK = 100;

    private static final Double PRICE = 2.25;

    private static final String NAME = "Dark souls 3";

    private static final String DESCRIPTION = "Desc 1";


    ArticlePutControllerShouldItTestCase(@Autowired ArticleRepository repository) {
        super(repository);
    }

    @Test
    void shouldPutAnArticle() throws Exception {
        final Article article = data.get(1);

        final ArticleRequest request = ArticleRequest.of(
                STOCK,
                PRICE,
                NAME,
                DESCRIPTION,
                null
        );

        final ArticleResponse expected = shouldPut(request, ArticleResponse.class, article.id().value());

        final ArticleResponse actual = shouldgetById(expected.id(), ArticleResponse.class);

        assertEquals(expected.id(), actual.id());
    }

    @Test
    void shouldNotPutAnArticleCauseNotExist() throws Exception {
        final ArticleRequest request = ArticleRequest.of(
                STOCK,
                PRICE,
                NAME,
                DESCRIPTION,
                null
        );

        final String id = IdMother.randomGeneration(888);

        shouldNotPutCauseNotExist(request, id);
    }

}