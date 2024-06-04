package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOnUpdateAdDto;
import ru.skypro.homework.dto.ExtendedAdDto;
import ru.skypro.homework.service.AdsService;

import java.io.IOException;
import java.security.Principal;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
public class AdsController {
    private final AdsService adsService;
    Logger logger = LoggerFactory.getLogger(AdsController.class);
    @GetMapping
    public ResponseEntity<AdsDto> getAllAds() {
        logger.info("Метод getAllAds в контроллере");
        AdsDto allAds = adsService.getAllAds();
        return ResponseEntity.ok(allAds);
    }

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

    @GetMapping("{id}")
    public ResponseEntity<ExtendedAdDto> getAdById(@PathVariable Integer id) {
        logger.info("Метод getAdById в контроллере");
        return ResponseEntity.ok(adsService.getAdById(id));
    }


    @DeleteMapping("/{id}")

    public ResponseEntity<?> deleteAd(@PathVariable Integer id,
                                      Principal principal) {
        logger.info("Метод deleteAd в контроллере");
        adsService.deleteAd(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AdDto> updateAd(
            @PathVariable Long id,
            @RequestBody CreateOnUpdateAdDto createOnUpdateAdDto) {
        logger.info("Метод  updateAd в контроллере");
        return null;
    }

    @GetMapping("/me")
    public ResponseEntity<AdsDto> getAdsByAuthUser(
            Principal principal) {
        logger.info("Метод  getAdsByAuthUser в контроллере");
        return ResponseEntity.ok(adsService.getAdsByAuthUser(principal));
    }

    @PatchMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<byte[]> updateAdImage(@PathVariable("id") Long adId,
                                                @RequestParam("image") MultipartFile imageFile) {
        return null;
    }
}
