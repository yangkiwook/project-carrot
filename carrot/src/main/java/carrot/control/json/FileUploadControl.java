package carrot.control.json;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Iterator;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;


@Controller("json.fileUploadControl")

@RequestMapping("/json")
public class FileUploadControl {
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	@ResponseBody
	public String handleFileUpload(MultipartHttpServletRequest request)	throws Exception {
		Iterator<String> itrator = request.getFileNames();
	
		//파일이 여러개일경우를 생각해서 itrator로 돌림
		while(itrator.hasNext()){
			
			MultipartFile multiFile = null;
			multiFile = request.getFile(itrator.next()); 
			try {

				//파일을 저장하는 코드
				//코드에는 없는데 시스탬 시간 대입해서 파일명이 유일하게 수정해야함.
				String fileName = multiFile.getOriginalFilename();
				String fileuploadRealPath = request.getServletContext().getRealPath("/fileupload");
				byte[] bytes = multiFile.getBytes();
				File file = new File(fileuploadRealPath + "/1222222312" + fileName);
				System.out.println(fileuploadRealPath);
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(file));
				stream.write(bytes);
				stream.close();
				//파일이름을 디비에 넣는 코드 넣으면됨.
				
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("Error while loading the file");
			}
		}
		return "success";
	}
}






