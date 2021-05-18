/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.dujmovic.confapp.web;

import hr.algebra.dujmovic.confapp.model.Lecture;
import hr.algebra.dujmovic.confapp.model.Speaker;
import static org.junit.Assume.assumeTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 *
 * @author matij
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LecturesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testInvalidLectureSubmit() throws Exception {
        this.mockMvc
                .perform(post("/lectures/new")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .with(csrf())
                        .with(user("test").password("testpass").roles("USER", "ADMIN")))
                .andExpect(status().isOk())
                .andExpect(view().name("lectureProposal"));
    }

    @Test
    public void testValidLectureSubmit() throws Exception {
        assumeTrue(this.mockMvc != null);

        this.mockMvc
                .perform(post("/lectures/new")
                        .param("title", "Test title")
                        .param("contents", "Test test test test test test test test test test test test test test")
                        .param("type", Lecture.Type.WORKSHOP.toString())
                        .param("speaker.name", "Tester")
                        .param("speaker.position", Speaker.Position.MID.toString())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .with(csrf())
                        .with(user("test").password("testpass").roles("USER", "ADMIN")))
                .andExpect(status().isOk())
                .andExpect(view().name("lectureAccepted"));
    }
}
