package org.dalvarez.shop.core.article.infrastructure.rest_api.controller.get.count;

import org.dalvarez.shop.core.article.application.count.by_criteria.ArticleByCriteriaCounter;
import org.dalvarez.shop.core.article.infrastructure.rest_api.controller.ArticleApiController;
import org.dalvarez.shop.shared.persistence.application.criteria.CountResultResponse;
import org.dalvarez.shop.shared.persistence.domain.criteria.Criteria;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public final class ArticleGetCountByCriteriaController extends ArticleApiController {

    private final ArticleByCriteriaCounter articleByCriteriaCounter;

    public ArticleGetCountByCriteriaController(final ArticleByCriteriaCounter articleByCriteriaCounter) {
        this.articleByCriteriaCounter = articleByCriteriaCounter;
    }

    @GetMapping(COUNT_BY_CRITERIA_PATH)
    public ResponseEntity<CountResultResponse> count(@RequestParam(required = false) final String orderField,
                                                   @RequestParam(required = false) final String orderType,
                                                   @RequestParam(required = false) final String filtersBooleanOperator,
                                                   @RequestParam(required = false) final String filtersValues,
                                                   @RequestParam(required = false) final Long pageIndex,
                                                   @RequestParam(required = false) final Long pageSize) {
        final Criteria criteria = Criteria.fromQuery(
                orderField,
                orderType,
                filtersBooleanOperator,
                filtersValues,
                pageIndex,
                pageSize
        );

        return ResponseEntity.ok(articleByCriteriaCounter.count(criteria));
    }

}
