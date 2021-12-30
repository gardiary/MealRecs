package com.meal.recs.navigator;

/**
 * User: gardiary
 * Date: 30/12/2021, 8:21
 */
public interface PageNavigator {
    String buildPageNav(String path, long recordCount, int page, int pageSize, int navTrail);
}
