package com.encore.basic.domain;

import lombok.Data;

@Data //getter setter 및 equals, toString이 사전 구현되어 있다.
//@Setter
//@Getter
public class Hello {
    private String name;
    private String email;
    private String passsWd;
}
