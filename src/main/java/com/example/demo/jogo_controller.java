package com.example.demo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.login;
import com.example.demo.TokenResponse;
import com.example.demo.jogo;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


    @RestController
    public class jogo_controller {

        private List<jogo> jogos = new ArrayList<>();

        public jogo_controller() {
            jogos.add(new jogo(
                    1L,
                    "The Legend of Zelda",
                    "Aventura",
                    10,
                    "Um clássico absoluto."
            ));

            jogos.add(new jogo(
                    2L,
                    "FIFA 23",
                    "Esporte",
                    7,
                    "Bom para jogar com amigos."
            ));
        }

        // LOGIN
        @PostMapping("/login")
        public ResponseEntity<?> login(@RequestBody login
                                               request) {

            if (
                    request.getEmail().equals("usuario@esoft.com") &&
                            request.getPassword().equals("Abc123")
            ) {

                return ResponseEntity.ok(
                        new TokenResponse(UUID.randomUUID().toString())
                );
            }

            Object inválidas;
            return ResponseEntity.status(401).body("Credenciais invalidas");
        }

        // GET TODOS
        @GetMapping("/jogos")
        public List<jogo> listarJogos() {
            return jogos;
        }

        // GET POR ID
        @GetMapping("/jogos/{id}")
        public ResponseEntity<?> buscarPorId(@PathVariable Long id) {

            for (jogo jogo : jogos) {
                if (jogo.getId().equals(id)) {
                    return ResponseEntity.ok(jogo);
                }
            }

            return ResponseEntity.notFound().build();
        }

        // POST
        @PostMapping("/jogos")
        public ResponseEntity<jogo> criarJogo(@RequestBody jogo jogo) {

            jogo.setId((long) (jogos.size() + 1));

            jogos.add(jogo);

            return ResponseEntity.status(201).body(jogo);
        }

        // PUT
        @PutMapping("/jogos/{id}")
        public ResponseEntity<?> atualizar(
                @PathVariable Long id,
                @RequestBody jogo jogoAtualizado
        ) {

            for (int i = 0; i < jogos.size(); i++) {

                if (jogos.get(i).getId().equals(id)) {

                    jogoAtualizado.setId(id);

                    jogos.set(i, jogoAtualizado);

                    return ResponseEntity.ok(jogoAtualizado);
                }
            }

            return ResponseEntity.notFound().build();
        }

        // DELETE
        @DeleteMapping("/jogos/{id}")
        public ResponseEntity<Void> deletar(@PathVariable Long id) {

            jogos.removeIf(j -> j.getId().equals(id));

            return ResponseEntity.noContent().build();
        }
    }


