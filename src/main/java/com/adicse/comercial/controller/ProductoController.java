package com.adicse.comercial.controller;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.imageio.ImageIO;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.adicse.comercial.model.Codigobarra;
import com.adicse.comercial.model.Filepath;

import com.adicse.comercial.model.Producto;
import com.adicse.comercial.service.FilepathService;
import com.adicse.comercial.service.ProductoService;
import com.adicse.comercial.utilitarios.Idunico;
import com.adicse.comercial.viewResolver.PdfListaProductos;


@RestController
@RequestMapping("/res/producto")
public class ProductoController {

	@Autowired
	private ProductoService productoService;

	@Autowired
	private FilepathService filepathService;

	@RequestMapping("/pagination")
	@ResponseBody
	public Map<String, Object> pagination(@RequestParam("pagenumber") Integer pagenumber,
			@RequestParam("rows") Integer rows, @RequestParam("sortdireccion") String sortdireccion,
			@RequestParam("sortcolumn") String sortcolumn, @RequestParam("filters") Object filter) {

		System.out.println("pagenumber :" + pagenumber);
		System.out.println("rows :" + rows);
		Page<Producto> page = productoService.pagination(pagenumber, rows, sortdireccion, sortcolumn, filter);

		List<Producto> lst = page.getContent();
		

		Map<String, Object> response = new HashMap<String, Object>();

		response.put("data", lst);
		response.put("totalCount", page.getTotalElements());
		response.put("success", true);
		return response;
	}
	
	@RequestMapping("/create")
	@ResponseBody
	public Producto postCreate(@RequestBody Producto producto) {
		producto.setIdproducto(0);
		return productoService.grabar(producto);
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public Producto putUdate(@RequestBody Producto entidad) {		
		Producto productoUpdate = productoService.findbyid(entidad.getIdproducto() ).get();
		
		// elimina en perfilesdetalle por idperfil si existe		
		productoService.deleteNumeradorByIdTipoDocumento(entidad.getIdproducto());
		
		// colocamos el id al detalle		
		for(Codigobarra row: entidad.getCodigobarras()) {			
			row.setProducto(entidad);			
			row.setIdcodigobarra(new Idunico().getIdunico());			
		}
		
		BeanUtils.copyProperties(entidad, productoUpdate);
		
		// evita recursividad
		Producto entidadRes = productoService.grabar(productoUpdate);
		for (Codigobarra rowpd: entidadRes.getCodigobarras()) {
			rowpd.setProducto(null);
		}
		
		return entidadRes;
	}
	
	@RequestMapping(value="/save", produces=MediaType.APPLICATION_JSON_VALUE)
	public Producto save( @RequestBody Producto entidad ) {
		
		// colocamos el id al detalle		
		for(Codigobarra row: entidad.getCodigobarras()) {			
			row.setProducto(entidad);			
			row.setIdcodigobarra(new Idunico().getIdunico());			
		}
								
		// evita recursividad
		Producto entidadRes = productoService.grabar(entidad);
		for (Codigobarra rowpd: entidadRes.getCodigobarras()) {
			rowpd.setProducto(null);
		}
		
		return entidadRes;
	}

	@RequestMapping(value="/getall", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Producto> getAll() {
		List<Producto> lst = productoService.getall();
		
		return lst;
	}

//	@RequestMapping("/edit")
//	@ResponseBody
//	public Producto getEdit(@RequestParam("id") Integer idproducto) {
//
//		Producto producto = productoService.findbyid(idproducto).get();
//
//		for (Codigobarra cb : producto.getCodigobarras()) {
//			cb.setProducto(null);
//		}
//
//		return producto;
//	}
	
	@RequestMapping("/edit")
	@ResponseBody
	public Producto getEdit(@RequestParam("id") Integer id) {
		Producto producto = productoService.findById(id);
		
		for (Codigobarra rowPD: producto.getCodigobarras()) {
			rowPD.setProducto(null);
		}
		return producto;
	}

	@ResponseBody
	@RequestMapping(value = "/uploadimage")
	public Map<String, Object> multipleSave(@RequestParam("uploads[]") MultipartFile[] files,
			@RequestParam("idproducto") Integer idproducto) {
		System.out.println("cargar file ..... ");
		Map<String, Object> response = new HashMap<String, Object>();
		//float quality = 0.1f; // Change this as needed
		//float quality_sm = 0.05f;

		String fileName = null;
		String msg = "";
		Optional<Filepath> filepath = filepathService.findbyid(1);
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
					BufferedImage resizeImageJpg = resizeImage(image, type, 200,200);
					ImageIO.write(resizeImageJpg, "jpg",
							new File(rutaImages + "articulo_small_" + idproducto.toString() + ".jpg"));
					
					
					//imagen large
					BufferedImage image2 = ImageIO.read(input2);
					
					BufferedImage resizeImageJpgL = resizeImage(image2, type, 400,400);
					ImageIO.write(resizeImageJpgL, "jpg",
							new File(rutaImages + "articulo_large_" + idproducto.toString() + ".jpg"));					


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
	public Map<String, Object> getImage(@RequestParam("idproducto") String idproducto,
			@RequestParam("imageSize") String imageSize) {
		Map<String, Object> response = new HashMap<String, Object>();

		Optional<Filepath> filepath = filepathService.findbyid(1);


		// lstFoto = iFotoServicio.getFotoByIdPadron(idpadron);
		String rutafoto = "";
		String archivoImagen = null, archivoImagenOrigen = null;
		ArrayList<byte[]> lstImage = new ArrayList<>();

		String archivoSmall = "articulo_small_" + idproducto.toString() + ".jpg";
		String archivoLarge = "articulo_large_" + idproducto.toString() + ".jpg";
		rutafoto = filepath.get().getRutaimages();
		switch (imageSize) {
		case "small":
			archivoImagen = archivoSmall;
			archivoImagenOrigen = archivoSmall;

			break;
		case "large":
			archivoImagen = archivoLarge;
			archivoImagenOrigen =archivoLarge;
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
		}else{
			
			//response.put("data", lstImage);
			response.put("msg", "El nombre de archivo no esta en la base de datos");
			response.put("success", false);				
			return response;			
		}

		// filetmp.delete();

		response.put("data", lstImage);
		response.put("msg", true);
		return response;
	}
	
	@ResponseBody
	@RequestMapping("/findByDscproductoContainingIgnoreCaseOrderByDscproducto")
	public List<Producto> findByDscproductoContainingIgnoreCaseOrderByDscproducto(@RequestParam("dscproducto") String dscproducto){
		
		
		List<Producto> lst = productoService.findByDscproductoContainingIgnoreCaseOrderByDscproducto(dscproducto);
		
		
		return lst;
		
	}
	
	
	@RequestMapping("/pdfLista")
	public ModelAndView reportePdfMarca() {

		
		List<Producto> lstProducto = productoService.getall();
		
		Map<String, Object> model = new HashMap<>();
		model.put("data", lstProducto);
	

		ModelAndView mv = new ModelAndView(new PdfListaProductos(), model);

		return mv;

	}
	

}
