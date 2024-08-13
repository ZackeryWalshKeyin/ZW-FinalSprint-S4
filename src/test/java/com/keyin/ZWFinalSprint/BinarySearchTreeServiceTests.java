package com.keyin.ZWFinalSprint;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.keyin.ZWFinalSprint.BinarySearchTree.BinarySearchTree;
import com.keyin.ZWFinalSprint.BinarySearchTree.BinarySearchTreeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

@DataJpaTest
public class BinarySearchTreeServiceTests {

    @Autowired
    private BinarySearchTreeService bstService;

    @Test
    public void testCreateAndRetrieveBinarySearchTree() {
        List<Integer> numbers = Arrays.asList(4, 3, 2, 1);
        BinarySearchTree bst = bstService.createBinarySearchTree(numbers);

        BinarySearchTree retrievedBst = bstService.getTree(bst.getId());

        assertEquals(numbers, retrievedBst.getNumbers(), "The numbers should match");
    }

    @Test
    public void testTreeStructureSerialization() {
        List<Integer> numbers = Arrays.asList(5, 3, 7, 2, 4);
        BinarySearchTree bst = bstService.createBinarySearchTree(numbers);

        BinarySearchTree retrievedBst = bstService.getTree(bst.getId());

        assertNotNull(retrievedBst.getTreeStructure(), "Tree structure should not be null");
    }

    @Test
    public void testTreeInitialization() {
        List<Integer> numbers = Arrays.asList(8, 4, 12, 2, 6);
        BinarySearchTree bst = bstService.createBinarySearchTree(numbers);

        BinarySearchTree retrievedBst = bstService.getTree(bst.getId());

        assertNotNull(retrievedBst.getRoot(), "The root of the tree should not be null");
    }
}
