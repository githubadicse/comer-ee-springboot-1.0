package com.adicse.comercial;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import com.adicse.comercial.config.JwtFilter;
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
public class ComercialApplication {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
	public FilterRegistrationBean jwtFilter() {
		final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(new JwtFilter());
		registrationBean.addUrlPatterns("/res/*");
		System.out.println("REGISTRANDO .................................................");
		return registrationBean;
	}

	@Bean
	@Primary
	public ObjectMapper jsonMapper() {
	
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		
	    ObjectMapper mapper = new ObjectMapper();
	    Hibernate5Module hm = new Hibernate5Module();
	    //hm.configure(Hibernate5Module.Feature.FORCE_LAZY_LOADING, false);
	    hm.enable(Feature.SERIALIZE_IDENTIFIER_FOR_LAZY_NOT_LOADED_OBJECTS);
	    
	    mapper.registerModule(hm);
	    mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
	    
	    // This doesn't work with Hibernate4Module :-(
	    //mapper.setSerializationInclusion(Include.NON_NULL);
	    mapper.setSerializationInclusion(Include.NON_EMPTY);
	    mapper.setDateFormat(df);
	    mapper.setTimeZone(TimeZone.getTimeZone("EST"));
	 
	   
	    return mapper;
	}
	
//	@Bean
//	public Jackson2ObjectMapperBuilder configureObjectMapper() {
//		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
//		
//		builder.serializationInclusion(JsonInclude.Include.NON_EMPTY);
//		builder.autoDetectFields(true);
//		builder.simpleDateFormat("dd/MM/yyyy");
//		
//		builder.modulesToInstall(Hibernate5Module.class).featuresToEnable(Feature.SERIALIZE_IDENTIFIER_FOR_LAZY_NOT_LOADED_OBJECTS)  ;
//		//builder.autoDetectGettersSetters(false);
//		builder.timeZone(TimeZone.getTimeZone("EST"));
//		//om.setTimeZone(TimeZone.getTimeZone("EST"));
//		return builder;
//
//	}


	
	public static void main(String[] args) {
		SpringApplication.run(ComercialApplication.class, args);
	}

}
