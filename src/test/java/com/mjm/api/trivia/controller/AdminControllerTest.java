package com.mjm.api.trivia.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mjm.api.trivia.model.Admin;
import com.mjm.api.trivia.service.AdminService;
import com.mjm.api.trivia.service.PlayerService;
import com.mjm.api.trivia.service.TriviaQuestionService;

@ExtendWith(MockitoExtension.class)
class AdminControllerTest {

    @Mock
    private TriviaQuestionService triviaQuestionService;

    @Mock
    private PlayerService playerService;

    @Mock
    private AdminService adminService;

    @InjectMocks
    private AdminController controller;

    @Test
    void shouldLoadData() {
        controller.loadData(5);

        verify(triviaQuestionService).loadQuestions(5);
    }

    @Test
    void shouldGetAdmin() {
        when(adminService.getAdmin(7L)).thenReturn("Admin exists");

        String result = controller.getAdmin(7L);

        assertEquals("Admin exists", result);
        verify(adminService).getAdmin(7L);
    }

    @Test
    void shouldAddAdmin() {
        Admin admin = new Admin();
        admin.setUsername("admin");
        admin.setPassword("secret");

        controller.addAdmin(admin);

        verify(adminService).addAdmin(admin);
    }

    @Test
    void shouldDeleteAdmin() {
        controller.deleteAdmin(11L);

        verify(adminService).deleteAdmin(11L);
    }

    @Test
    void shouldDeletePlayer() {
        controller.deletePlayer(13L);

        verify(playerService).deletePlayer(13L);
    }
}
