package com.meal.recs.navigator;

/**
 * User: gardiary
 * Date: 13/06/19, 8:30
 */
public class Bootstrap4Navigator implements PageNavigator {

    @Override
    public String buildPageNav(String path, long recordCount, int page, int pageSize, int navTrail) {
        int allPage = (int) recordCount / pageSize;

        boolean isOdd = (recordCount % pageSize != 0);
        allPage = (isOdd ? allPage+1 : allPage);

        int iStart = page - navTrail;
        int iEnd = page + navTrail;

        if(iStart < 1) {
            iEnd = iEnd + (1-iStart);
            iStart = 1;

            if(iEnd > allPage) {
                iEnd = allPage;
            }
        } else if(iEnd > allPage) {
            iStart = iStart - (iEnd - allPage);
            iEnd = allPage;

            if(iStart < 1) {
                iStart = 1;
            }
        }

        StringBuffer sb = new StringBuffer();
        sb.append("<ul class=\"pagination\">");
        for(int i=iStart; i <=iEnd; i++) {
            if(i == page) {
                sb.append("<li class=\"page-item active disabled\"><a class=\"page-link\" href='#'>").append(i).append("</a></li>");
            } else {
                if(path.contains("?")) {
                    sb.append("<li class=\"page-item\"><a class=\"page-link\" href='").append(path).append("&p=").append(i).append("'>").append(i).append("</a></li>");
                } else {
                    sb.append("<li class=\"page-item\"><a class=\"page-link\" href='").append(path).append("?p=").append(i).append("'>").append(i).append("</a></li>");
                }
            }
        }
        sb.append("</ul>");

        return sb.toString();
    }

}
