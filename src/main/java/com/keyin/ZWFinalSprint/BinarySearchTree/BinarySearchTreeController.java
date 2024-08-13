package com.keyin.ZWFinalSprint.BinarySearchTree;

import com.keyin.ZWFinalSprint.BinarySearchTree.BinarySearchTreeService;
import com.keyin.ZWFinalSprint.BinarySearchTree.BinarySearchTree;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@Controller
public class BinarySearchTreeController {

    @Autowired
    private BinarySearchTreeService bstService;

    @Autowired
    private BinarySearchTreeRepository bstRepository;

    @GetMapping("/enter-numbers")
    public String enterNumbers() {
        return "enter-numbers";
    }

    @PostMapping("/process-numbers")
    public String processNumbers(@RequestParam("numbers") String numbers, Model model) {
        try {
            // Parse the input string into a list of integers
            List<Integer> numbersList = Arrays.stream(numbers.split(","))
                    .map(String::trim) // Ensure whitespace is trimmed
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());

            // Create the binary search tree and store it in the database
            BinarySearchTree bst = bstService.createBinarySearchTree(numbersList);
            model.addAttribute("tree", bst);
            return "tree-result"; // Template name to display the result
        } catch (NumberFormatException e) {
            model.addAttribute("error", "Invalid input format. Please enter valid integers.");
            return "enter-numbers"; // Redirect back to the input form with an error
        }
    }

    @GetMapping("/previous-trees")
    public String previousTrees(Model model) {
        List<BinarySearchTree> trees = bstService.getAllBinarySearchTrees();
        model.addAttribute("trees", trees);
        return "previous-trees";
    }

    public class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;

        public TreeNode(int value) {
            this.value = value;
        }
    }

    public TreeNode insert(TreeNode root, int value) {
        if (root == null) {
            return new TreeNode(value);
        }
        if (value < root.value) {
            root.left = insert(root.left, value);
        } else if (value > root.value) {
            root.right = insert(root.right, value);
        }
        return root;
    }

    public String convertTreeToJson(TreeNode root) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(root);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "{}";
        }
    }
}