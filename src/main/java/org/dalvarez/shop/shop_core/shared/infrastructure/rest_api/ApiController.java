package org.dalvarez.shop.shop_core.shared.infrastructure.rest_api;

import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
public class ApiController {

    public static final String SEARCH_PATH = "/search";

    public static final String ID_PATH_VAR = "/{id}";

    public static final String UUID_PARAM = "uuid";

    public static final String SUM_PATH = "/sum";

    public static final String COUNT_PATH = "/count";

}