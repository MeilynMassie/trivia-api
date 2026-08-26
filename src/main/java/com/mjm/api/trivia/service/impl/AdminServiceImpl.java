package com.mjm.api.trivia.service.impl;

import com.mjm.api.trivia.exception.ResourceNotFoundException;
import com.mjm.api.trivia.model.Admin;
import com.mjm.api.trivia.repository.AdminRepository;
import com.mjm.api.trivia.service.AdminService;

import jakarta.transaction.Transactional;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final String className = "Admin";

    public AdminServiceImpl(AdminRepository adminRepository, PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Admin findByUsername(String username) {
        return adminRepository.findByUsername(username)
                .orElseThrow(() ->
                        new ResourceNotFoundException(className, username));
    }

    @Override
    public UserDetails loadUserByUsername(String username) {        
        Admin admin = adminRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                "Admin not found: " + username));

        return User.builder()
                .username(admin.getUsername())
                .password(admin.getPassword())
                .build();
    }

    @Override
    public void addAdmin(Admin admin) {
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        adminRepository.save(admin);
    }

    @Transactional
    @Override
    public void deleteAdmin(long id) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(className, id));   
        adminRepository.delete(admin);
    }

    @Override
    public String getAdmin(long id) {
        adminRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(className, id));   
        return "Admin exists";
    }
}