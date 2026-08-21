package com.mjm.api.trivia.service.impl;

import com.mjm.api.trivia.model.Admin;
import com.mjm.api.trivia.repository.AdminRepository;
import com.mjm.api.trivia.service.AdminService;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;

    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        System.out.println("Admin username: "  + username);
        
        Admin admin = adminRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                "Admin not found: " + username));
        System.out.println("Found user: " + admin.getUsername());
        System.out.println("Password from DB: " + admin.getPassword());
        return User.builder()
                .username(admin.getUsername())
                .password(admin.getPassword())
                .build();
    }

    @Override
    public Admin findByUsername(String username) {
        return adminRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                "Admin not found: " + username));
    }
}