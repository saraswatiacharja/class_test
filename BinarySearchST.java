import java.util.*;

public class BinarySearchST<Key extends Comparable<Key>, Value> {
    private Node root;         

    private class Node {
        private Key key;        
        private Value val;        
        private Node left, right;
        int size;      
        public Node(Key key, Value val, int size) {
            this.key = key;
            this.val = val;
            this.size =0;
        }
    }
    public int size(){
        return size(root);    
    }
    private int size(Node x) {
        if(x!=null){
            return x.size;
        }
        return 0;  
    }
    public Value get(Key key) {
        return get(root,key);    
    }
    private Value get(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        
        if (cmp < 0)
            return get(x.left,key);
        else if (cmp > 0)
            return get(x.right,key);  
        return x.val;
    }
    public void put(Key key, Value val) {
        root = put(root, key, val);
    }
    private Node put(Node x, Key key, Value val) {
        if (x == null) return new Node(key, val,1);        
        int cmp = key.compareTo(x.key);
        if (cmp == 0) 
        {
            x.val = val;
            return x;
        } 
        else if (cmp < 0)
        {
            x.left = put(x.left, key, val);
        } 
        else if (cmp > 0) 
        {
            x.right = put(x.right, key, val);
        }
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }   
    // private Node put(Node x, Key key, Value val) {
        
    // }
    public Key min() {
        if (root==null) {
            return null;
        }
        return min(root).key;
       
    } 

    private Node min(Node x) { 
        if (x.left==null) {
            return x;
            
        }
         return min(x.left);
    } 
    public Key floor(Key key) {
        Node n=floor(root,key);
       if (n==null) {
        System.out.println(" no exist");
        }
        else{
            return key;            
        }
        return n.key;
        
    }
    private Node floor(Node x, Key key){
        if (x == null) return null;
        
        int cmp = key.compareTo(x.key);
        if (cmp < 0) 
            return floor(x.left, key);
        else if (cmp > 0) {
            Node t = floor(x.right, key);
            if (t == null) 
                return x;
            else
                return t;
        }
        return x;

    }

   public Key select(int rank) {
        return select(root, rank);
    }

    private Key select(Node x, int rank) {
        if (x == null) return null;
        
        int t = size(x.left);
        if(rank < t) return select(x.left, rank);
        else if (rank > t)
            return select(x.right, rank-t-1);
        return x.key;
    }


    public void delete(Key key){
        root=delete(root,key);
    }


    private Node delete(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        
        if (cmp > 0) 
        {
            x.right = delete(x.right, key);
        } else if (cmp < 0)
        {
            x.left = delete(x.left, key);
        } 
        else 
        { 
            if (x.left == null) 
                return x.right;
            else if (x.right == null) 
                return x.left;
            else {
                
                Node t = x.right;
                x = min(t.right);
                x.right = delete(t.right,key);
                x.left = t.left; 
            }
        }
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }
    public Iterable<Key> keys(Key lo, Key hi) {
        LinkedList<Key> link=new LinkedList<Key>();
        keys(root,link,lo,hi);
        return link;
     
    } 

    private void keys(Node x,LinkedList<Key> link, Key lo, Key hi) {
    if(x==null) return;
    int low=lo.compareTo(x.key);
    int high=hi.compareTo(x.key);

    if(low<0) keys(x.left,link,lo,hi);
    if(low<= 0 && high >=0) link.add(x.key);
    if(high>0) keys(x.right,link,lo,hi); 
        
    }
    public static void main(String[] args) { 

        BinarySearchST <String, Integer> obj=new BinarySearchST<String, Integer>();
        obj.put("Ada",1);
        obj.put("Ballerina",3);
        System.out.println(obj.get("Ada"));
        obj.put("Html",5);
        obj.put("Java",7);
        System.out.println(obj.get("Java"));
        System.out.println(obj.size());
        System.out.println(obj.min());
        System.out.println(obj.floor("Ballerina"));
        System.out.println(obj.select(3));
        System.out.println(obj.keys("Ada","Java"));
        obj.put("Java",8);
        obj.put("Dart",9);
        System.out.println(obj.get("Java"));
        System.out.println(obj.size());
       
        System.out.println(obj.keys("Ballerina","Java"));
        obj.delete("Java");



       
       
    }
}