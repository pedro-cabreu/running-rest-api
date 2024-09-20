package dev.pedroabreu.restapi.run;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// Repository é uma anotação que indica que a classe anotada é um repositório ou um mecanismo de armazenamento.
@Repository
public class RunRepository {

    public List<Run> runs = new ArrayList<>();

    public List<Run> getRuns() {
        return runs;
    }

    public Optional<Run> findById(Integer id) {
        return runs.stream().filter(run -> run.id().equals(id)).findFirst();
    }

    public void addRun(Run run) {
        runs.add(run);
    }

    void updateRun(Run run) {
        Optional<Run> existingRun = findById(run.id());

        if (existingRun.isPresent()) {
            runs.set(runs.indexOf(existingRun.get()), run);
        }else{

            throw new RuntimeException("Run not found");
        }
    }

    void deleteRun(Integer id) {
        Optional<Run> existingRun = findById(id);

        if (existingRun.isPresent()) {
            runs.remove(existingRun.get());
        }else{

            throw new RuntimeException("Run not found");
        }
    }

    // PostConstruct é uma anotação que é usada em um método que precisa ser executado após a injeção de dependência ser concluída para executar qualquer inicialização.
    @PostConstruct
    private void init() {
        runs.add(new Run(1, "Morning Run", LocalDateTime.now(), LocalDateTime.now().plusHours(1), 10, Location.OUTDOOR));
        runs.add(new Run(2, "Evening Run", LocalDateTime.now(), LocalDateTime.now().plusHours(1), 15, Location.OUTDOOR));
    }
}
