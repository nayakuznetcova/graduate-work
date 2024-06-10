package ru.skypro.homework.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.model.AdEntity;
import ru.skypro.homework.model.CommentEntity;
import ru.skypro.homework.model.UserEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import static ru.skypro.homework.model.special.Role.USER;


public class CommentMapperTest {

private final CommentMapper commentMapperTest= Mappers.getMapper(CommentMapper.class);
    @Test
    void mapCreateOrUpdateCommentDtoToModel() {

        UserEntity user=new UserEntity();
        user.setId(1);
        user.setEmail("pop@mail.ru");
        user.setFirstName("Матвей");
        user.setLastName("Белобоков");
        user.setPhone("+7(923)888-81-63");
        user.setPassword("matvei6548");
        user.setRole(USER);

        AdEntity adEntity=new AdEntity();
        adEntity.setId(2);
        adEntity.setPrice(52);
        adEntity.setTitle("Телевизор");
        adEntity.setDescription("продажа телевизора");


        CreateOrUpdateComment createOrUpdateComment=new CreateOrUpdateComment();
        createOrUpdateComment.setText("Создание комментария");
        Date current = new Date();
        long createdAt = current.getTime();


        CommentEntity commentEntity=commentMapperTest.toCommentEntity(createOrUpdateComment,createdAt,user,adEntity);



        Assertions.assertNotNull(commentEntity);
        Assertions.assertNotNull(commentEntity.getUser());
        Assertions.assertNotNull(commentEntity.getAd());
        Assertions.assertEquals(createOrUpdateComment.getText(), commentEntity.getText());

        Assertions.assertEquals(createdAt, commentEntity.getCreatedAt());
        Assertions.assertEquals(createdAt, commentEntity.getCreatedAt());

    }

//    @Test
//    void shouldProperlyMapDtoToModel() {
//        //given
//        LectureDTO dto = new LectureDTO();
//        dto.setId(11L);
//        dto.setName("lecture name");
//
//        //when
//        LectureModel model = mapperUnderTest.toModel(dto);
//
//        //then
//        Assertions.assertNotNull(model);
//        Assertions.assertEquals(dto.getId(), model.getId());
//        Assertions.assertEquals(dto.getName(), model.getName());
//    }

}
