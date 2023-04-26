package nl.novi.backendspringtechiteasy.controller;

import nl.novi.backendspringtechiteasy.exception.RecordNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("televisions")
public class TelevisionController {

    private List<String> televisionDatabase = new ArrayList<>();

    @GetMapping
    public ResponseEntity<List<String>> getTelevisions() {
        return new ResponseEntity<>(televisionDatabase, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getTelevision(@PathVariable int id) {
        if (id >= 0 && id < televisionDatabase.size()) {
            String television = televisionDatabase.get(id);
            return new ResponseEntity<>(television, HttpStatus.OK);
        } else {
            throw new RecordNotFoundException("ID cannot be found");
        }
    }

    @PostMapping
    public ResponseEntity<String> addTelevision(@RequestParam String television) {
        televisionDatabase.add(television);
        return new ResponseEntity<>(television, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateTelevision(@PathVariable int id, @RequestParam String television) {
        if (id >= 0 && id < televisionDatabase.size()) {
            televisionDatabase.set(id, television);
            return new ResponseEntity<>(television, HttpStatus.OK);
        } else if (id >= televisionDatabase.size()) {
            throw new IndexOutOfBoundsException("ID out of bounds");
        } else {
            throw new RecordNotFoundException("ID cannot be found");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTelevision(@PathVariable int id) {
        if (id >= 0 && id < televisionDatabase.size()) {
            televisionDatabase.remove(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            throw new RecordNotFoundException("ID cannot be found");
        }
    }
}
