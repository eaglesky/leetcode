public class RestoreIpAddresses {
    
    //DFS solution
    private void dfs(String s, int start, int level, List<String> address, List<String> result) {
        if (level > 4) {
            if (start >= s.length()) {
                String curAddress = String.join(".", address);
                result.add(curAddress);
            }
            return;
        }
        int maxI = s.charAt(start) == '0' ? start + 1 : Math.min(start + 3, s.length());
        for (int i = start; i < maxI; ++i) {
            String str = s.substring(start, i + 1);
            int strValue = Integer.parseInt(str);
            if (strValue >= 0 && strValue <= 255 && i <= (s.length() - 5 + level)
                && i >= (s.length() - 1 - 3*(4 - level))) {
                address.add(str);
                dfs(s, i+1, level+1, address, result);
                address.remove(address.size()-1);
            }
        }
    }
    
    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        if (s.length() < 4) {
            return result;
        }
        dfs(s, 0, 1, new ArrayList<String>(), result);
        return result;
    }

    //Another implementation:
    private static boolean isValid(String ipStr) {
        if ((ipStr.charAt(0) == '0' && ipStr.length() > 1)
            || ipStr.length() > 3) {
            return false;
        }
        int ip = Integer.parseInt(ipStr);
        return ip >= 0 && ip <= 255;
    }
    
    private static void dfs(String s, int start, List<String> curIp, List<String> allIps) {
        if (curIp.size() == 4) {
            if (start >= s.length()) {
                allIps.add(String.join(".", curIp));
            }
            return;
        }
        int minStart = Math.max(start, s.length() - 12 + 3*curIp.size());
        int maxStart = s.length() - 4 + curIp.size();
        for (int i = minStart; i <= maxStart; ++i) {
            String curStr = s.substring(start, i+1);
            if (isValid(curStr)) {
                curIp.add(curStr);
                dfs(s, i+1, curIp, allIps);
                curIp.remove(curIp.size()-1);
            } else {
                break;
            }
        }
    }
    
    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        dfs(s, 0, new ArrayList<String>(), result);
        return result;
    }


    //Easier to come up with backtracking solution:
    private void dfs(String s, int startId, List<String> curIp, List<String> ips) {
        if (startId == s.length() && curIp.size() == 4) {
            ips.add(String.join(".", curIp));
            return;
        }
        if (curIp.size() >= 4) {
            return;
        }
        
        int ipSeg = 0;
        //The range of i can be further improved to be smaller
        for (int i = startId; i < s.length(); ++i) {
            ipSeg = ipSeg * 10 + (s.charAt(i) - '0');
            if (ipSeg <= 255) {
                curIp.add(String.valueOf(ipSeg));
                dfs(s, i + 1, curIp, ips);
                curIp.remove(curIp.size() - 1);
            }
            if (ipSeg == 0 || ipSeg > 255) {
                return;
            }
        }
    }
    
    //Add pruning:
    private void dfs(String s, int startId, List<String> ip, List<String> result) {
        if (ip.size() == 4 && startId == s.length()) {
            result.add(String.join(".", ip));
            return;
        }
        if (ip.size() >= 4 || startId >= s.length()) {
            return;
        }
        int numCharLeft = s.length() - startId;
        if (numCharLeft > (4 - ip.size()) * 3 || numCharLeft < (4 - ip.size())) {
            return;
        }
        int curNum = 0;
        for (int i = startId; i < s.length(); ++i) {
            char c = s.charAt(i);
            curNum = 10 * curNum + (c - '0');
            if (curNum >= 256) {
                break;
            }
            ip.add(String.valueOf(curNum));
            dfs(s, i + 1, ip, result);
            ip.remove(ip.size() - 1);
            if (curNum == 0) {
                break;
            }
        }
    }

    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        dfs(s, 0, new ArrayList<>(), result);
        return result;
    }

    //See c++ solution for the iterative approach, not recommended.
}