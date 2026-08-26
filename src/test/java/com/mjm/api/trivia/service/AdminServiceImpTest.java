package com.mjm.api.trivia.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.mjm.api.trivia.exception.ResourceNotFoundException;
import com.mjm.api.trivia.model.Admin;
import com.mjm.api.trivia.repository.AdminRepository;
import com.mjm.api.trivia.service.impl.AdminServiceImpl;

@ExtendWith(MockitoExtension.class)
public class AdminServiceImpTest {

    @Mock
    private AdminRepository adminRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AdminServiceImpl adminService;

    @Test
    void shouldFindAdminByUsername() {
        Admin expected = new Admin();
        expected.setId(1L);
        expected.setUsername("admin");
        expected.setPassword("secret");

        when(adminRepository.findByUsername("admin"))
            .thenReturn(Optional.of(expected));

        Admin actual = adminService.findByUsername("admin");

        assertEquals(expected, actual);
        verify(adminRepository).findByUsername("admin");
    }

    @Test
    void shouldThrowWhenAdminUsernameNotFound() {
        when(adminRepository.findByUsername("missing"))
            .thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(
            ResourceNotFoundException.class,
            () -> adminService.findByUsername("missing")
        );

        assertEquals("Admin not found with username: missing", exception.getMessage());
    }

    @Test
    void shouldLoadUserByUsername() {
        Admin admin = new Admin();
        admin.setUsername("admin");
        admin.setPassword("encoded-password");

        when(adminRepository.findByUsername("admin"))
            .thenReturn(Optional.of(admin));

        UserDetails actual = adminService.loadUserByUsername("admin");

        assertEquals("admin", actual.getUsername());
        assertEquals("encoded-password", actual.getPassword());
        verify(adminRepository).findByUsername("admin");
    }

    @Test
    void shouldThrowWhenLoadUserByUsernameNotFound() {
        when(adminRepository.findByUsername("missing"))
            .thenReturn(Optional.empty());

        UsernameNotFoundException exception = assertThrows(
            UsernameNotFoundException.class,
            () -> adminService.loadUserByUsername("missing")
        );

        assertEquals("Admin not found: missing", exception.getMessage());
    }

    @Test
    void shouldAddAdminAndEncodePassword() {
        Admin admin = new Admin();
        admin.setUsername("admin");
        admin.setPassword("plain-text");

        when(passwordEncoder.encode("plain-text"))
            .thenReturn("encoded-password");

        adminService.addAdmin(admin);

        assertEquals("encoded-password", admin.getPassword());
        verify(passwordEncoder).encode("plain-text");
        verify(adminRepository).save(admin);
    }

    @Test
    void shouldDeleteAdmin() {
        Admin admin = new Admin();
        admin.setId(1L);
        admin.setUsername("admin");
        admin.setPassword("secret");

        when(adminRepository.findById(1L))
            .thenReturn(Optional.of(admin));

        adminService.deleteAdmin(1L);

        verify(adminRepository).findById(1L);
        verify(adminRepository).delete(admin);
    }

    @Test
    void shouldReturnAdminExistsWhenIdFound() {
        Admin admin = new Admin();
        admin.setId(1L);
        admin.setUsername("admin");
        admin.setPassword("secret");

        when(adminRepository.findById(1L))
            .thenReturn(Optional.of(admin));

        String result = adminService.getAdmin(1L);

        assertEquals("Admin exists", result);
        verify(adminRepository).findById(1L);
    }
}
