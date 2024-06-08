package ru.skypro.homework.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOnUpdateAdDto;
import ru.skypro.homework.dto.ExtendedAdDto;
import ru.skypro.homework.service.AdsService;

import java.io.IOException;
import java.security.Principal;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AdsControllerTest {

    @Mock
    private AdsService adsService;

    @Mock
    private Principal principal;

    @Mock
    private MultipartFile imageFile;

    @InjectMocks
    private AdsController adsController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllAdsTest() {
        AdsDto adsDto = new AdsDto();
        when(adsService.getAllAds()).thenReturn(adsDto);

        ResponseEntity<AdsDto> response = adsController.getAllAds();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(adsDto, response.getBody());
    }

    @Test
    void createAdTest() throws IOException {
        CreateOnUpdateAdDto createOnUpdateAdDto = new CreateOnUpdateAdDto();
        AdDto adDto = new AdDto();
        when(adsService.createAd(any(CreateOnUpdateAdDto.class), any(MultipartFile.class), any(Principal.class))).thenReturn(adDto);

        ResponseEntity<AdDto> response = adsController.createAd(createOnUpdateAdDto, imageFile, principal);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(adDto, response.getBody());
    }

    @Test
    void createAdWithIOExceptionTest() throws IOException {
        CreateOnUpdateAdDto createOnUpdateAdDto = new CreateOnUpdateAdDto();
        when(adsService.createAd(any(CreateOnUpdateAdDto.class), any(MultipartFile.class), any(Principal.class))).thenThrow(IOException.class);

        ResponseEntity<AdDto> response = adsController.createAd(createOnUpdateAdDto, imageFile, principal);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void getAdByIdTest() {
        ExtendedAdDto extendedAdDto = new ExtendedAdDto();
        when(adsService.getAdById(anyInt())).thenReturn(extendedAdDto);

        ResponseEntity<ExtendedAdDto> response = adsController.getAdById(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(extendedAdDto, response.getBody());
    }

    @Test
    void deleteAdTest() {
        doNothing().when(adsService).deleteAd(anyInt(), any(Principal.class));

        ResponseEntity<?> response = adsController.deleteAd(1, principal);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void deleteAdWithNoSuchElementExceptionTest() {
        doThrow(NoSuchElementException.class).when(adsService).deleteAd(anyInt(), any(Principal.class));

        ResponseEntity<?> response = adsController.deleteAd(1, principal);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void updateAdTest() {
        CreateOnUpdateAdDto createOnUpdateAdDto = new CreateOnUpdateAdDto();
        AdDto adDto = new AdDto();
        when(adsService.updateAd(anyInt(), any(CreateOnUpdateAdDto.class))).thenReturn(adDto);

        ResponseEntity<AdDto> response = adsController.updateAd(1, createOnUpdateAdDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(adDto, response.getBody());
    }

    @Test
    void getAdsByAuthUserTest() {
        AdsDto adsDto = new AdsDto();
        when(adsService.
        getAdsByAuthUser(any(Principal.class))).thenReturn(adsDto);

        ResponseEntity<AdsDto> response = adsController.getAdsByAuthUser(principal);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(adsDto, response.getBody());
    }

    @Test
    void updateAdImageTest() throws IOException {
        byte[] imageBytes = new byte[]{};
        when(adsService.updateAdImage(anyInt(), any(MultipartFile.class))).thenReturn(imageBytes);

        ResponseEntity<byte[]> response = adsController.updateAdImage(1, imageFile);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(imageBytes, response.getBody());
    }

    @Test
    void updateAdImageWithIOExceptionTest() throws IOException {
        when(adsService.updateAdImage(anyInt(), any(MultipartFile.class))).thenThrow(IOException.class);

        ResponseEntity<byte[]> response = adsController.updateAdImage(1, imageFile);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}