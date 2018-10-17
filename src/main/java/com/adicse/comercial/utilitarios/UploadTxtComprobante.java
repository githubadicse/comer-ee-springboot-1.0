package com.adicse.comercial.utilitarios;



import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/res/uploadtxtcomprobante")
public class UploadTxtComprobante {
	
	private static String UPLOADED_FOLDER = "D:\\proyectos\\generador archivos para facturador\\empresas\\20601449910\\fileJson\\";
	
	
	@ResponseBody
	@PostMapping("/upload") // //new annotation since 4.3
    public Map<String, Object> singleFileUpload(@RequestParam("file[]") MultipartFile[] file) {
		
		Map<String, Object> response = new HashMap<String, Object>();
		
		if (file == null || file.length == 0) {
			response.put("success", false);
			response.put("msg", "no se econtro ningun archivo");
			return response;
		}
		
		for (int i = 0; i < file.length; i++) {
			
			try {

	            // Get the file and save it somewhere
	            byte[] bytes = file[i].getBytes();
	            Path path = Paths.get(UPLOADED_FOLDER + file[i].getOriginalFilename());
	            Files.write(path, bytes);

//	            redirectAttributes.addFlashAttribute("message",
//	                    "You successfully uploaded '" + file.getOriginalFilename() + "'");
	            
	            response.put("success", true);
				response.put("msg", "Archivos subidos correctamente");

	        } catch (IOException e) {
	            e.printStackTrace();            
	            response.put("success", false);
				response.put("msg", e.getMessage());
	        }
			
		}
		                
        return response;
        
    }
}
