package com.example.justa.demo.repository;

import com.example.justa.demo.model.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProviderRepository extends JpaRepository<Provider, Long> {
}
