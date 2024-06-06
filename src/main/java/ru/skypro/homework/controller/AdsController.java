package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.controller.swagger.AdsControllerSwagger;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOnUpdateAdDto;
import ru.skypro.homework.dto.ExtendedAdDto;
import ru.skypro.homework.service.AdsService;

import java.io.IOException;
import java.security.Principal;
import java.util.NoSuchElementException;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
public class AdsController implements AdsControllerSwagger {
    private final AdsService adsService;

    @GetMapping
    public ResponseEntity<AdsDto> getAllAds() {
        AdsDto allAds = adsService.getAllAds();
        return ResponseEntity.ok(allAds);
    }

    @Transactional
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AdDto> createAd(@RequestPart("properties") CreateOnUpdateAdDto createOnUpdateAdDto,
                                          @RequestPart("image") MultipartFile imageFile,
                                          Principal principal) {
        try {
            AdDto ad = adsService.createAd(createOnUpdateAdDto, imageFile, principal);
            return ResponseEntity.ok(ad);
        } catch (IOException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExtendedAdDto> getAdById(@PathVariable Integer id) {
        ExtendedAdDto extendedAdDto = adsService.getAdById(id);
        return ResponseEntity.ok(extendedAdDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAd(@PathVariable Integer id, Principal principal) {
        try {
            adsService.deleteAd(id, principal);
        }catch (NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AdDto> updateAd(@PathVariable Integer id, @RequestBody CreateOnUpdateAdDto createOnUpdateAdDto) {
        return null;
    }

    @GetMapping("/me")
    public ResponseEntity<AdsDto> getAdsByAuthUser(Principal principal) {
        AdsDto allAdAuthUser = adsService.getAdsByAuthUser(principal);
        return ResponseEntity.ok(allAdAuthUser);
    }

    @Transactional
    @PatchMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<byte[]> updateAdImage(@PathVariable("id") Integer adId, @RequestParam("image") MultipartFile imageFile) {
        return null;
    }
}
