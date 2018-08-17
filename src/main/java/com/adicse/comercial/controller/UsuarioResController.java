package com.adicse.comercial.controller;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;

import org.hibernate.HibernateException;
import org.hibernate.JDBCException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.adicse.comercial.model.Empleado;
import com.adicse.comercial.model.Filepath;
import com.adicse.comercial.model.GuiaRemision001;
import com.adicse.comercial.model.GuiaRemision002;
import com.adicse.comercial.model.Puntoventa;
import com.adicse.comercial.model.Usuario;
import com.adicse.comercial.model.Usuarioempleado;
import com.adicse.comercial.model.Vehiculo;
import com.adicse.comercial.service.FilepathService;
import com.adicse.comercial.service.PuntoventaService;
import com.adicse.comercial.service.UsuarioEmpleadoService;
import com.adicse.comercial.service.UsuarioService;
import com.adicse.comercial.viewResolver.PdfGuiaRemision;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RequestMapping("/res/usuario")
@RestController
public class UsuarioResController {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private FilepathService filepathService;

	@Autowired
	private UsuarioEmpleadoService usuarioEmpleadoService;
	
	@Autowired
	private PuntoventaService puntoventaService;
	
	

	@RequestMapping("pagination")
	@ResponseBody
	public Map<String, Object> pagination(@RequestParam("pagenumber") Integer pagenumber,
			@RequestParam("rows") Integer rows, @RequestParam("sortdireccion") String sortdireccion,
			@RequestParam("sortcolumn") String sortcolumn, @RequestParam("filters") Object filter) {

		Map<String, Object> response = new HashMap<String, Object>();

		Page<Usuario> page = usuarioService.pagination(pagenumber, rows, sortdireccion, sortcolumn, filter);

		List<Usuario> lst = page.getContent();

		response.put("data", lst);
		response.put("totalCount", page.getTotalElements());
		response.put("success", true);

		return response;

	}

	@RequestMapping("/save")
	@ResponseBody
	public Usuario postCreate(@RequestBody Usuario usuario) {
//		usuario.setIdusuario(0);
		return usuarioService.grabar(usuario);
	}
	
	@RequestMapping("/delete/{id}")
	@ResponseBody
	public void delete(@PathVariable Integer id) {	
		
		usuarioService.deletebyid(id);
	}
	
	@RequestMapping("/edit/{id}")
	@ResponseBody
	public Usuario getEdit(@RequestParam("id") Integer id) {
		return usuarioService.findbyid(id).get();
	}

	

	
	@RequestMapping("/update")	
	public Usuario putUdate(@RequestBody Usuario entidad) {		
		Usuario usuarioUpdate = usuarioService.findbyid(entidad.getIdusuario()).get();
			
		BeanUtils.copyProperties(entidad, usuarioUpdate);		
		return usuarioService.grabar(usuarioUpdate);
	}
	
	

	

	
	
	@RequestMapping("/findbyid")
	@ResponseBody
	public Map<String, Object> findbyid(@RequestParam("id") Integer idusuario) {
		Map<String, Object> response = new HashMap<>();

		
		Optional<Usuario> usuario = usuarioService.findbyid(idusuario);
		
		if(usuario.isPresent() ){
			for (Usuarioempleado usuarioEmpleado : usuario.get().getUsuarioempleados()) {
				usuarioEmpleado.setUsuario(null);
			}
			response.put("data", usuario.get());
		}else{
			response.put("data", new Usuario());
		}


		
		return response;
	}
	
	@ResponseBody
	@RequestMapping(value = "/loginpuntoventa")
	public Map<String, Object> loginpuntoventa(@RequestParam("username") String username,
			@RequestParam("password") String password
			)
			throws ServletException {
		System.out.println("Ingreso a login res....");

		Map<String, Object> response = new HashMap<>();
		
//		for(String row : macs){
//			row = row.toLowerCase();
//		}
  

		String sreturn = "";
		Boolean status = true;
		if (username == null || password == null) {
			// throw new ServletException ("Please fill in username and
			// password");
			sreturn = "Ingrese el usuario y clave";
			status = false;
	
			response.put("sucess", false);
			response.put("msg", sreturn);
		}

	

		Usuario usuario = usuarioService.getAllByLogin(username);

		if (usuario == null) {
			// throw new ServletException ("User name not found.");
			status = false;
			sreturn = "Login no existe";
			
			response.put("sucess", false);
			response.put("msg", sreturn);
		}

		String pwd = usuario.getClave();

		if (!password.equals(pwd)) {
			// throw new ServletException("Invalid login. Please check your name
			// and password.");
			sreturn = "Login invalido, Por favor revise login y clave";
			status = false;
			
			response.put("sucess", false);
			response.put("msg", sreturn);
		}
		if (status) {
			response.put("sucess", true);
			response.put("msg", sreturn);
		}
		
//		if(status){
//			Empleado empleado = validarUsuarioRelacionEmpleado(usuario);
//			if(empleado == null){
//				response.put("empleado", false);
//			}else{
//				response.put("empleado", empleado);
//				response.put("usuario", usuario);
//				response.put("empleado", empleado);		
//				
//				Puntoventa puntoventa = validarMacPuntoVenta(macs) ;
//				if(puntoventa != null){
//	
//					response.put("pc-caja", true);
//					response.put("puntoventa", puntoventa);
//				}else{
//					response.put("pc-caja", false);
//				}
//				
//	
//			}		
//		}
	
		
		
		return response;

	}
	
	public Empleado validarUsuarioRelacionEmpleado(Usuario usuario){
		Usuarioempleado usuarioEmpleado = usuarioEmpleadoService.getUsuarioEmpleadoByIdUsuario(usuario.getIdusuario());
		Empleado empleado = null;
		if(usuarioEmpleado != null)
			empleado = usuarioEmpleado.getEmpleado() ;
		
		return empleado;
	}
	
	public Puntoventa validarMacPuntoVenta(List<String> macs){
		Puntoventa puntoventa = puntoventaService.getPuntoVentaByMac(macs);
		
		if(puntoventa == null){
			return null;
		}else{
			return puntoventa;
		}
	}


	@ResponseBody
	@RequestMapping(value = "/uploadimage")
	public Map<String, Object> multipleSave(@RequestParam("uploads[]") MultipartFile[] files,
			@RequestParam("idusuario") Integer idusuario) {
		System.out.println("cargar file ..... ");
		Map<String, Object> response = new HashMap<String, Object>();
		// float quality = 0.1f; // Change this as needed
		// float quality_sm = 0.05f;

		String fileName = null;
		String msg = "";
		Optional<Filepath> filepath = filepathService.findbyid(2); // path id=2
																	// fotos
																	// usuarios
		String rutaImages = filepath.get().getRutaimages();
		if (files != null && files.length > 0) {
			for (int i = 0; i < files.length; i++) {
				try {
					fileName = files[i].getOriginalFilename();
					byte[] bytes = files[i].getBytes();

					ByteArrayInputStream input = new ByteArrayInputStream(bytes);
					ByteArrayInputStream input2 = new ByteArrayInputStream(bytes);

					BufferedImage image = ImageIO.read(input);
					int type = image.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : image.getType();
					BufferedImage resizeImageJpg = resizeImage(image, type, 200, 200);
					ImageIO.write(resizeImageJpg, "jpg",
							new File(rutaImages + "usuario_small_" + idusuario.toString() + ".jpg"));

					// imagen large
					BufferedImage image2 = ImageIO.read(input2);

					BufferedImage resizeImageJpgL = resizeImage(image2, type, 400, 400);
					ImageIO.write(resizeImageJpgL, "jpg",
							new File(rutaImages + "usuario_large_" + idusuario.toString() + ".jpg"));

					msg += "You have successfully uploaded " + fileName;

				} catch (Exception e) {
					msg = "You failed to upload " + fileName + ": " + e.getMessage();
					response.put("msg", msg);
					return response;
				}
			}
			response.put("msg", msg);

			return response;
		} else {
			msg = "Unable to upload. File is empty.";
			response.put("msg", msg);
		}

		return response;
	}

	private static BufferedImage resizeImage(BufferedImage originalImage, int type, int width, int height) {

		BufferedImage resizedImage = new BufferedImage(width, height, type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, width, height, null);
		g.dispose();

		return resizedImage;
	}

	@ResponseBody
	@RequestMapping(value = "/getImage")
	public Map<String, Object> getImage(@RequestParam("idusuario") String idusuario,
			@RequestParam("imageSize") String imageSize) {
		Map<String, Object> response = new HashMap<String, Object>();

		Optional<Filepath> filepath = filepathService.findbyid(2);

		// lstFoto = iFotoServicio.getFotoByIdPadron(idusuario);
		String rutafoto = "";
		String archivoImagen = null, archivoImagenOrigen = null;
		ArrayList<byte[]> lstImage = new ArrayList<>();

		String archivoSmall = "usuario_small_" + idusuario.toString() + ".jpg";
		String archivoLarge = "usuario_large_" + idusuario.toString() + ".jpg";
		rutafoto = filepath.get().getRutaimages();
		switch (imageSize) {
		case "small":
			archivoImagen = archivoSmall;
			archivoImagenOrigen = archivoSmall;

			break;
		case "large":
			archivoImagen = archivoLarge;
			archivoImagenOrigen = archivoLarge;
			break;

		default:
			break;
		}

		if (archivoImagen != null) {
			if (archivoImagen.indexOf(".") > 0) {
			}
			archivoImagen = rutafoto + archivoImagen;
			// archivoImagen = archivoImagen.replace("\\", "/");
			System.out.println("Archivo imagen : " + archivoImagen);
			File FileImagen = new File(rutafoto, archivoImagenOrigen);
			if (!FileImagen.exists()) {
				response.put("data", lstImage);
				response.put("msg", "El archivo no existe");
				response.put("success", false);
				System.out.println("No existe la imagen .....");
				return response;
			}

			System.out.println("AbsolutePath " + FileImagen.getAbsolutePath());
			System.out.println("Name " + FileImagen.getName());
			System.out.println("Path " + FileImagen.getPath());

			if (!FileImagen.canRead()) {
				response.put("data", lstImage);
				response.put("msg", "No se puede leer el archivo");
				response.put("success", false);
				System.out.println("No se pude leer el archivo.. .....");
				return response;
			}
			byte[] bimagen = null;
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			BufferedImage img;
			try {
				img = ImageIO.read(FileImagen);

				ImageIO.write(img, "jpg", byteArrayOutputStream);
				bimagen = byteArrayOutputStream.toByteArray();
				lstImage.add(bimagen);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Error ::: " + e.getMessage());
				response.put("data", lstImage);
				response.put("msg", e.getMessage());
				response.put("success", false);
				return response;
			}
		} else {

			// response.put("data", lstImage);
			response.put("msg", "El nombre de archivo no esta en la base de datos");
			response.put("success", false);
			return response;
		}

		// filetmp.delete();

		response.put("data", lstImage);
		response.put("msg", true);
		return response;
	}
	
	
	@RequestMapping(value="/getall", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Usuario> getall() {
//		Map<String, Object> response = new HashMap<String, Object>();

		List<Usuario> lst = usuarioService.getall();
//		response.put("data", lst);
		return lst;
	}
	
	
	
	
//	@RequestMapping("/getallporfilial")
//	@ResponseBody
//	public ModelAndView getGuiaRemisionPorItem(
//			@RequestParam("idfilial") String idFilial
//			){
//		
//		List<Usuario> lstUsuario = usuarioService.getGuiaRemisionPorItemAndAnnoNumeroEntrega(idItem, anno, numeroEntrega);
//		
//		for(GuiaRemision001 guiaRemision001: lstGuiaRemision001) {
//			for(GuiaRemision002 guiaRemision002: guiaRemision001.getGuiaRemision002s()) {
//				guiaRemision002.setGuiaRemision001(null);
//			}
//		}
//		
//		Map<String,Object> model = new HashMap<>();
//		
//		model.put("data", lstGuiaRemision001);
//		
//		ModelAndView mv = new ModelAndView(new PdfGuiaRemision(),model);
//		
//		return mv;	
//		
//		
//		
//		
//	}

}
