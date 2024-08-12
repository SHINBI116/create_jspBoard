package jspboard.webprocess;

import javax.servlet.http.HttpServletRequest;

public class PazingImpl implements Pazing {

    private int pageSize;
    private int showPage;
    private int totalRows;
    private int totalPages;
    private int currPage;
    private int startPage;
    private int endPage;
    private int startRow;
    private int endRow;

    public PazingImpl() {
        this.pageSize = 10; // 기본값
        this.showPage = 10; // 기본값
    }

    @Override
    public int getPageSize() {
        return pageSize;
    }

    @Override
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public int getShowPage() {
        return showPage;
    }

    @Override
    public void setShowPage(int showPage) {
        this.showPage = showPage;
    }

    @Override
    public int getTotalPages() {
        return totalPages;
    }

    @Override
    public int getCurrPage() {
        return currPage;
    }

    @Override
    public int getStartPage() {
        return startPage;
    }

    @Override
    public int getEndPage() {
        return endPage;
    }

    @Override
    public int getStartRow() {
        return startRow;
    }

    @Override
    public int getEndRow() {
        return endRow;
    }

    @Override
    public void calculatePaging(int totalRows, HttpServletRequest request) {
        this.totalRows = totalRows;
        this.currPage = getCurrentPage(request);
        this.totalPages = (int) Math.ceil((double) totalRows / pageSize);
        this.startRow = (currPage - 1) * pageSize + 1;
        this.endRow = Math.min(currPage * pageSize, totalRows);

        int groupNumber = (currPage - 1) / showPage;
        this.startPage = groupNumber * showPage + 1;
        this.endPage = Math.min(startPage + showPage - 1, totalPages);
    }

    private int getCurrentPage(HttpServletRequest request) {
        String page = request.getParameter("page");
        if (page == null) {
            return 1;
        } else {
            return Integer.parseInt(page);
        }
    }
}
