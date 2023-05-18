package nl.novi.backendspringtechiteasy.controller;

import nl.novi.backendspringtechiteasy.dto.CIModuleDto;
import nl.novi.backendspringtechiteasy.service.CIModuleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ci-modules")
public class CIModuleController {
    private final CIModuleService ciModuleService;

    public CIModuleController(CIModuleService ciModuleService) {
        this.ciModuleService = ciModuleService;
    }

    @PostMapping
    public ResponseEntity<Object> createCIModule(@RequestBody CIModuleDto ciModuleDto, BindingResult br) {
        if (br.hasFieldErrors()) {
            return ResponseEntity.badRequest().body(getBindingResult(br));
        } else {
            Long newId = ciModuleService.createCIModule(ciModuleDto);
            return new ResponseEntity<>(newId, HttpStatus.CREATED);
        }
    }

    @GetMapping
    public ResponseEntity<Iterable<CIModuleDto>> getAllCIModules() {
        return ResponseEntity.ok(ciModuleService.getAllCIModules());
    }



    private String getBindingResult(BindingResult br) {
        StringBuilder sb = new StringBuilder();
        for (FieldError fe : br.getFieldErrors()) {
            sb.append(fe.getField()).append(": ").append(fe.getDefaultMessage()).append("\n");
        }
        return sb.toString();
    }
}
