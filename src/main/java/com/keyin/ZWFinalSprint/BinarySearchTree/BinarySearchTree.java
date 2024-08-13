package com.keyin.ZWFinalSprint.BinarySearchTree;

import jakarta.persistence.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

@Entity
@Table(name = "binary_search_tree")
public class BinarySearchTree {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(name = "tree_structure", columnDefinition = "TEXT")
    private String treeStructure;

    @ElementCollection
    @CollectionTable(name = "tree_numbers", joinColumns = @JoinColumn(name = "bst_id"))
    @Column(name = "number")
    private List<Integer> numbers;

    @Transient
    private TreeNode root; // This field will not be persisted, but used for in-memory operations

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public void setNumbers(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public String getTreeStructure() {
        return treeStructure;
    }

    public void setTreeStructure(String treeStructure) {
        this.treeStructure = treeStructure;
    }

    public TreeNode getRoot() {
        return deserializeTree(treeStructure);
    }

    public void setRoot(TreeNode root) {
        this.root = root;
        this.treeStructure = serializeTree(root);
    }

    // Method to construct the tree
    public void createBinarySearchTree(List<Integer> numbers) {
        for (Integer number : numbers) {
            root = insertIntoTree(root, number);
        }
        this.treeStructure = serializeTree(root); // Update the serialized tree structure after building the tree
    }

    // Method to insert a number into the tree
    private TreeNode insertIntoTree(TreeNode root, Integer number) {
        if (root == null) {
            return new TreeNode(number);
        }

        if (number < root.getValue()) {
            root.setLeft(insertIntoTree(root.getLeft(), number));
        } else {
            root.setRight(insertIntoTree(root.getRight(), number));
        }

        return root;
    }

    // Serialization and Deserialization

    private String serializeTree(TreeNode root) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(root);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    private TreeNode deserializeTree(String treeStructure) {
        if (treeStructure == null || treeStructure.isEmpty()) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(treeStructure, TreeNode.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}