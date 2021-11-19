package com.motaharinia.crud.modules.member.persistence;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MemberDB {
    public static HashMap<Long, Member> memberHashMap = new HashMap<>();
    static{
        LocalDate now = LocalDate.now(ZoneId.systemDefault());
        memberHashMap.put(1L, new Member(1L, "Lokesh", "Gupta","1111111111", now));
        memberHashMap.put(2L, new Member(2L, "John", "Gruber","1111111111", now));
        memberHashMap.put(3L, new Member(3L, "Melcum", "Marshal","1111111111", now));
    }

    public static List<Member> readAll(){
        return new ArrayList<>(memberHashMap.values());
    }

    public static Member readById(Long id){
        return memberHashMap.get(id);
    }

    public static void update(Long id, Member member){
        memberHashMap.put(id, member);
    }

    public static void delete(Long id){
        memberHashMap.remove(id);
    }
}
