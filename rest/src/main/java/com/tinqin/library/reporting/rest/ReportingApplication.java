package com.tinqin.library.reporting.rest;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.tinqin.library.reporting")
public class ReportingApplication {

  public static void main(String[] args) {
    SpringApplication.run(ReportingApplication.class, args);
  }
}