package com.sockMarket.controller;

import com.sockMarket.exception.NegativeQuantityException;
import com.sockMarket.exception.ValidationException;
import com.sockMarket.model.Sock;
import com.sockMarket.model.enums.Color;
import com.sockMarket.model.enums.Size;
import com.sockMarket.service.FileService;
import com.sockMarket.service.WarehouseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/socks")
@Tag(name = "Контроллер CRUD-операций для носков")
public class WarehouseController {

    private final WarehouseService service;
    private final FileService fileService;

    public WarehouseController(WarehouseService service, FileService fileService) {
        this.service = service;
        this.fileService = fileService;
    }

    @PostMapping
    @Operation(summary = "добавление партии носков на склад")
    public ResponseEntity<List<Sock>> addSocks(@RequestBody Sock sock) {
        try {

            service.addSocks(sock);
            return ResponseEntity.ok(service.getAllSocks());

        } catch (ValidationException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping
    @Operation(summary = "выдача носков со склада")
    public ResponseEntity<Sock> releaseSocks(@RequestBody Sock sock) {
        try {

            Sock remain = service.releaseSocks(sock);
            return ResponseEntity.ok(remain);

        } catch (ValidationException | NegativeQuantityException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping()
    @Operation(summary = "получение остатка носков на складе по заданным критериям")
    public ResponseEntity<List<Sock>> getByCotton(@RequestParam Color color,
                                                  @RequestParam Size size,
                                                  @RequestParam(required = false) Integer cottonMin,
                                                  @RequestParam(required = false) Integer cottonMax) {

        if (cottonMin == null) {
            cottonMin = 0;
        }

        if (cottonMax == null || cottonMax == 0) {
            cottonMax = 100;
        }

        List<Sock> socks = new ArrayList<>();
        try {
            socks = service.getByCotton(color, size, cottonMin, cottonMax);
        } catch (ValidationException e) {
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(socks);
    }

    @DeleteMapping
    public ResponseEntity<List<Sock>> deleteSock(@RequestBody Sock sock) {
        try {
            Sock remain = service.deleteSock(sock);
            return ResponseEntity.ok(service.getAllSocks());

        } catch (ValidationException | NegativeQuantityException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/all")
    @Operation(summary = "добавление списка носков")
    public ResponseEntity<List<Sock>> postSocks(@RequestBody List<Sock> socks) {
        service.getAllSocks().addAll(socks);
        return ResponseEntity.ok(service.getAllSocks());
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "загрузка файла с носками")
    public ResponseEntity<Void> uploadFIle(@RequestParam MultipartFile file) {
        if (!fileService.uploadDataFile(file)) {
            return ResponseEntity.internalServerError().build();
        } else {
            return ResponseEntity.ok().build();
        }
    }
}
