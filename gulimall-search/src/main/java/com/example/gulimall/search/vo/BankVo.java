package com.example.gulimall.search.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.io.Serializable;

/**
 * @author ZhibinJiang on 2022/6/28
 */
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class BankVo implements Serializable {
    private Long accountNumber;
    private Long balance;
    private String firstname;
    private String lastname;
    private Long age;
    private String gender;
    private String address;
    private String employer;
    private String email;
    private String city;
    private String state;
}

