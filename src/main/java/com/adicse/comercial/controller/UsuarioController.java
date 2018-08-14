package com.adicse.comercial.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.adicse.comercial.model.PeriodoLectivo;
//import com.adicse.comercial.configsecurity.JwtUtil;
import com.adicse.comercial.model.Usuario;
import com.adicse.comercial.service.PeriodoLectivoService;
import com.adicse.comercial.service.UsuarioService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RequestMapping("/usuario")
@RestController
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;


	@Autowired
	private PeriodoLectivoService periodoLectivoService;

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

	@SuppressWarnings("unused")
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Map<String, Object> login(@RequestBody Map<String, String> json)
			throws ServletException {
		System.out.println("Ingreso a login....");

		Map<String, Object> response = new HashMap<>();
		
//	    String remoteAddr = "";
//	    remoteAddr = request.getRemoteAddr();
//        if (request != null) {
//            remoteAddr = request.getHeader("X-FORWARDED-FOR");
//            if (remoteAddr == null || "".equals(remoteAddr)) {
//                remoteAddr = request.getRemoteAddr();
//            }
//        }
        
//        String ip = request.getHeader("X-Forwarded-For");  
//        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {  
//            ip = request.getHeader("Proxy-Client-IP");  
//        }  
//        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {  
//            ip = request.getHeader("WL-Proxy-Client-IP");  
//        }  
//        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {  
//            ip = request.getHeader("HTTP_X_FORWARDED_FOR");  
//        }  
//        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {  
//            ip = request.getHeader("HTTP_X_FORWARDED");  
//        }  
//        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {  
//            ip = request.getHeader("HTTP_X_CLUSTER_CLIENT_IP");  
//        }  
//        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {  
//            ip = request.getHeader("HTTP_CLIENT_IP");  
//        }  
//        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {  
//            ip = request.getHeader("HTTP_FORWARDED_FOR");  
//        }  
//        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {  
//            ip = request.getHeader("HTTP_FORWARDED");  
//        }  
//        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {  
//            ip = request.getHeader("HTTP_VIA");  
//        }  
//        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {  
//            ip = request.getHeader("REMOTE_ADDR");  
//        }  
//        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {  
//            ip = request.getRemoteAddr();  
//        }  
//        System.out.println("IP : " + ip);

		String sreturn = "hola";
		Boolean status = true;
		if (json.get("username") == null || json.get("password") == null) {
			// throw new ServletException ("Please fill in username and
			// password");
			sreturn = "Ingrese el usuario y clave";
			status = false;
			response.put("token", "");
			response.put("sucess", false);
			response.put("msg", sreturn);
		}

		String login = json.get("username");
		String password = json.get("password");

		Usuario usuario = usuarioService.getAllByLogin(login);

		if (usuario == null) {
			// throw new ServletException ("User name not found.");
			status = false;
			sreturn = "Login no existe";
			response.put("token", "");
			response.put("sucess", false);
			response.put("msg", sreturn);
		}

		String pwd = usuario.getClave();

		if (!password.equals(pwd)) {
			// throw new ServletException("Invalid login. Please check your name
			// and password.");
			sreturn = "Login invalido, Por favor revise login y clave";
			status = false;
			response.put("token", "");
			response.put("sucess", false);
			response.put("msg", sreturn);
		}
		if (status) {

			Map<String, Object> claims = new HashMap<String, Object>();
			Date date = new Date();
			
			long t = date.getTime();
			long tt = 60000 * 30 ;
			Date expirationTime = new Date(t + tt); // set 5 seconds
			Long expireLon = expirationTime.getTime();
			claims.put("role", "admin");
			claims.put("role_ventas", "admin");
			String s = Jwts.builder().setSubject(login)
				
					.setClaims(claims)
					.setIssuedAt(new Date())
					//.setExpiration(expirationTime)
					.signWith(SignatureAlgorithm.HS256, "secretkey").compact();

			
			
			// String tk = JwtUtil.addAuthentication(res, login);
			
			PeriodoLectivo pl = periodoLectivoService.findbyid(1).get() ;

			response.put("token", s);
			response.put("sucess", true);
			response.put("msg", sreturn);
			
			response.put("anno", pl.getAnno());
			response.put("numeroEntrega", pl.getNumeroEntrega());
		}
		return response;

	}

//	@RequestMapping(value="/getall", produces=MediaType.APPLICATION_JSON_VALUE)
//	public List<Usuario> getall() {
//		Map<String, Object> response = new HashMap<String, Object>();
//
//		List<Usuario> lst = usuarioService.getall();
//		response.put("data", lst);
//		return lst;
//	}
	
	@RequestMapping(value="/getall", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Usuario> getAll(){
		return usuarioService.getall(); 
	}


}
