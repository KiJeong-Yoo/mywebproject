package debate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import board.vo.BoardVO;
import board.vo.PageBoard;
import debate.inter.DebateInter;

@Service
public class DebateService {
	
	@Autowired
	DebateInter debate;
	
	public PageBoard list(int request_Page, int boardid) {
		return debate.list(request_Page, boardid);
	}

	public int readcountUpdate(int ridx) {
		return debate.readcountUpdate(ridx);
		
	}

	public BoardVO select(int ridx, int boardid) {
		return debate.select(ridx, boardid);
	}

	public int update(int parseInt, String title, String content) {
		return debate.update(parseInt, title, content);
	}

	public int delete(int parseInt, int boardid) {
		return debate.delete(parseInt, boardid);
	}

	public int replyInsert(BoardVO board) {
		return debate.replyInsert(board);
	}

	public PageBoard searchlist(String field, String search, int request_Page, int parseInt) {
		return debate.searchlist(field, search, request_Page, parseInt);
	}

	public int insert(BoardVO board) {
		return debate.insert(board);
	}
	
}
