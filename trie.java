import java.util.*;

class TrieNode {
    TrieNode[] links = new TrieNode[26];
    boolean isEnd = false;
    boolean containsKey(char ch) {
        return links[ch - 'a'] != null;
    }
    TrieNode get(char ch) {
        return links[ch - 'a'];
    }
    void put(char ch, TrieNode node) {
        links[ch - 'a'] = node;
    }
    void setEnd() {
        isEnd = true;
    }
    boolean isEnd() {
        return isEnd;
    }
}

class Trie {
    TrieNode root = new TrieNode();

    public void insert(String word) {
        TrieNode node = root;
        for(int i=0;i<word.length();i++) {
            char ch = word.charAt(i);
            if(!node.containsKey(ch)) node.put(ch,new TrieNode());
            node = node.get(ch);
        }
        node.setEnd();
    }

    public boolean search(String word) {
        TrieNode node = root;
        for(int i=0;i<word.length();i++) {
            char ch = word.charAt(i);
            if(!node.containsKey(ch)) return false;
            node = node.get(ch);
        }
        return node.isEnd();
    }

    public List<String> getAllWords() {
        List<String> res = new ArrayList<>();
        dfs(root,"",res);
        return res;
    }

    public List<String> getWordsWithPrefix(String prefix) {
        List<String> res = new ArrayList<>();
        TrieNode node = root;
        for(int i=0;i<prefix.length();i++) {
            char ch = prefix.charAt(i);
            if(!node.containsKey(ch)) return res;
            node = node.get(ch);
        }
        dfs(node,prefix,res);
        return res;
    }

    private void dfs(TrieNode node,String path,List<String> res) {
        if(node.isEnd()) res.add(path);
        for(char ch='a';ch<='z';ch++) {
            if(node.containsKey(ch)) dfs(node.get(ch),path+ch,res);
        }
    }
}

class Main{
    public static void main(String Args[]){
        Trie trie = new Trie();
        String[] words = {"abc","abcd","abcde","fsfsdfsd","harshith"};
        for(String word : words) trie.insert(word);
        if(trie.search("abc")) System.out.println("true");
        else System.out.println("false");
        List<String>allWords = trie.getAllWords();
        List<String>prefixWords = trie.getWordsWithPrefix("abc");
        for(String word:allWords){
            System.out.print(word+" ");
        }
        System.out.println();
        for(String word:prefixWords){
            System.out.print(word+" ");
        }
        System.out.println();
    }
}