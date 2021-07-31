package vote.dao;

import java.util.Vector;

import vote.vo.PollItemVo;
import vote.vo.PollListVo;

public interface PollInter {

	boolean insertPoll(PollListVo plvo, PollItemVo pivo);

	Vector<PollListVo> getAllList();

	Vector<String> getItem(int num);

	PollListVo getPollRead(int num);

	int sumCount(int vnum);

	boolean updatePoll(int count, String[] itemnum);

	Vector<PollItemVo> getView(int view_num);

	int getMaxcount(int view_num);

}
