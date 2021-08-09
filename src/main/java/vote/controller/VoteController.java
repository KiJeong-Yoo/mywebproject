package vote.controller;

import java.util.Random;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import vote.service.VoteService;
import vote.vo.PollItemVo;
import vote.vo.PollListVo;

@Controller
@RequestMapping("/vote/")
public class VoteController {
	
	@Autowired
	VoteService voteService;
	
	@RequestMapping("pollList")
	public ModelAndView main(String num) {
		int vnum = 0;
		ModelAndView mv = new ModelAndView();
		Vector<PollListVo> vlist = voteService.getAllList();
		
		if(num != null) {
			vnum = Integer.parseInt(num);
		}
		
		PollListVo plvo = new PollListVo();
		plvo = voteService.getPollRead(vnum);
		Vector<String> list = voteService.getItem(vnum);
		int voteCount = voteService.sumCount(vnum);
		
		mv.addObject("alllist", vlist);
		mv.addObject("plvo", plvo);
		mv.addObject("ilist", list);
		mv.addObject("voteCount", voteCount);
		mv.addObject("section", "/vote/pollList.jsp");
		mv.setViewName("/WEB-INF/index.jsp");
		return mv;
	}
	
	@RequestMapping("pollInsert")
	public ModelAndView insert() {
		ModelAndView mv = new ModelAndView();

		mv.addObject("section", "/vote/pollInsert.jsp");
		mv.setViewName("/WEB-INF/index.jsp");
		return mv;
	}
	
	@RequestMapping("pollInsertProc")
	public ModelAndView insertProc(String question, int type, String[] item, String sdate, String edate) {
		ModelAndView mv = new ModelAndView();
		PollItemVo pivo = new PollItemVo(item);
		PollListVo plvo = new PollListVo(question, type, sdate, edate);
//		pivo.setItem(item);
		boolean result = voteService.insertPoll(plvo, pivo);
				
		//String msg = "설문추가에 실패하였습니다.";
		String url = "/vote/pollInsert.jsp";
		
		if(result) {
			//msg = "설문 성공하였습니다.";
			url = "/vote/pollList.jsp";
			Vector<PollListVo> vlist = voteService.getAllList();
			
			mv.addObject("section", url);
			mv.addObject("alllist", vlist);
		} else {
			mv.addObject("section", url);
		}
		
		mv.setViewName("/WEB-INF/index.jsp");
		return mv;
	}
	
	@RequestMapping("pollFormProc")
	public ModelAndView formProc(String num, HttpServletRequest req) {
		ModelAndView mv = new ModelAndView();
		
		int count = Integer.parseInt(num);
		
		String itemnum[] = req.getParameterValues("itemnum"); 
//		boolean result = voteService.updatePoll(count, itemnum);
		voteService.updatePoll(count, itemnum);
//		String msg="투표가 등록되지 않았습니다.";
//		
//		 if(result) 
//			 msg = "투표하였습니다."; 
		 
		Vector<PollListVo> vlist = voteService.getAllList();
		PollListVo plvo = new PollListVo();
		plvo = voteService.getPollRead(count);
		
		Vector<String> list = voteService.getItem(count);
		int voteCount = voteService.sumCount(count);		
			 
		//mv.addObject("msg", msg);
		mv.addObject("alllist", vlist);
		mv.addObject("plvo", plvo);
		mv.addObject("slist", list);
		mv.addObject("voteCount", voteCount);
		mv.addObject("section", "/vote/pollList.jsp");
		mv.setViewName("/WEB-INF/index.jsp");
		 
		return mv;
	}
	
	@RequestMapping("pollview")
	public ModelAndView view(String num, String listnum) {
		ModelAndView mv = new ModelAndView();
		Random r = new Random();
		int view_num = Integer.parseInt(num);
		int list_num = Integer.parseInt(listnum);
		PollListVo plvo = voteService.getPollRead(list_num);
		PollItemVo pivo = new PollItemVo();
		int count = view_num;
		
		Vector<PollItemVo> vlist = voteService.getView(list_num);
		Vector<String> item = voteService.getItem(list_num);
		
		int sumCnt = voteService.sumCount(list_num);
		int maxCnt = voteService.getMaxcount(list_num);
		int ratio[] = new int[vlist.size()];

		for(int i=0; i<vlist.size(); i++) 
			ratio[i] = (int)(Math.round((double)vlist.get(i).getCount()/sumCnt*100));

		String rgb = "#"+ Integer.toHexString(r.nextInt(255*255*255));
				
		mv.addObject("plvo2", plvo);		
		mv.addObject("item2", item);
		mv.addObject("vlist2", vlist);
		mv.addObject("count2", count);
		mv.addObject("ratio2", ratio);
		mv.addObject("rgb2", rgb);
		mv.addObject("sumCnt2", sumCnt);
		mv.addObject("maxCnt2", maxCnt);
		mv.setViewName("/WEB-INF/vote/pollView.jsp");
		return mv;
	}
}
