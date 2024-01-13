package com.encore.basic.domain;

public class Member {
    private String name;
    private String id;
    private String pwd;

    Member(MemberDTO memberDTO){
        this.name = memberDTO.getName();
        this.id = memberDTO.getId();
        this.pwd = memberDTO.getPwd();
    }

    public String toString(){
        return "name : "+ name +" id : "+id+" pwd : "+pwd;
    }
}
