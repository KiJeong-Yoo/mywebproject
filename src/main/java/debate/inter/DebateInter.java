package debate.inter;

import java.util.List;

import board.vo.BoardVO;
import board.vo.PageBoard;

public interface DebateInter {

	PageBoard list(int request_Page, int boardid);

	int readcountUpdate(int ridx);

	BoardVO select(int ridx, int boardid);

	int update(int parseInt, String title, String content);

	int delete(int parseInt, int boardid);

	int replyInsert(BoardVO board);

	PageBoard searchlist(String field, String search, int request_Page, int parseInt);

	int insert(BoardVO board);

	
}
