package com.backbase.assignment.kalah;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 * The Spring Boot Main class to initiate application.
 *
 * @author Jibran Tariq
 * @version 1.0
 */
@SpringBootApplication
@ComponentScan("com.backbase.assignment")
public class KalahApplication {
  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {
    SpringApplication.run(KalahApplication.class, args);
  }

  /** The id generator of game. */
  public static final AtomicLong ID_GENERATOR = new AtomicLong(1);

  /**
   * Object mapper instance.
   *
   * @return the object mapper
   */
  @Bean
  public ObjectMapper objectMapper() {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    return objectMapper;
  }
}
