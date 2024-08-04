package jspboard.process;

public class BoardAddBadProcess extends EvaluationProcess {

    @Override
    protected String getSql() {
        return "UPDATE jspboard SET board_bad_count = board_bad_count + 1 WHERE board_id = ?";
    }

    @Override
    protected String getCookieNamePrefix() {
        return "bad_voted_";
    }

    @Override
    protected String getMessage() {
        return "비추천은 1일 1회만 가능합니다!";
    }
}
