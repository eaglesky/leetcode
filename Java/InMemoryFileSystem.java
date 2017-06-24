/*
Design an in-memory file system to simulate the following functions:

ls: Given a path in string format. If it is a file path, return a list that only contains this file's name. If it is a directory path, return the list of file and directory names in this directory. Your output (file and directory names together) should in lexicographic order.

mkdir: Given a directory path that does not exist, you should make a new directory according to the path. If the middle directories in the path don't exist either, you should create them as well. This function has void return type.

addContentToFile: Given a file path and file content in string format. If the file doesn't exist, you need to create that file containing given content. If the file already exists, you need to append given content to original content. This function has void return type.

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
You can assume all file or directory paths are absolute paths which begin with / and do not end with / except that the path is just "/".
You can assume that all operations will be passed valid parameters and users will not attempt to retrieve file content or list a directory or file that does not exist.
You can assume that all directory names and file names only contain lower-case letters, and same names won't exist in the same directory.
*/

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
}

/**
 * Your FileSystem object will be instantiated and called as such:
 * FileSystem obj = new FileSystem();
 * List<String> param_1 = obj.ls(path);
 * obj.mkdir(path);
 * obj.addContentToFile(filePath,content);
 * String param_4 = obj.readContentFromFile(filePath);
 */