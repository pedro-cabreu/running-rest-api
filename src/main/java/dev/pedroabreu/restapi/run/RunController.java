package dev.pedroabreu.restapi.run;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
// Seta o caminho base para o controlador
@RequestMapping("/api/runs")
public class RunController {

    private final RunRepository repository;

    public RunController(RunRepository repository) {
        this.repository = repository;
    }

    @GetMapping("")
    List<Run> findAll() {
        return repository.getRuns();
    }

    @GetMapping("/{id}")
    Run findById(@PathVariable Integer id) {

        Optional<Run> run = repository.findById(id);

        if (run.isEmpty()) {
            throw new RunNotFoundException();
        }

        return run.get();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    void create(@Valid @RequestBody Run run) {
        repository.addRun(run);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("")
    void update(@RequestBody Run run) {
        repository.updateRun(run);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete(@PathVariable Integer id) {
        repository.deleteRun(id);
    }
}
