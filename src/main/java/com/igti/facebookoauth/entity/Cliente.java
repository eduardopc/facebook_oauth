package com.igti.facebookoauth.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Cliente {
    private String id;
    private String facebookId;
    private String name;
}
