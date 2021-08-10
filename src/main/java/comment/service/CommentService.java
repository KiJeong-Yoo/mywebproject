package comment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import comment.dao.CommentInter;
import comment.vo.CPageBoard;
import comment.vo.CommentVo;

@Service
public class CommentService {
	@Autowired
	CommentInter comment;

	public CPageBoard list(int requestPage, int pidx) {
		return comment.list(requestPage, pidx);
	}

	public List<CommentVo> clist(int pidx) {
		return comment.clist(pidx);
	}

	public int insert(CommentVo comment2) {
		return comment.insert(comment2);
	}

	public int replyInsert(CommentVo comment2) {
		return comment.replyInsert(comment2);
	}

	public int delete(int groupid, int reorder) {
		return comment.delete(groupid, reorder);
	}

	public int update(int idx, String commentreply) {
		return comment.update(idx, commentreply);
	}
}
