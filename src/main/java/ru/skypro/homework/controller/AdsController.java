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
import ru.skypro.homework.service.AdService;

import java.io.IOException;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
public class AdsController {

    private final AdService adService;

    @GetMapping
    public ResponseEntity<AdsDto> getAllAds() {

        return null;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AdDto> createAd(@RequestPart("properties") CreateOnUpdateAdDto createOnUpdateAdDto,
                                          @RequestPart("image") MultipartFile imageFile,
                                          Authentication authentication) throws IOException {
        return ResponseEntity.ok(adService.createAd(createOnUpdateAdDto,imageFile,authentication));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExtendedAdDto> getAdById(@PathVariable Integer id) {

        return ResponseEntity.ok(adService.getAdById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAd(@PathVariable Integer id,
                                      Authentication authentication) {
        adService.deleteAd(id);
        return ResponseEntity.ok().build();
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
