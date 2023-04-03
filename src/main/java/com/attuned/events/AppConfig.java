package com.attuned.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.attuned.events.dto.converter.CreateReservationRequestToReservationEntityConverter;
import com.attuned.events.dto.converter.EventEntityToEventDTOConverter;
import com.attuned.events.dto.converter.ReservationEntityToReservationDTOConverter;
import com.attuned.events.dto.converter.UpdateReservationRequestToReservationEntityConverter;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class AppConfig implements WebMvcConfigurer {
	@Autowired
	private CreateReservationRequestToReservationEntityConverter createReservationRequestToReservationEntityConverter;
	@Autowired
	private EventEntityToEventDTOConverter entityToEventDTOConverter;
	@Autowired
	private ReservationEntityToReservationDTOConverter reservationEntityToReservationDTOConverter;
	@Autowired
	private UpdateReservationRequestToReservationEntityConverter updateReservationRequestToReservationEntityConverter;
	  @Override
	    public void addFormatters(FormatterRegistry registry) {
	        registry.addConverter(createReservationRequestToReservationEntityConverter);
	        registry.addConverter(entityToEventDTOConverter);
	        registry.addConverter(reservationEntityToReservationDTOConverter);
	        registry.addConverter(updateReservationRequestToReservationEntityConverter);
	    }
	  @Bean
	  public Docket api() {
	     return new Docket(DocumentationType.SWAGGER_2)
	       .select()
	       .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
	       .build();
	  }

}
