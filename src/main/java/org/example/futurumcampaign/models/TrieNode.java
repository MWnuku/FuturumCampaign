package org.example.futurumcampaign.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
@Getter
@Setter
@NoArgsConstructor
public class TrieNode {

	private char c;
	private HashMap<Character, TrieNode> children = new HashMap<>();
	private boolean isLeaf;


	public TrieNode(char c){
		this.c = c;
	}

	public boolean isLeaf() {
		return isLeaf;
	}

	public void setLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}
}
