package com.example.ch4labs.labs03.common;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.core.types.dsl.EntityPathBase;

public class QueryDslSortUtil {
    public static OrderSpecifier<?> toOrderSpecifier(EntityPathBase<?> pathBase, String sort) {
        if (sort == null || !sort.contains(",")) {
            return null;
        }

        String[] parts = sort.split(",");
        String field = parts[0].trim();
        Order order = Order.valueOf(parts[1].trim().toUpperCase());

        PathBuilder<?> pathBuilder = new PathBuilder<>(pathBase.getType(), pathBase.getMetadata());

        return new OrderSpecifier<>(order, pathBuilder.getComparable(field, Comparable.class));

    }
}
