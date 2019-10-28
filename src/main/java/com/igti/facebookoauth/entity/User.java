package com.igti.facebookoauth.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class User {
    private Object data;
}
