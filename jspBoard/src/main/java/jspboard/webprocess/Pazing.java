package jspboard.webprocess;

import javax.servlet.http.HttpServletRequest;

public interface Pazing {
    int getPageSize();
    void setPageSize(int pageSize);
    int getShowPage();
    void setShowPage(int showPage);
    int getTotalPages();
    int getCurrPage();
    int getStartPage();
    int getEndPage();
    int getStartRow();
    int getEndRow();
    void calculatePaging(int totalRows, HttpServletRequest request);
}
