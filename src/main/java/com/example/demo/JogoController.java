package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

@CrossOrigin("*")
@RestController
public class JogoController {

    private final List<Jogo> jogos = new ArrayList<>();
    private final AtomicLong identity = new AtomicLong(2);

    public JogoController() {
        jogos.add(new Jogo(1L, "The Legend of Zelda", "Aventura", 10, "Um clássico absoluto."));
        jogos.add(new Jogo(2L, "FIFA 23", "Esporte", 7, "Bom para jogar com amigos."));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Login request) {
        if ("usuario@esoft.com".equals(request.getEmail()) && "Abc123".equals(request.getPassword())) {
            return ResponseEntity.ok(new TokenResponse(UUID.randomUUID().toString()));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas");
    }

    @GetMapping("/jogos")
    public ResponseEntity<List<Jogo>> listarJogos() {
        return ResponseEntity.ok(jogos);
    }

    @GetMapping("/jogos/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        for (Jogo jogo : jogos) {
            if (jogo.getId().equals(id)) {
                return ResponseEntity.ok(jogo);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/jogos")
    public ResponseEntity<?> criarJogo(@RequestBody Jogo jogo) {
        if (jogo.getNome() == null || jogo.getTipo() == null || jogo.getReview() == null) {
            return ResponseEntity.badRequest().body("Todos os campos são obrigatórios");
        }

        jogo.setId(identity.incrementAndGet());
        jogos.add(jogo);
        return ResponseEntity.status(HttpStatus.CREATED).body(jogo);
    }

    @PutMapping("/jogos/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Jogo jogoAtualizado) {
        if (jogoAtualizado.getNome() == null || jogoAtualizado.getTipo() == null || jogoAtualizado.getReview() == null) {
            return ResponseEntity.badRequest().body("Todos os campos são obrigatórios");
        }

        for (int i = 0; i < jogos.size(); i++) {
            if (jogos.get(i).getId().equals(id)) {
                jogoAtualizado.setId(id);
                jogos.set(i, jogoAtualizado);
                return ResponseEntity.ok(jogoAtualizado);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/jogos/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        boolean removido = jogos.removeIf(j -> j.getId().equals(id));
        if (removido) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}