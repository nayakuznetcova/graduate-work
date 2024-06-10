package ru.skypro.homework.controller;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.CommentsDto;
import ru.skypro.homework.model.AdEntity;
import ru.skypro.homework.model.CommentEntity;
import ru.skypro.homework.model.UserEntity;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.security.WebSecurityConfig;
import ru.skypro.homework.service.AdsService;

import ru.skypro.homework.service.UserService;
import ru.skypro.homework.service.impl.AdsServiceImpl;
import ru.skypro.homework.service.impl.CommentServiceImpl;
import ru.skypro.homework.service.impl.UserServiceImpl;

import java.time.LocalDate;
import java.util.*;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


//@WebMvcTest()
@AutoConfigureMockMvc
@SpringBootTest

public class CommentWebMvcTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CommentRepository commentRepository;
    @MockBean
    private AdRepository adRepository;
    @MockBean
    private UserRepository userRepository;


    @SpyBean
    private CommentServiceImpl commentService;
    @SpyBean
    private AdsServiceImpl adsService;
    @SpyBean
    private UserServiceImpl userService;


    @InjectMocks
    private CommentsController commentsController;

    @Test
    public void addCommentTest() throws Exception {
        Integer id = 1;
        String text = "комментарий";

        Date current = new Date();
        long createdAt = current.getTime();

        JSONObject commentObject = new JSONObject();
        commentObject.put("text", text);
        commentObject.put("createdAt", createdAt);

        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setText(text);
        commentEntity.setCreatedAt(createdAt);
        commentEntity.setId(id);
//        commentEntity.setUser(userEntity);
//        commentEntity.setAd(adEntity);

        when(commentRepository.save(any(CommentEntity.class))).thenReturn(commentEntity);


        mockMvc.perform(MockMvcRequestBuilders
                        .post("/{id}/comments", id)
                        .content(commentObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.text").value(text));



    }

//    @Test
//    public void getCommentsByAdId() throws Exception {
//        int author = 1;
//        String authorImage ="Test";
//        String authorFirstName ="Test";
//        LocalDate createdAt = LocalDate.parse("2007-12-03");
//        int pk=1;
//        String text=  "Test";
//
//        Integer id = 1;
//        String text1 = "комментарий";
//        LocalDate createdAt1 = LocalDate.parse("2007-12-03");
//
//        CommentEntity commentEntity = new CommentEntity();
//        commentEntity.setText(text1);
//        commentEntity.setCreatedAt(createdAt1);
//        commentEntity.setId(id);
//        List<CommentEntity>commentEntityList=new ArrayList<>(Arrays.asList(commentEntity,commentEntity));
//
//
//        CommentDto commentDto=new CommentDto();
//        commentDto.setAuthor(author);
//        commentDto.setAuthorImage(authorImage);
//        commentDto.setAuthorFirstName(authorFirstName);
//        commentDto.setCreatedAt(createdAt);
//        commentDto.setPk(pk);
//        commentDto.setText(text);
//        List<CommentDto>commentDtoList=new ArrayList<>(Arrays.asList(commentDto,commentDto));
//        CommentsDto commentsDto=new CommentsDto();
//        commentsDto.setResults(commentDtoList);
//        commentsDto.setCount(commentDtoList.size());
//
//        when(commentService.getCommentsByAdId(any(Integer.class))).thenReturn(commentsDto);
//        mockMvc.perform(MockMvcRequestBuilders
//                        .get("/{id}/comments")
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andDo(print())
//                .andExpect(jsonPath("$", hasSize(2)));
////                .andExpect(jsonPath("$[0].name").value(name))
////                .andExpect(jsonPath("$[1].name").value(faculty1.getName()));
//
//    }
}





