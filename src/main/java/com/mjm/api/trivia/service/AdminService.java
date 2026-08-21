package com.mjm.api.trivia.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.mjm.api.trivia.model.Admin;


public interface AdminService extends UserDetailsService {
    Admin findByUsername(String username) throws UsernameNotFoundException;
}