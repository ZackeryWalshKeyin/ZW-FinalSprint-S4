package com.keyin.ZWFinalSprint.BinarySearchTree;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BinarySearchTreeRepository extends JpaRepository<BinarySearchTree, Long> {
    // Additional custom queries can be defined here if needed
}