/*
Design an in-memory file system to simulate the following functions:

ls: Given a path in string format. If it is a file path, return a list that only 
contains this file's name. If it is a directory path, return the list of file 
and directory names in this directory. Your output (file and directory names together) 
should in lexicographic order.

mkdir: Given a directory path that does not exist, you should make a new directory 
according to the path. If the middle directories in the path don't exist either, 
you should create them as well. This function has void return type.

addContentToFile: Given a file path and file content in string format. If the file doesn't 
exist, you need to create that file containing given content. If the file already exists, 
you need to append given content to original content. This function has void return type.

readContentFromFile: Given a file path, return its content in string format.

Example:
Input: 
["FileSystem","ls","mkdir","addContentToFile","ls","readContentFromFile"]
[[],["/"],["/a/b/c"],["/a/b/c/d","hello"],["/"],["/a/b/c/d"]]
Output:
[null,[],null,null,["a"],"hello"]
Explanation:
See leetcode/filesystem_example.png

Note:
You can assume all file or directory paths are absolute paths which begin with / and 
do not end with / except that the path is just "/".
You can assume that all operations will be passed valid parameters and users will not 
attempt to retrieve file content or list a directory or file that does not exist.
You can assume that all directory names and file names only contain lower-case letters, 
and same names won't exist in the same directory.
*/

import java.util.*;

public class FileSystem {
    private final Map<String, Object> fileMap = new HashMap<>();
    
    public FileSystem() {
        fileMap.put("/", new TreeSet<>());
    }
    
    //O(l + n) time, l is the length of path, n is the number of entries in the directory
    public List<String> ls(String path) {
        Object directoryContent = fileMap.get(path);
        List<String> result = null;
        if (directoryContent != null) {
            if (directoryContent instanceof SortedSet) {
                result = new ArrayList<>((SortedSet<String>)directoryContent);
            } else {
                result = new ArrayList<>();
                result.add(path.substring(path.lastIndexOf('/') + 1));
            }
        }
        return result;
    }
    
    //O(l*(l + logn)) time, l is the length of path, n is the average number of entries
    //in each directory
    public void mkdir(String path) {
        String[] parts = path.split("/");
        StringBuilder curPathSb = new StringBuilder();
        String parentDir = "/";
        for (int i = 1; i < parts.length; ++i) {
            curPathSb.append("/");
            curPathSb.append(parts[i]);
            String curDir = curPathSb.toString();
            if (!fileMap.containsKey(curDir)) {
                ((SortedSet)fileMap.get(parentDir)).add(parts[i]);
                fileMap.put(curDir, new TreeSet<>());
            }
            parentDir = curDir;
        }
    }
    
    //O(l^2 + logn + lc) time, l is the length of filePath, lc is the length of content,
    //n is the number of entries in the parent directory
    public void addContentToFile(String filePath, String content) {
        String[] parts = filePath.split("/");
        StringBuilder curPathSb = new StringBuilder();
        String parentDir = "/";
        for (int i = 1; i < parts.length - 1; ++i) {
            curPathSb.append("/");
            curPathSb.append(parts[i]);
            String curDir = curPathSb.toString();
            if (!fileMap.containsKey(curDir)) {
                return;
            }
            parentDir = curDir;
        }
        Object fileContent = fileMap.get(filePath);
        if (fileContent instanceof SortedSet) {
            return;
        }
        if (fileContent == null) {
            fileContent = new StringBuilder();
            ((SortedSet)fileMap.get(parentDir)).add(parts[parts.length - 1]);
            fileMap.put(filePath, fileContent);
        }
        ((StringBuilder)fileContent).append(content);
    }
    
    //O(l + lc) time, l is the length of filePath, lc is the length of file content
    public String readContentFromFile(String filePath) {
        Object sb = fileMap.get(filePath);
        if (sb == null || !(sb instanceof StringBuilder)) {
            return null;
        }
        return sb.toString();
    }

//--------------------------Second try ----------------------------------------------------

    //The above solution cannot create a file that has same name as a directory.
    //If we want to do that, we should store FileNode in the TreeSet instead of
    //just the file name, to consider both file name and file type.
    private static class FileNode implements Comparable<FileNode> {
        final String name;
        private final Set<FileNode> fNodes = new TreeSet<>();
        private final StringBuilder content = new StringBuilder();
        final int type; //0 is a directory, 1 is a file
        
        FileNode(String name, int type) {
            this.name = name;
            this.type = type;
            if (type == 1) {
                fNodes.add(this);
            }
        }
        
        @Override
        public int compareTo(FileNode fn) {
            int diff = name.compareTo(fn.name);
            if (diff != 0) {
                return diff;
            }
            return Integer.compare(type, fn.type);
        }
        
        //O(n) time, if fNodes is BST.
        List<String> ls() {
            List<String> result = new ArrayList<>();
            for (FileNode fn : fNodes) {
                result.add(fn.name);
            }
            return result;
        }
        
        void addContent(String name, int type) {
            if (this.type == 0) {
                fNodes.add(new FileNode(name, type));
            }
        }
        
        void addContent(String str) {
            if (type == 1) {
                content.append(str);
            }
        }
        
        String getContent() {
            if (type == 0) {
                return null;
            }    
            return content.toString();
        }
        
        //For debugging
        public String toString() {
            return "(name = " + name + ", " + type + ", " + ls().toString() + ", " + content
                + ")";
        }
    }
    
    final Map<String, FileNode> fileMap = new HashMap<>();
    
    public FileSystem() {
        fileMap.put("/", new FileNode("/", 0));
    }
    
    //O(l + n) time, l is the length of path, n is the number of entries in the directory
    public List<String> ls(String path) {
        FileNode fn = fileMap.get(path);
        if (fn == null) {
            return new ArrayList<>();
        }
        return fn.ls();
    }
    
    //O(l*(l + logn)) time, l is the length of path, n is the average number of entries
    //in each directory
    public void mkdir(String path) {
        String[] dirs = path.split("/");
        String curPath = "";
        FileNode preNode = fileMap.get("/");
        for (int i = 1; i < dirs.length; ++i) {
            curPath += "/" + dirs[i];
            FileNode curNode = fileMap.get(curPath);
            if (curNode == null) {
                curNode = new FileNode(dirs[i], 0);
                preNode.addContent(dirs[i], 0);
                fileMap.put(curPath, curNode);
            }
            preNode = curNode;
        }
    }
    
    //O(l + logn) time
    public void addContentToFile(String filePath, String content) {
        int lastSlashId = filePath.lastIndexOf("/");
        String directoryPath = (lastSlashId == 0) ? "/" : filePath.substring(0, lastSlashId);
        FileNode dirNode = fileMap.get(directoryPath);
        if (dirNode == null) {
            return;
        }
        String fileName = filePath.substring(lastSlashId + 1);
        FileNode fileNode = fileMap.get(filePath);
        if (fileNode == null) {
            fileNode = new FileNode(fileName, 1);
            fileMap.put(filePath, fileNode);
            dirNode.addContent(fileName, 1);
        }
        fileNode.addContent(content);
    }
    
    //O(l) time
    public String readContentFromFile(String filePath) {
        FileNode fn = fileMap.get(filePath);
        if (fn == null) {
            return null;
        }
        return fn.getContent();
    }
    
// ------------------------ Another way that store linked list instead of map -------------

    private static abstract class Node implements Comparable<Node> {
        final String name;
        final int type;  //0 is a directory, 1 is a file
        Node(String name, int type) {
            this.name = name;
            this.type = type;
        }
    
        abstract List<String> ls();
        void addNode(Node node){}
        void addContent(String str){}
        String getContent() {
            return null;
        }
                        
        @Override
        public int compareTo(Node node) {
            int diff = name.compareTo(node.name);
            if (diff != 0) {
                return diff;
            }
            return Integer.compare(type, node.type);
        }
        
        @Override
        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (other instanceof Node) {
                Node otherNode = (Node)other;
                return Objects.equals(this.name, otherNode.name) && this.type == otherNode.type;
            }
            return false;
        }
        
        @Override
        public int hashCode() {
            return 31 * type + name.hashCode();
        }
        
        public String toString() {
            return "(" + name + ", " + type + ")";
        }
    }
    
    private static class DirectoryNode extends Node {
        private final TreeSet<Node> nodes = new TreeSet<>();
        
        DirectoryNode(String name) {
            super(name, 0);
        }
        
        List<String> ls() {
            List<String> result = new ArrayList<>();
            for (Node node : nodes) {
                result.add(node.name);
            }
            return result;
        }
        
        void addNode(Node node) {
            nodes.add(node);
        }
        
        Node getNode(String name, int type) {
            Node node = (type == 0) ? new DirectoryNode(name) : new FileNode(name);
            Node floorNode = nodes.floor(node);
            if (floorNode == null || !floorNode.equals(node)) {
                return null;
            }
            return floorNode;
        }
        
        public String toString() {
            return super.toString() + ", " + nodes.toString();
        }
    }
    
    private static class FileNode extends Node {
        private final StringBuilder content = new StringBuilder();
        
        FileNode(String name) {
            super(name, 1);
        }

        List<String> ls() {
            List<String> result = new ArrayList<>();
            result.add(name);
            return result;
        }
        
        void addContent(String str) {
            content.append(str);
        }
        
        String getContent() { 
            return content.toString();
        }
        
        public String toString() {
            return super.toString() + ", content: " + content;
        }
    }
    
    private final DirectoryNode root;
    
    public FileSystem() {
        root = new DirectoryNode("/");
    }
    
    private Node getNodeFromPath(String path) {
        String[] strs = path.split("/");
        DirectoryNode curNode = root;
        int i = 1;
        for (; i < strs.length - 1; ++i) {
            DirectoryNode nextNode = (DirectoryNode) curNode.getNode(strs[i], 0);
            if (nextNode == null) {
                return null;
            }
            curNode = nextNode;
        }
        if (i >= strs.length) {
            return curNode;
        }
        Node result = curNode.getNode(strs[i], 0);
        if (result != null) {
            return result;
        }
        return curNode.getNode(strs[i], 1);
    }
    
    //If path corresponds to a directory, return the content of that directory;
    //Otherwise if it corresponds to a file, return the file name alone
    //Else return null
    //O(nlogn) time
    public List<String> ls(String path) {
        Node node = getNodeFromPath(path);
        return (node == null) ? null : node.ls();
    }
    
    public void mkdir(String path) {
        String[] strs = path.split("/");
        DirectoryNode curNode = root;
        for (int i = 1; i < strs.length; ++i) {
            DirectoryNode nextNode = (DirectoryNode) curNode.getNode(strs[i], 0);
            if (nextNode == null) {
                nextNode = new DirectoryNode(strs[i]);
                curNode.addNode(nextNode);
            }
            curNode = nextNode;
        }
    }
    
    public void addContentToFile(String filePath, String content) {
        int lastSlashId = filePath.lastIndexOf("/");
        String parentPath = (lastSlashId == 0) ? "/" : filePath.substring(0, lastSlashId);
        Node parentNode = getNodeFromPath(parentPath);
        if (parentNode == null || parentNode.type != 0) {
            return;
        }
        DirectoryNode pNode = (DirectoryNode) parentNode;
        String fileName = filePath.substring(lastSlashId + 1);
        Node curNode = pNode.getNode(fileName, 1);
        if (curNode == null) {
            curNode = new FileNode(fileName);
            pNode.addNode(curNode);
        }
        curNode.addContent(content);
    }
    
    public String readContentFromFile(String filePath) {
        Node node = getNodeFromPath(filePath);
        return node == null ? null : node.getContent();
    }
    
    public static void main(String[] args) {
        System.out.println("Start!");
        FileSystem fs = new FileSystem();
        
        System.out.println(fs.ls("/").toString());
        fs.mkdir("/a/b/c");
        System.out.println(fs.ls("/a/b/c").toString());
        System.out.println(fs.ls("/a/b").toString());
        fs.addContentToFile("/a/b/c/d", "hello");
        System.out.println(fs.readContentFromFile("/a/b/c/d"));
    }

}

/**
 * Your FileSystem object will be instantiated and called as such:
 * FileSystem obj = new FileSystem();
 * List<String> param_1 = obj.ls(path);
 * obj.mkdir(path);
 * obj.addContentToFile(filePath,content);
 * String param_4 = obj.readContentFromFile(filePath);
 */