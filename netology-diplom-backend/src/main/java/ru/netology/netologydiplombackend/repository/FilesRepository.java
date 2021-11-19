package ru.netology.netologydiplombackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.netology.netologydiplombackend.model.Files;

import java.util.Optional;

@Repository
public interface FilesRepository extends JpaRepository<Files, Long> {
    Optional<Files> findByName(Files name);
}
