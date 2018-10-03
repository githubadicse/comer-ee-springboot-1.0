package com.adicse.comercial;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module.Feature;

@EnableAutoConfiguration
@SpringBootApplication
// @JsonInclude(value=JsonInclude.Include.NON_EMPTY,
// content=JsonInclude.Include.NON_EMPTY)
// extends SpringBootServletInitializer implements WebApplicationInitializer
public class ComercialApplication implements CommandLineRunner {


	@Bean
	@Primary
	public ObjectMapper jsonMapper() {
	
		
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		//TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
		
	    ObjectMapper mapper = new ObjectMapper();
	    Hibernate5Module hm = new Hibernate5Module();
	    //hm.configure(Hibernate5Module.Feature.FORCE_LAZY_LOADING, false);
	    hm.enable(Feature.SERIALIZE_IDENTIFIER_FOR_LAZY_NOT_LOADED_OBJECTS);
	    
	    mapper.registerModule(hm);
	    mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
	    
	    // This doesn't work with Hibernate4Module :-(
	    //mapper.setSerializationInclusion(Include.NON_NULL);
	    mapper.setSerializationInclusion(Include.NON_EMPTY);
	    //df.setTimeZone(TimeZone.getTimeZone("UTC"));
	    mapper.setDateFormat(df);
	   
	    return mapper;
	}
	
	  @Bean
	   public LocaleResolver localeResolver(){
	        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
	        localeResolver.setDefaultLocale(Locale.getDefault() );
	        return  localeResolver;
	    }
	@Bean
	public BCryptPasswordEncoder  passwordEncoder() {
		return new BCryptPasswordEncoder();
	}	


	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	
	public static void main(String[] args) {
		
		SpringApplication.run(ComercialApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String passw = "123";
		
		for(int i=0 ; i < 2 ; i++) {
			String ps = passwordEncoder.encode(passw);
			System.out.println(ps);
		}
		
//		LocalDateTime ahora = LocalDateTime.now(); 
//	       System.out.printf("La hora es: %s%n", ahora); 
//	       LocalDateTime algunDia = LocalDateTime.of(1976, Month.MARCH, 27, 6, 10); 
//	       System.out.printf("Yo nací el %s%n", algunDia); 
//	       System.out.printf("Hace seis meses fue %s%n", LocalDateTime.now().minusMonths(6)); 

		
	       ZoneId zona = ZoneId.systemDefault();
	       LocalDate ahora = LocalDate.now();
	       ZonedDateTime inicioHoy = ahora.atStartOfDay(zona);
	       Instant instante = inicioHoy.toInstant();
	       Date fecha = Date.from(instante);

	       System.out.println(zona);
	       System.out.println(ahora);
	       System.out.println(inicioHoy);
	       System.out.println(instante);
	       System.out.println(fecha);	       
	       
	       
	}

}
