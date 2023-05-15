package nl.novi.backendspringtechiteasy.controller;

import jakarta.validation.Valid;
import nl.novi.backendspringtechiteasy.dto.TelevisionDto;
import nl.novi.backendspringtechiteasy.exception.RecordNotFoundException;
import nl.novi.backendspringtechiteasy.model.Television;
import nl.novi.backendspringtechiteasy.repository.TelevisionRepository;
import nl.novi.backendspringtechiteasy.service.TelevisionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("televisions")
public class TelevisionController {

    private final TelevisionService service;

    public TelevisionController(TelevisionService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Iterable<TelevisionDto>> getTelevisions() {
        return ResponseEntity.ok(service.getTelevisions());
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<Optional<Television>> getTelevision(@PathVariable Long id) {
//        if (id >= 0 && id < repos.count()) {
//            return ResponseEntity.ok(repos.findById(id));
//        } else {
//            throw new RecordNotFoundException("ID cannot be found");
//        }
//    }

//    @PostMapping
//    public ResponseEntity<Television> addTelevision(@RequestBody Television television) {
//        repos.save(television);
//        return ResponseEntity.ok(television);
//    }

    @PostMapping
    public ResponseEntity<Object> addTelevision(@Valid @RequestBody TelevisionDto televisionDto, BindingResult br) {
        if (br.hasFieldErrors()) {
            StringBuilder sb = new StringBuilder();
            for (FieldError fe : br.getFieldErrors()) {
                sb.append(fe.getField() + ": ");
                sb.append(fe.getDefaultMessage());
                sb.append("\n");
            }
            return ResponseEntity.badRequest().body(sb.toString());
        } else {
            Long newId = service.saveTelevision(televisionDto);
            URI uri = URI.create(ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/" + newId).toUriString());
            return ResponseEntity.created(uri).body(newId);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Television> updateTelevision(@PathVariable Long id, @RequestBody TelevisionDto televisionDto) {


//        if (id >= 0 && id < repos.count()) {
//            repos.save(t);
//            return ResponseEntity.ok(t);
//        } else if (id >= repos.count()) {
//            throw new IndexOutOfBoundsException("ID out of bounds");
//        } else {
//            throw new RecordNotFoundException("ID cannot be found");
//        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTelevision(@PathVariable Long id) {
        if (id >= 0 && id < repos.count()) {
            repos.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            throw new RecordNotFoundException("ID cannot be found");
        }
    }
}
