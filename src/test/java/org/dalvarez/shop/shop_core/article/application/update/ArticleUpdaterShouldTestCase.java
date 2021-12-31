package org.dalvarez.shop.shop_core.article.application.update;

import org.dalvarez.shop.shop_common.shared.domain.value_object.id.Identifier;
import org.dalvarez.shop.shop_core.article.application.ArticleApplicationModuleTestCase;
import org.dalvarez.shop.shop_core.article.application.ArticleRequest;
import org.dalvarez.shop.shop_core.article.domain.model.Article;
import org.dalvarez.shop.shop_core.article.domain.ArticleMother;
import org.dalvarez.shop.shop_core.category.domain.port.CategoryRepository;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public final class ArticleUpdaterShouldTestCase extends ArticleApplicationModuleTestCase {

    private final ArticleUpdater articleUpdater;

    private final CategoryRepository categoryRepository;

    public ArticleUpdaterShouldTestCase() {
        categoryRepository = mock(CategoryRepository.class);
        articleUpdater = new ArticleUpdater(repository, categoryRepository);
    }

    @Test
    public void updateAnArticle() {
        final String id = Identifier.random()
                                .value();
        final Article random = ArticleMother.random(id);

        final ArticleRequest randomRequest = ArticleRequest.of(random.id()
                                                                     .value(),
                                                               random.stock()
                                                                     .value(),
                                                               random.price()
                                                                     .value(),
                                                               random.name()
                                                                     .value(),
                                                               random.description()
                                                                     .value(),
                                                               null
        );

        when(repository.getById(any())).thenReturn(random);

        when(repository.update(any())).thenReturn(random);

        articleUpdater.update(id, randomRequest);

        shouldHaveUpdated(random);
    }

}
