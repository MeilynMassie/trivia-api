package com.mjm.api.trivia.service.impl;

import com.mjm.api.trivia.model.Admin;
import com.mjm.api.trivia.repository.AdminRepository;
import com.mjm.api.trivia.service.AdminService;

import jakarta.transaction.Transactional;

import java.util.Optional;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminServiceImpl(AdminRepository adminRepository, PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
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

    @Override
    public void addAdmin(Admin admin) {
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        adminRepository.save(admin);
    }

    @Transactional
    @Override
    public void deleteAdmin(long id) throws UsernameNotFoundException {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Admin not found: " + id)        
        );
        adminRepository.delete(admin);
    }

    @Override
    public Boolean getAdmin(long id) {
        Optional<Admin> admin =  adminRepository.findById(id);
        if (admin.isPresent()) {
        return true;
        }
        return false;
    }
}