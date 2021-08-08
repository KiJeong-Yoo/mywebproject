package image.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import image.service.ImageService;
import image.vo.ImageVo;
import image.vo.PageBoard;


@Controller
@RequestMapping("/photoboard/")
public class ImageController {

	@Autowired
	ImageService imageService;
	int request_page = 1;
	
	
//	private final String UPLOAD_PATH = "/Users/yjw8459/git/mywebproject/src/main/webapp/attachment";
	private final String UPLOAD_PATH = "C:/Users/syace/git/mywebproject/src/main/webapp/attachment";
	private final String LOAD_PATH = "/attachment";
	
	//메인화면
	@RequestMapping("photomain")
	public ModelAndView main(HttpServletRequest req, String requestPage) {
		HttpSession session = req.getSession();
		session.setAttribute("uploadPath", req.getRealPath("/"));
		ModelAndView mv = new ModelAndView();

		if(requestPage != null)
			request_page = Integer.parseInt(requestPage);
		
		mv.addObject("pageboard", imageService.list(request_page));
		mv.addObject("section", "/photoboard/photomain.jsp");
		mv.setViewName("/WEB-INF/index.jsp");
		return mv;
	}
	
	//글 작성
	@RequestMapping("write")
	public ModelAndView write() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("section", "/photoboard/write.jsp");
		mv.setViewName("/WEB-INF/index.jsp");
		return mv;
	}
	
	//작성 액션
	@RequestMapping("write.do")
	public ModelAndView writeProc(HttpServletRequest request, MultipartFile upload, String title, String writeId, String requestPage) {
		ModelAndView mv = new ModelAndView();
		//파일명
        String originalFile = upload.getOriginalFilename();
        //파일명 중 확장자만 추출                                                //lastIndexOf(".") - 뒤에 있는 . 의 index번호
        String originalFileExtension = originalFile.substring(originalFile.lastIndexOf("."));
        //fileuploadtest.doc
        //lastIndexOf(".") = 14(index는 0번부터)
        //substring(14) = .doc
        
        //업무에서 사용하는 리눅스, UNIX는 한글지원이 안 되는 운영체제 
        //파일업로드시 파일명은 ASCII코드로 저장되므로, 한글명으로 저장 필요
        //UUID클래스 - (특수문자를 포함한)문자를 랜덤으로 생성                    "-"라면 생략으로 대체
        String storedFileName = UUID.randomUUID().toString().replaceAll("-", "") + originalFileExtension;
        
        //파일을 저장하기 위한 파일 객체 생성
        File file = new File(UPLOAD_PATH, storedFileName);
        //파일 저장
        try {
			upload.transferTo(file);
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        String img = LOAD_PATH + "/" + storedFileName; 
        int result = imageService.insert(title, writeId, img);
        
        System.out.println(writeId + "가 업로드한 파일은");
        System.out.println(originalFile + "은 업로드한 파일이다.");
        System.out.println(storedFileName + "라는 이름으로 업로드 됐다.");
        System.out.println("파일사이즈는 " + upload.getSize());

		if(requestPage != null)
			request_page = Integer.parseInt(requestPage);
        
        PageBoard pageboard = imageService.list(request_page); 
        
        mv.addObject("requestpage", request_page);
		mv.addObject("pageboard", pageboard);
        mv.addObject("imgpath", img);
		mv.addObject("section", "/photoboard/photomain.jsp");
		mv.setViewName("/WEB-INF/index.jsp");
		return mv;
	}
	
	//포토 게시판 메인에서 하나 선택했을 때 화면
	@RequestMapping("view")
	public ModelAndView view(String idx) {
		ModelAndView mv = new ModelAndView();
		imageService.readcountUpdate(Integer.parseInt(idx)); // 조회수 증가
		
		ImageVo image = imageService.select(Integer.parseInt(idx));
		
		mv.addObject("image", image);
		mv.addObject("section", "/photoboard/view.jsp");
		mv.setViewName("/WEB-INF/index.jsp");		
		return mv;
	}
	
	//수정
	@RequestMapping("update")
	public ModelAndView update(String title, String content, String idx) {
		ModelAndView mv = new ModelAndView();
		ImageVo image = imageService.select(Integer.parseInt(idx));

		mv.addObject("iamge", image);
		mv.addObject("section", "/photoboard/update.jsp");
		mv.setViewName("/WEB-INF/index.jsp");
		return mv;
	}
	
	@RequestMapping("update.do")
	public ModelAndView updateProc(HttpServletRequest request, MultipartFile upload, String title, String idx) {
		ModelAndView mv = new ModelAndView();
		//파일명
        String originalFile = upload.getOriginalFilename();
        //파일명 중 확장자만 추출                                                //lastIndexOf(".") - 뒤에 있는 . 의 index번호
        String originalFileExtension = originalFile.substring(originalFile.lastIndexOf("."));

        String storedFileName = UUID.randomUUID().toString().replaceAll("-", "") + originalFileExtension;
        
        //파일을 저장하기 위한 파일 객체 생성
        File file = new File(UPLOAD_PATH, storedFileName);
        //파일 저장
        try {
			upload.transferTo(file);
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        String img = LOAD_PATH + "/" + storedFileName;
        System.out.println(title);
        System.out.println(img);
        imageService.update(title, img, Integer.parseInt(idx));		
		ImageVo image = imageService.select(Integer.parseInt(idx));
		
		mv.addObject("iamge", image);
		mv.addObject("section", "/photoboard/view.jsp");
		mv.setViewName("/WEB-INF/index.jsp");
		return mv;
	}
	
	//삭제
	@RequestMapping("delete")
	public ModelAndView delete(String idx, String requestPage) {
		ModelAndView mv = new ModelAndView();
		
		int result = imageService.delete(Integer.parseInt(idx));
		System.out.println(result);
		PageBoard pageboard = imageService.list(Integer.parseInt(requestPage)); 
		
		if(requestPage != null)
			request_page = Integer.parseInt(requestPage);
		
		mv.addObject("requestpage", request_page);
		mv.addObject("pageboard", pageboard);
		mv.addObject("section", "/photoboard/photomain.jsp");
		mv.setViewName("/WEB-INF/index.jsp");
		return mv;
	}
	
	@RequestMapping("search")
	public ModelAndView search(String search, String requestPage) {
		ModelAndView mv = new ModelAndView();
		
		int request_page = 1;
		if(requestPage != null)
			request_page = Integer.parseInt(requestPage);
		
		PageBoard pageboard = imageService.searchlist(request_page, search); 
		
		mv.addObject("requestpage", request_page);
		mv.addObject("pageboard", pageboard);
		mv.addObject("section", "/photoboard/photomain.jsp");
		mv.setViewName("/WEB-INF/index.jsp");
		return mv;
	}

}