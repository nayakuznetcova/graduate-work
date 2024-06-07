package ru.skypro.homework.service;

import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOnUpdateAdDto;
import ru.skypro.homework.dto.ExtendedAdDto;

import java.io.IOException;
import java.security.Principal;

public interface AdsService {
    AdDto createAd(CreateOnUpdateAdDto createOnUpdateAdDto, MultipartFile imageFile, Principal principal) throws IOException;
    AdsDto getAllAds();
    ExtendedAdDto getAdById(int id);
    void deleteAd(Integer id, Principal principal);
    AdsDto getAdsByAuthUser(Principal principal);
}
