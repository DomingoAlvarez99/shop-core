package org.dalvarez.shop.core.article.infrastructure.rest_api.controller.get.sum.by_stock;

import org.dalvarez.shop.core.article.application.sum.by_stock.ArticleByStockAdder;
import org.dalvarez.shop.core.article.infrastructure.rest_api.controller.ArticleApiController;
import org.dalvarez.shop.core.shared.application.sum.SumResponse;
import org.dalvarez.shop.core.shared.domain.criteria.Criteria;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public final class ArticleGetStockSumByCriteriaController extends ArticleApiController {

    private final ArticleByStockAdder articleByStockAdder;

    public ArticleGetStockSumByCriteriaController(final ArticleByStockAdder articleByStockAdder) {
        this.articleByStockAdder = articleByStockAdder;
    }

    @GetMapping(STOCK_SUM_BY_CRITERIA_PATH)
    public ResponseEntity<SumResponse<Integer>> get(@RequestParam(required = false) final String orderField,
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

        return ResponseEntity.ok(articleByStockAdder.sum(criteria));
    }

}