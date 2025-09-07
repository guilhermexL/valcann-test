package com.gui.api.service;

import com.gui.api.model.User;
import com.gui.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository repo;

    public Map<String, Object> getUsers(int page, int pageSize, String q, String role, Boolean isActive) {
        List<User> filtered = repo.findAll().stream()
                .filter(u -> (q == null || u.getName().toLowerCase().contains(q.toLowerCase()) || u.getEmail().toLowerCase().contains(q.toLowerCase())))
                .filter(u -> (role == null || u.getRole().equals(role)))
                .filter(u -> (isActive == null || u.getIs_active().equals(isActive)))
                .collect(Collectors.toList());

        int total = filtered.size();
        int from = (page - 1) * pageSize;
        List<User> paginated = filtered.subList(Math.min(from, total), Math.min(from + pageSize, total));

        Map<String, Object> pagination = Map.of("page", page, "page_size", pageSize, "total", total, "total_pages", (int) Math.ceil((double) total / pageSize));
        return Map.of("data", paginated, "pagination", pagination);
    }

    public User getUserById(Long id) {
        return repo.findById(id);
    }
}
