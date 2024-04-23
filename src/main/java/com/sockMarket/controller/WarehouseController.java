package com.sockMarket.controller;

import com.sockMarket.exception.NegativeQuantityException;
import com.sockMarket.exception.ValidationException;
import com.sockMarket.model.Sock;
import com.sockMarket.model.enums.Color;
import com.sockMarket.model.enums.Size;
import com.sockMarket.service.WarehouseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/socks")
@Tag(name = "Контроллер CRUD-операций для носков")
public class WarehouseController {

    private final WarehouseService service;

    public WarehouseController(WarehouseService service) {
        this.service = service;
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

    @PostMapping("/all")
    @Operation(summary = "добавление списка носков")
    public ResponseEntity<List<Sock>> postSocks(@RequestBody List<Sock> socks) {
        service.getAllSocks().addAll(socks);
        return ResponseEntity.ok(service.getAllSocks());
    }
}
