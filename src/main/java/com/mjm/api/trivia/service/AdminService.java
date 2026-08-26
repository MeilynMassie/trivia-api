package com.mjm.api.trivia.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.mjm.api.trivia.model.Admin;


public interface AdminService extends UserDetailsService {
    Admin findByUsername(String username);
    String getAdmin(long id);
    void addAdmin(Admin admin);
    void deleteAdmin(long id);
}