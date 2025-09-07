package com.gui.api.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gui.api.model.User;
import org.springframework.stereotype.Repository;

import java.io.InputStream;
import java.util.List;

@Repository
public class UserRepository {

    private final List<User> users; // Uso 'final' porque a lista será carregada uma vez e não mudará mais

    public UserRepository() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            InputStream inputStream = getClass().getResourceAsStream("/mock-users.json");
            if (inputStream == null) {
                throw new RuntimeException("Não foi possível encontrar o arquivo 'mock-users.json'");
            }
            // Lê os dados do arquivo (inputStream) e converte em uma Lista de Users
            this.users = mapper.readValue(inputStream, new TypeReference<List<User>>() {});

        } catch (Exception e) {
            throw new RuntimeException("Falha ao carregar e processar o arquivo de usuários mock.", e);
        }
    }

    public List<User> findAll() {
        return this.users;
    }

    public User findById(Long id) {
        return this.users.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}