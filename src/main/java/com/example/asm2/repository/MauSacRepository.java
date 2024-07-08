package com.example.asm2.repository;

import com.example.asm2.entity.MauSac;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MauSacRepository extends JpaRepository<MauSac, Integer>{
}
