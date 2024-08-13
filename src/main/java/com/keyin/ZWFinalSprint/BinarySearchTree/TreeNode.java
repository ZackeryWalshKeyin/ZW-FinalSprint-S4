package com.keyin.ZWFinalSprint.BinarySearchTree;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TreeNode {
    @JsonProperty("value")
    private Integer value;

    @JsonProperty("left")
    private TreeNode left;

    @JsonProperty("right")
    private TreeNode right;

    public TreeNode() {}

    public TreeNode(Integer value) {
        this.value = value;
    }

    // Getters and setters
    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }
}