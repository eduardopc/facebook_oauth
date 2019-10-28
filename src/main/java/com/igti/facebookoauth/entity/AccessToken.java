package com.igti.facebookoauth.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class AccessToken {
    private String access_token;
    private String token_type;
    private Double expires_in;
}