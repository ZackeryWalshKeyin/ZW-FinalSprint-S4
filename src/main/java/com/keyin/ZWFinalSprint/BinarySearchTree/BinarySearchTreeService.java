package com.keyin.ZWFinalSprint.BinarySearchTree;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

@Service
public class BinarySearchTreeService {

    @Autowired
    private BinarySearchTreeRepository bstRepository;

    private static final Logger logger = LoggerFactory.getLogger(BinarySearchTreeService.class);

    public BinarySearchTree createBinarySearchTree(List<Integer> numbers) {
        BinarySearchTree bst = new BinarySearchTree();
        bst.setNumbers(numbers);
        bst.createBinarySearchTree(numbers);
        return bstRepository.save(bst);
    }

    public List<BinarySearchTree> getAllBinarySearchTrees() {
        List<BinarySearchTree> trees = bstRepository.findAll();
        for (BinarySearchTree tree : trees) {
            logger.info("BST ID: {}", tree.getId());
            logger.info("Numbers: {}", tree.getNumbers());
        }
        return trees;
    }

    public BinarySearchTree getTree(Long id) {
        return bstRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("BinarySearchTree not found with id: " + id));
    }
}