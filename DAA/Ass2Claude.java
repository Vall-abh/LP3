import java.util.*;

public class Ass2Claude {

    // A node in the Huffman tree
    static class Node implements Comparable<Node> {
        char ch;
        int freq;
        Node left, right;

        Node(char ch, int freq, Node left, Node right) {
            this.ch = ch;
            this.freq = freq;
            this.left = left;
            this.right = right;
        }

        public int compareTo(Node other) {
            return this.freq - other.freq;
        }
    }

    // Walk the tree: left = '0', right = '1'. Leaf nodes get a code.
    static void buildCodes(Node node, String code, Map<Character, String> codes) {
        if (node == null) return;
        if (node.left == null && node.right == null) {
            codes.put(node.ch, code.isEmpty() ? "0" : code);
            return;
        }
        buildCodes(node.left, code + "0", codes);
        buildCodes(node.right, code + "1", codes);
    }

    public static void main(String[] args) {
        String text = "huffman coding";

        // 1. Count frequency of each character
        Map<Character, Integer> freq = new TreeMap<>();
        for (char c : text.toCharArray()) {
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }

        // 2. Put every character in a min-heap (priority queue)
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (Map.Entry<Character, Integer> e : freq.entrySet()) {
            pq.add(new Node(e.getKey(), e.getValue(), null, null));
        }

        // 3. Greedy step: repeatedly merge the two lowest-frequency nodes
        while (pq.size() > 1) {
            Node a = pq.poll();
            Node b = pq.poll();
            pq.add(new Node('\0', a.freq + b.freq, a, b));
        }
        Node root = pq.poll();

        // 4. Generate codes from the tree
        Map<Character, String> codes = new TreeMap<>();
        buildCodes(root, "", codes);

        // 5. Encode the text
        StringBuilder encoded = new StringBuilder();
        for (char c : text.toCharArray()) {
            encoded.append(codes.get(c));
        }

        // Output
        System.out.println("Character codes:");
        for (Map.Entry<Character, String> e : codes.entrySet()) {
            System.out.println("'" + e.getKey() + "' -> " + e.getValue());
        }
        System.out.println("\nOriginal : " + text);
        System.out.println("Encoded  : " + encoded);
        System.out.println("Bits before: " + text.length() * 8);
        System.out.println("Bits after : " + encoded.length());
    }
}