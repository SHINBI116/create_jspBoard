package jspboard.process;

public class BoardAddGoodProcess extends EvaluationProcess {

    @Override
    protected String getSql() {
        return "UPDATE jspboard SET board_good_count = board_good_count + 1 WHERE board_id = ?";
    }

    @Override
    protected String getCookieNamePrefix() {
        return "good_voted_";
    }

    @Override
    protected String getMessage() {
        return "추천은 1일 1회만 가능합니다!";
    }
}
