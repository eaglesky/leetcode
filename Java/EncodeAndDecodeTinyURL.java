public class EncodeAndDecodeTinyURL {
    private final Map<String, Integer> url2Id = new HashMap<>();
    private final List<String> urls = new ArrayList<>();
    private final static String MY_URL_PREFIX = "http://tinyurl.com/";
    private final static int RADIX = 62;
    
    //n is in the range [0, 61]
    private char getSymbol(int n) {
        if (n <= 9) {
            return (char)('0' + n);
        } else if (n <= 35) {
            return (char)('a' + (n - 10));
        } else if (n <= 61) {
            return (char)('A' + (n - 36));
        }
        return '#';
    }
    
    private String convertToRadix(int num) {
        StringBuilder sb = new StringBuilder();
        for(; num > 0; num = num / RADIX) {
            sb.append(getSymbol(num % RADIX));
        }
        return sb.reverse().toString();
    }
    
    private int convertToDecimal(String str) {
        int num = 0;
        for (int i = 0; i < str.length(); ++i) {
            num = num * RADIX + str.charAt(i);
        }
        return num;
    }
    
    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        if (longUrl == null) {
            throw new RuntimeException("Not a valid long url!");
        }
        Integer id = url2Id.get(longUrl);
        if (id == null) {
            id = urls.size();
            urls.add(longUrl);
            url2Id.put(longUrl, id);
        }
        return MY_URL_PREFIX + convertToRadix(id);
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        if (shortUrl == null || !shortUrl.startsWith(MY_URL_PREFIX)) {
            throw new RuntimeException("Not a valid short url!");
        }
        String keyPart = shortUrl.substring(MY_URL_PREFIX.length());
        int id = convertToDecimal(keyPart);
        return (id >= urls.size()) ? "" : urls.get(id);
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(url));