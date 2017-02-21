package com.lyun.estate.core.supports.resolvers;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.common.base.Splitter;
import com.lyun.estate.core.repo.OffsetPageBounds;
import com.lyun.estate.core.utils.QueryStringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.format.Formatter;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by Jeffrey on 16/5/26.
 */
@ControllerAdvice
public class PageBoundsArgumentResolver implements HandlerMethodArgumentResolver, Formatter<PageBounds> {

    public static final String PAGE_HEADER = "X-PAGING";

    private static final int DEFAULT_LIMIT = 10;

    private static final int MAX_LIMIT = 10000;

    private static final String PAGE = "page";

    private static final String OFFSET = "offset";

    private static final String LIMIT = "limit";

    private static final String TOTAL = "total";

    private static final String ORDERS = "orders";

    private static final String ORDER_REGEX = "([a-zA-Z][_a-zA-Z0-9]*)(:(desc|asc|DESC|ASC))?";

    private static final PageBounds DEFAULT_PAGE_BOUNDS = new PageBounds(0, DEFAULT_LIMIT, false);

    private final Logger logger = LoggerFactory.getLogger(PageBoundsArgumentResolver.class);

    @Override
    public PageBounds parse(String text, Locale locale) throws ParseException {
        logger.debug("pagebound resolve:{}", text);
        if (!StringUtils.hasText(text)) {
            return DEFAULT_PAGE_BOUNDS;
        }

        MultiValueMap<String, String> map = QueryStringUtil.parse(text);
        String pageStr = map.getFirst(PAGE);
        String offsetStr = map.getFirst(OFFSET);
        String limitStr = map.getFirst(LIMIT);
        String orderStr = map.getFirst(ORDERS);
        String totalStr = map.getFirst(TOTAL);

        //parse count
        boolean containsTotalCount = Boolean.parseBoolean(totalStr);

        //parse Orders
        List<Order> orders = new ArrayList<>();
        if (!StringUtils.isEmpty(orderStr)) {
            Splitter.on(',').omitEmptyStrings().trimResults().splitToList(orderStr).forEach(orderArr -> {
                Pattern pattern = Pattern.compile(ORDER_REGEX);
                Matcher matcher = pattern.matcher(orderArr);
                if (matcher.matches()) {
                    String var = matcher.group(1);
                    String direction = matcher.group(3);
                    Order order = new Order(var, Order.Direction.fromString(direction), null);
                    orders.add(order);
                }
            });
        }


        int limit = DEFAULT_LIMIT;

        if (StringUtils.hasLength(limitStr)) {
            limit = Math.min(MAX_LIMIT, Integer.parseInt(limitStr));
        }

        if (StringUtils.hasLength(pageStr)) {
            return new PageBounds(Integer.parseInt(pageStr), limit, orders, containsTotalCount);
        }

        if (StringUtils.hasLength(offsetStr)) {
            return new OffsetPageBounds(Integer.parseInt(offsetStr), limit, orders, containsTotalCount);
        }

        return DEFAULT_PAGE_BOUNDS;
    }

    @Override
    public String print(PageBounds object, Locale locale) {
        return null;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return PageBounds.class.isAssignableFrom(parameter.getParameterType());

    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String text = webRequest.getHeader(PAGE_HEADER);
//        if (!StringUtils.hasText(text)) {
//            text = webRequest.getNativeRequest(HttpServletRequest.class).getQueryString();
//        }
        return parse(text, null);
    }
}
