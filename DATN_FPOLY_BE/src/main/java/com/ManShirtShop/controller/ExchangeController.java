package com.ManShirtShop.controller;

import com.ManShirtShop.dto.exchange.EchangeRequestAdmin;
import com.ManShirtShop.service.exchange.ExchangeService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/exchange")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "exchange api")
public class ExchangeController {
    @Autowired
    ExchangeService exchangeService;

    @PostMapping(value = "create", consumes = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> create(@RequestBody EchangeRequestAdmin requet) {
        return ResponseEntity.ok(exchangeService.create(requet));
    }

    @PostMapping(value = "updateStatusGiaoHang")
    public ResponseEntity<?> updatStatusGiaoHang(@RequestParam Integer id) {
        return ResponseEntity.ok(exchangeService.updateStausGiaoHang(id));
    }

    @PostMapping(value = "updateStatusHuy")
    public ResponseEntity<?> updateStatusHuy(@RequestParam Integer id) {
        return ResponseEntity.ok(exchangeService.updateStausHuy(id));
    }

    @PostMapping(value = "updateStatusGiaoThatBai")
    public ResponseEntity<?> updateStatusGiaoThatBai(@RequestParam Integer id) {
        return ResponseEntity.ok(exchangeService.updateStausGiaoThatBai(id));
    }

    @PostMapping(value = "updateStatusThanhCong")
    public ResponseEntity<?> updateStatusThanhCong(@RequestParam Integer id) {
        return ResponseEntity.ok(exchangeService.updateStausThanhCong(id));
    }

}
