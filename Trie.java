import java.util.*;

class Trie {
    static class Node {
        Node[] links = new Node[26];
        boolean isEnd;

        boolean has(char ch) {
            return links[ch - 'a'] != null;
        }

        Node get(char ch) {
            return links[ch - 'a'];
        }

        void put(char ch, Node node) {
            links[ch - 'a'] = node;
        }
    }

    private final Node root = new Node();

    public void insert(String word) {
        Node node = root;
        for (char ch : word.toCharArray()) {
            if (!node.has(ch)) node.put(ch, new Node());
            node = node.get(ch);
        }
        node.isEnd = true;
    }

    public boolean search(String word) {
        Node node = root;
        for (char ch : word.toCharArray()) {
            if (!node.has(ch)) return false;
            node = node.get(ch);
        }
        return node.isEnd;
    }

    public List<String> getAllWords() {
        List<String> res = new ArrayList<>();
        dfs(root, "", res);
        return res;
    }

    public List<String> getWordsWithPrefix(String prefix) {
        List<String> res = new ArrayList<>();
        Node node = root;
        for (char ch : prefix.toCharArray()) {
            if (!node.has(ch)) return res;
            node = node.get(ch);
        }
        dfs(node, prefix, res);
        return res;
    }

    private void dfs(Node node, String path, List<String> res) {
        if (node.isEnd) res.add(path);
        for (char ch = 'a'; ch <= 'z'; ch++) {
            if (node.has(ch)) dfs(node.get(ch), path + ch, res);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Trie trie = new Trie();
        String[] words = {"abc", "abcd", "abcde", "fsfsdfsd"};
        for (String word : words) trie.insert(word);

        System.out.println(trie.search("abc") ? "true" : "false");

        List<String> allWords = trie.getAllWords();
        List<String> prefixWords = trie.getWordsWithPrefix("abc");

        allWords.forEach(w -> System.out.print(w + " "));
        System.out.println();
        prefixWords.forEach(w -> System.out.print(w + " "));
        System.out.println();
    }
}
