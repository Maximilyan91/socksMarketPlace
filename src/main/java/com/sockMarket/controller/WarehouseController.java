package com.sockMarket.controller;

import com.sockMarket.exception.NegativeQuantityException;
import com.sockMarket.exception.ValidationException;
import com.sockMarket.model.Sock;
import com.sockMarket.service.WarehouseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

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

            service.releaseSocks(sock);
            return ResponseEntity.ok(service.get(sock));

        } catch (ValidationException | NegativeQuantityException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
