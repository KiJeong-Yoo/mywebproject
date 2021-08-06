package image.controller;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import image.dao.ImageDao;
import image.service.ImageService;
import image.vo.ImageVo;
import image.vo.PageBoard;


@Controller
@RequestMapping("/photoboard/")
public class ImageController {

	@Autowired
	ImageService imageService;
	
	
	//메인화면
	@RequestMapping("photomain")
	public ModelAndView main(HttpServletRequest req, String requestPage) {
		ModelAndView mv = new ModelAndView();
		
		int request_page = 1;
		if(requestPage != null)
			request_page = Integer.parseInt(requestPage);
		
		PageBoard pageboard = imageService.list(request_page); 
		System.out.println(pageboard.getList().get(2));
		mv.addObject("pageboard", pageboard);
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
	public ModelAndView writeProc(MultipartFile upload, String title, String writeId, String requestPage) {
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
        String filePath = "C:\\Users\\admin\\eclipse-workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp1\\wtpwebapps\\football\\pds\\imageFile";
        //파일을 저장하기 위한 파일 객체 생성
        File file = new File(filePath + storedFileName);
        //파일 저장
        try {
			upload.transferTo(file);
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        String img = filePath + storedFileName; 
        int result = imageService.insert(title, writeId, img);
        
        System.out.println(writeId + "가 업로드한 파일은");
        System.out.println(originalFile + "은 업로드한 파일이다.");
        System.out.println(storedFileName + "라는 이름으로 업로드 됐다.");
        System.out.println("파일사이즈는 " + upload.getSize());
        
        int request_page = 1;
		if(requestPage != null)
			request_page = Integer.parseInt(requestPage);
        
        PageBoard pageboard = imageService.list(request_page); 
        
		mv.addObject("list", pageboard);
        mv.addObject("imgpath", img);
		mv.addObject("section", "/photoboard/photomain.jsp"); // view로 보낼가?
		mv.setViewName("/WEB-INF/index.jsp");
		return mv;
	}
	
	//포토 게시판 메인에서 하나 선택했을 때 화면
	@RequestMapping("view")
	public ModelAndView view(String idx) {
		ModelAndView mv = new ModelAndView();
		imageService.readcountUpdate(Integer.parseInt(idx)); // 조회수 증가
		
		ImageVo image = imageService.select(Integer.parseInt(idx));
		
		mv.addObject("iamge", image);
		mv.addObject("section", "/photoboard/view.jsp");
		mv.setViewName("/WEB-INF/index.jsp");		
		return mv;
	}
	
	//수정
	@RequestMapping("update")
	public ModelAndView update(String title, String content, String idx) {
		ModelAndView mv = new ModelAndView();
		
		int result = imageService.update(title, content, Integer.parseInt(idx));
		
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
		
		PageBoard pageboard = imageService.list(Integer.parseInt(requestPage));  
		mv.addObject("list", pageboard);
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
		
		PageBoard pageboard = imageService.list(request_page); 
		mv.addObject("list", pageboard);
		mv.addObject("section", "/photoboard/photomain.jsp");
		mv.setViewName("/WEB-INF/index.jsp");
		return mv;
	}
	
//	@RequestMapping("img")
	
	
//	@RequestMapping(value="/common/getImg.do" , method=RequestMethod.GET)
//	  public void getImg( 
//	    @RequestParam(value="fileNM") String fileNM,
//	    HttpServletResponse response) throws Exception{
//		Properties globalProperties = new Properties();
//		globalProperties.load(new FileReader("C:\\Users\\admin\\git\\mywebproject\\src\\main\\resources\\globals.properties"));
//	    String DIR = globalProperties.getProperty("Globals.dir");
//	    String filePath = DIR+fileNM;
//
//	  getImage(filePath,response);
//	}
//	
//	public void getImage(String filePath, HttpServletResponse response) throws Exception{
//		
//		File file = new File(filePath);
//		if(!file.isFile()){
//			response.setContentType("text/html; charset=UTF-8");
//			PrintWriter out = response.getWriter();
//			out.write("<script type='text/javascript'>alert('조회된 정보가 없습니다.'); self.close();</script>");
//			out.flush();
//			return;
//		}
//		
//		FileInputStream fis = new FileInputStream(file);
//		
//		BufferedInputStream in = null;
//		ByteArrayOutputStream bStream = null;
//		try {
//			fis = new FileInputStream(file);
//			in = new BufferedInputStream(fis);
//			bStream = new ByteArrayOutputStream();
//			int imgByte;
//			while ((imgByte = in.read()) != -1) {
//				bStream.write(imgByte);
//			}
//
//			String type = "";
//			String ext = FilenameUtils.getExtension(file.getName());
//			if (ext != null && !"".equals(ext)) {
//				if ("jpg".equals(ext.toLowerCase())) {
//					type = "image/jpeg";
//				} else {
//					type = "image/" + ext.toLowerCase();
//				}
//
//			} else {
//				//LOGGER.debug("Image fileType is null.");
//			}
//
//			response.setHeader("Content-Type", type);
//			response.setContentLength(bStream.size());
//
//			bStream.writeTo(response.getOutputStream());
//
//			response.getOutputStream().flush();
//			response.getOutputStream().close();
//
//		} catch (Exception e) {
//			//LOGGER.debug("{}", e);
//		} finally {
//			if (bStream != null) {
//				try {
//					bStream.close();
//				} catch (Exception est) {
//					//LOGGER.debug("IGNORED: {}", est.getMessage());
//				}
//			}
//			if (in != null) {
//				try {
//					in.close();
//				} catch (Exception ei) {
//					//LOGGER.debug("IGNORED: {}", ei.getMessage());
//				}
//			}
//			if (fis != null) {
//				try {
//					fis.close();
//				} catch (Exception efis) {
//					//LOGGER.debug("IGNORED: {}", efis.getMessage());
//				}
//			}
//		}
//	}
}
