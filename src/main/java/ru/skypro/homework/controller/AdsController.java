package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOnUpdateAdDto;
import ru.skypro.homework.dto.ExtendedAdDto;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
public class AdsController {

    @GetMapping
    public ResponseEntity<AdsDto> getAllAds() {
        return null;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AdDto> createAd(@RequestPart("properties") CreateOnUpdateAdDto createOnUpdateAdDto,
                                          @RequestPart("image") MultipartFile imageFile,
                                          Authentication authentication) {
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExtendedAdDto> getAdById(@PathVariable Long id) {
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAd(@PathVariable Long id,
                                      Authentication authentication) {
        return null;
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AdDto> updateAd(
            @PathVariable Long id,
            @RequestBody CreateOnUpdateAdDto createOnUpdateAdDto) {
        return null;
    }

    @GetMapping("/me")
    public ResponseEntity<AdsDto> getAdsByAuthUser(
            Authentication authentication) {
        return null;
    }

    @PatchMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<byte[]> updateAdImage(@PathVariable("id") Long adId,
                                                @RequestParam("image") MultipartFile imageFile) {
        return null;
    }
}
