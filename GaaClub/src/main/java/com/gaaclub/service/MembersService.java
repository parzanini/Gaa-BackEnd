package com.gaaclub.service;

import com.gaaclub.model.Members;

import java.util.List;


public interface MembersService {

    Members getMemberById(String email);
    void saveMember(Members member);
    void deleteMember(String id);
    void updateMember(Members member);
    List<Members> getAllMembers();


}
