package com.adicse.comercial.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;

public class JwtFilter extends GenericFilterBean {

	public void doFilter(final ServletRequest req, final ServletResponse res, final FilterChain chain)
			throws IOException, ServletException {

		final HttpServletRequest request = (HttpServletRequest) req;
		final HttpServletResponse response = (HttpServletResponse) res;
		final String authHeader = request.getHeader("authorization");
		// System.out.println("JwFilter .........................................");
		if ("OPTIONS".equals(request.getMethod())) {
			response.setStatus(HttpServletResponse.SC_OK);
			chain.doFilter(req, res);
		} else {

			if (authHeader == null || !authHeader.startsWith("Bearer ")) {
				System.out.println("Controlando Error ..................................");
				throw new ServletException("Missing or invalid Authorization header");
			}

			final String token = authHeader.substring(7);
			//DecodedJWT jwt = JWT.decode(token);
			//Date date = new Date();
			//long l = date.getTime();
			//long lexp = jwt.getExpiresAt().getTime();
			

				try {
					final Claims claims = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody();
					request.setAttribute("claims", claims);
				} catch (final SignatureException e) {
					System.out.println("Error :" + e.getMessage());
					throw new ServletException("Invalid token");
				}
			
			chain.doFilter(req, res);

		}
	}

}
