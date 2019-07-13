package cn.com.linkwide.common.util.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.log4j.lf5.util.StreamUtils;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.util.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
/**
 * 
 * 类 名 称： FileUploadController 类 描 述： jQuery File Upload 例子 创 建 人： yiming.zhang
 * 联系方式： 1374250553@qq.com 创建时间： 2014-2-19 下午11:25:22
 * 
 * 修 改 人： Administrator 操作时间： 2014-2-19 下午11:25:22 操作原因：
 * 
 */
//@Scope("prototype")
@Controller
@RequestMapping("/fileUpAndDownController")
public class FileUpAndDownController extends BaseController {
 
	private static final Logger logger = Logger.getLogger(FileUpAndDownController.class);
	
	FileMetaEntity fileMetaEntity = null;
	
	@RequestMapping(params = "upload", method = RequestMethod.POST)
	@ResponseBody
	public LinkedList<FileMetaEntity> upload(MultipartHttpServletRequest request, HttpServletResponse response) {
		LinkedList<FileMetaEntity> files = new LinkedList<FileMetaEntity>();

		Iterator<String> itr = request.getFileNames();
		MultipartFile mpf = null;

		while (itr.hasNext()) {
			mpf = request.getFile(itr.next());

			fileMetaEntity = new FileMetaEntity();
			
			fileMetaEntity.setFileSize(mpf.getSize() / 1024 + " Kb");
			fileMetaEntity.setFileType(mpf.getContentType());
			try {
				fileMetaEntity.setBytes(mpf.getBytes());
				String path ="upload/";
				String realPath = request.getSession().getServletContext().getRealPath("/") + "/" + path ;// 文件的硬盘真实路径

				// 文件保存全路径
				String savePath = realPath + mpf.getOriginalFilename();

				FileCopyUtils.copy(mpf.getBytes(),new File(savePath));

				//文件保存路径--相对路径
				fileMetaEntity.setFilePath(path + mpf.getOriginalFilename());

				//文件名称
				fileMetaEntity.setFileName(mpf.getOriginalFilename());
			} catch (IOException e) {
				e.printStackTrace();
			}

			files.add(fileMetaEntity);

		}

		return files;
	}
	
	@RequestMapping(params = "view", method = {RequestMethod.GET,RequestMethod.POST})
	 public void get(HttpServletResponse response,String index,HttpServletRequest request){
		 try {		
			 	String filePath = request.getParameter("filePath");
			 	if("".equals(filePath)||filePath==null)
			 		return;
			 	/*filePath = URLDecoder.decode(filePath,"UTF-8");*/
			 	filePath = new String(filePath.getBytes("iso-8859-1"),"UTF-8");
			 	String fileName = filePath.substring(7);
			 	
			 	response.setContentType(FileUtils.contentType(FileUtils.getExtend(fileName)));

			 	// 文件的硬盘真实路径
				String realPath = request.getSession().getServletContext().getRealPath("/") + "/" + filePath ;
				
				// 文件保存全路径
				String savePath = realPath;
				
			 	File file = new File(savePath);
			 	FileInputStream fin = new FileInputStream(file);
			 	response.setHeader("Content-disposition", "attachment; filename=\"" + fileName + "\"");
		        FileCopyUtils.copy(StreamUtils.getBytes(fin), response.getOutputStream());
		 }catch (IOException e) {
			e.printStackTrace();
		 }
	 }
	
	/**
	 * 数据库检索附件
	 * @param response
	 * @param index
	 * @param request
	 */
	@RequestMapping(params = "viewDoc", method = {RequestMethod.GET,RequestMethod.POST})
	 public void viewDoc(HttpServletResponse response,String index,HttpServletRequest request){
		 try {		
			 	String filePath = request.getParameter("filePath");

			 	
			 	filePath = new String(filePath.getBytes("iso-8859-1"),"UTF-8");
			 	String [] array = filePath.split("/");
			 	String fileName = array[array.length-1];
			 	
			 	response.setContentType(FileUtils.contentType(FileUtils.getExtend(fileName)));

			 	// 文件的硬盘真实路径
				String realPath = request.getSession().getServletContext().getRealPath("/") + "/" + filePath ;
				
				// 文件保存全路径
				String savePath = realPath;
				
			 	File file = new File(savePath);
			 	FileInputStream fin = new FileInputStream(file);
			 	response.setHeader("Content-disposition", "attachment; filename=\"" + URLEncoder.encode(fileName, "UTF-8") + "\"");
		        FileCopyUtils.copy(StreamUtils.getBytes(fin), response.getOutputStream());
		 }catch (IOException e) {
			e.printStackTrace();
		 }
	 }
	
	/**
	 * 制定文件路径的上传
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "uploadPara", method = RequestMethod.POST)
	@ResponseBody
	public LinkedList<FileMetaEntity> uploadPara(MultipartHttpServletRequest request, HttpServletResponse response) {
		LinkedList<FileMetaEntity> files = new LinkedList<FileMetaEntity>();

		Iterator<String> itr = request.getFileNames();
		MultipartFile mpf = null;

		while (itr.hasNext()) {
			mpf = request.getFile(itr.next());

			fileMetaEntity = new FileMetaEntity();
			
			fileMetaEntity.setFileSize(mpf.getSize() / 1024 + " Kb");
			fileMetaEntity.setFileType(mpf.getContentType());
			try {
				fileMetaEntity.setBytes(mpf.getBytes());
				
				//获取 用户制定的上传路径
				String userUploadPath = request.getParameter("uploadPath");

				String realPath = request.getSession().getServletContext().getRealPath("/") + "/" + userUploadPath ;// 文件的硬盘真实路径

				File file = new File(realPath);
				if (!file.exists()) {
					file.mkdirs();// 创建根目录
				}
				
				String saveName =  mpf.getOriginalFilename();
				if(saveName.indexOf(".")>0){
					saveName = UUID.randomUUID().toString().replaceAll("-","") + saveName.substring(saveName.indexOf("."), saveName.length()) ;
				}
				// 文件保存全路径
				String savePath = realPath + saveName;

				FileCopyUtils.copy(mpf.getBytes(),new File(savePath));

				//文件保存路径--相对路径
				fileMetaEntity.setFilePath(userUploadPath +saveName);

				//文件名称
				fileMetaEntity.setFileName(mpf.getOriginalFilename());
			} catch (IOException e) {
				e.printStackTrace();
			}

			files.add(fileMetaEntity);

		}

		return files;
	}
	
}
