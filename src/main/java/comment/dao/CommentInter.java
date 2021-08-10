package comment.dao;

import java.util.List;

import comment.vo.CPageBoard;
import comment.vo.CommentVo;

public interface CommentInter {

	CPageBoard list(int requestPage, int pidx);

	List<CommentVo> clist(int pidx);

	int insert(CommentVo comment2);

	int replyInsert(CommentVo comment2);

	int delete(int groupid, int reorder);

	int update(int idx, String commentreply);

}
