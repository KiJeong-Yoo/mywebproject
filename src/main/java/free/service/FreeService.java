package free.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import board.vo.BoardVO;
import board.vo.PageBoard;
import free.inter.FreeInter;

@Service
public class FreeService {
	@Autowired
	FreeInter free;
	
	public PageBoard list(int request_Page, int boardid) {
		return free.list(request_Page, boardid);
	}

	public int readcountUpdate(int ridx) {
		return free.readcountUpdate(ridx);
		
	}

	public BoardVO select(int ridx, int boardid) {
		return free.select(ridx, boardid);
	}

	public int update(int parseInt, String title, String content) {
		return free.update(parseInt, title, content);
	}

	public int delete(int parseInt, int boardid) {
		return free.delete(parseInt, boardid);
	}

	public int replyInsert(BoardVO board) {
		return free.replyInsert(board);
	}

	public PageBoard searchlist(String field, String search, int request_Page, int parseInt) {
		return free.searchlist(field, search, request_Page, parseInt);
	}

	public int insert(BoardVO board) {
		return free.insert(board);
	}

	public PageBoard mainpagelist(int request_Page, int boardid) {
		return free.mainpagelist(request_Page, boardid);
	}
}
