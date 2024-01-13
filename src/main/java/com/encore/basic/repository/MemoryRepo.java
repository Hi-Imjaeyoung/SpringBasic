package com.encore.basic.repository;

import com.encore.basic.domain.Member;

import java.util.ArrayList;
import java.util.List;

public class MemoryRepo {
    static  List<Member> memberList = new ArrayList<>();
    public static void addMember(Member member){
        memberList.add(member);
    }
    public static String showMember(int i){
        return memberList.get(i).toString();
    }
}