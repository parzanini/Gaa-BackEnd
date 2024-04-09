package com.gaaclub.service;

import com.gaaclub.model.Members;
import com.gaaclub.repository.MembersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MembersServiceImpl implements MembersService {

    private final MembersRepository membersRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public MembersServiceImpl(MembersRepository membersRepository, PasswordEncoder passwordEncoder) {
        this.membersRepository = membersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Members getMemberById(String email) {
        Optional<Members> optional = membersRepository.findById(email);
        Members member = null;
        if (optional.isPresent()) {
            member = optional.get();
        } else {
            throw new RuntimeException(" member not found for email : " + email);
        }
        return member;
    }

    @Override
    public void saveMember(Members member) {
        if (membersRepository.findById(member.getEmail()).isPresent()) {
            throw new RuntimeException(" member already exists for email : " + member.getEmail());
        } else {
            var password = passwordEncoder.encode(member.getPassword());
            member.setPassword(password);
            this.membersRepository.save(member);
        }
    }

    @Override
    public void deleteMember(String email) {
        this.membersRepository.deleteById(email);
    }

    @Override
    public void updateMember(Members member) {
        var password = passwordEncoder.encode(member.getPassword());
        member.setPassword(password);
        this.membersRepository.save(member);
    }

    @Override
    public List<Members> getAllMembers() {
        return membersRepository.findAll();
    }
}