package vote.service;

import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vote.dao.PollInter;
import vote.vo.PollItemVo;
import vote.vo.PollListVo;

@Service
public class VoteService {
	@Autowired
	PollInter poll;

	public boolean insertPoll(PollListVo plvo, PollItemVo pivo) {
		return poll.insertPoll(plvo, pivo);
	}

	public Vector<PollListVo> getAllList() {
		return poll.getAllList();
	}

	public Vector<String> getItem(int num) {
		return poll.getItem(num);
	}

	public PollListVo getPollRead(int num) {
		return poll.getPollRead(num);
	}

	public int sumCount(int vnum) {
		return poll.sumCount(vnum);
	}

	public boolean updatePoll(int count, String[] itemnum) {
		return poll.updatePoll(count, itemnum);
	}
	
	
}
