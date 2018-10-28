package org.smartinrub.reactivespringdemo.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Member {

    @Id
    private final String id;
    private final String name;
}
