package nl.novi.backendspringtechiteasy.controller;

import nl.novi.backendspringtechiteasy.dto.WallBracketDto;
import nl.novi.backendspringtechiteasy.service.WallBracketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wall-brackets")
public class WallBracketController {

    private final WallBracketService wallBracketService;

    public WallBracketController(WallBracketService wallBracketService) {
        this.wallBracketService = wallBracketService;
    }

    @PostMapping
    public ResponseEntity<Object> createWallBracket(@RequestBody WallBracketDto wallBracketDto, BindingResult br) {
        if (br.hasFieldErrors()) {
            return ResponseEntity.badRequest().body(getBindingResult(br));
        } else {
            Long newId = wallBracketService.createWallBracket(wallBracketDto);
            return new ResponseEntity<>(newId, HttpStatus.CREATED);
        }
    }

    @GetMapping
    public ResponseEntity<Iterable<WallBracketDto>> getAllWallBrackets() {
        return ResponseEntity.ok(wallBracketService.getAllWallBrackets());
    }

    @GetMapping("/{id}")
    public ResponseEntity<WallBracketDto> getWallBracket(@PathVariable Long id) {
        return ResponseEntity.ok(wallBracketService.getWallBracket(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateWallBracket(@PathVariable Long id, @RequestBody WallBracketDto wallBracketDto, BindingResult br) {
        if (br.hasFieldErrors()) {
            return ResponseEntity.badRequest().body(getBindingResult(br));
        } else {
            wallBracketService.updateWallBracket(id, wallBracketDto);
            return ResponseEntity.ok("Remote controller was updated");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteWallBracket(@PathVariable Long id) {
        wallBracketService.deleteWallBracket(id);
        return ResponseEntity.noContent().build();
    }

    private String getBindingResult(BindingResult br) {
        StringBuilder sb = new StringBuilder();
        for (FieldError fe : br.getFieldErrors()) {
            sb.append(fe.getField()).append(": ").append(fe.getDefaultMessage()).append("\n");
        }
        return sb.toString();
    }
}
