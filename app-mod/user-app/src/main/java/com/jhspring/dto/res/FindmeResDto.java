package com.jhspring.dto.res;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindmeResDto {
    private String id;
    private String name;
    private String email;

    public FindmeResDto(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}
