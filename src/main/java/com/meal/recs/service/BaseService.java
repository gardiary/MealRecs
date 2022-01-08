package com.meal.recs.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;

/**
 * User: gardiary
 * Date: 06/01/22, 09.02
 */
public abstract class BaseService {
    protected final Logger LOGGER = LoggerFactory.getLogger(getClass());
    public static final Sort NAME_ASC = Sort.by(Sort.Order.asc("name"));
    public static final Sort ID_ASC = Sort.by(Sort.Order.asc("id"));
    public static final Sort ID_DESC = Sort.by(Sort.Order.desc("id"));
    public static final Sort ITEM_NAME_ASC = Sort.by(Sort.Order.asc("item.name"));
}
