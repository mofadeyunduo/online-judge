package per.piers.onlineJudge.util;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class TokenUtil {

    private static final long TIMEOUT = 1000 * 60 * 5;
    private static ConcurrentHashMap<String, String> tokenEmails = new ConcurrentHashMap<>();

    public static synchronized String addURLToken(long time, String email) {
        char[] emailCharacters = email.toCharArray();
        Random random = new Random();
        int emailSum = 0;
        for (char c : emailCharacters) {
            emailSum += ((int) c) * random.nextInt(10);
        }
        String key = String.format("%d%03d", time, emailSum % 100);
        tokenEmails.put(key, email);
        return key;
    }

    public static synchronized String getEmailFromToken(String token) {
        long now = System.currentTimeMillis();
        for (String checkToken : tokenEmails.keySet()) {
            long create = Long.parseLong(checkToken.substring(0, token.length() - 3));
            if (now < create) throw new IllegalStateException("now < create");
            if (now - create > TIMEOUT) {
                tokenEmails.remove(checkToken);
            }
        }
        if (!tokenEmails.containsKey(token)) return null;
        long create = Long.parseLong(token.substring(0, token.length() - 3));
        if (now < create) throw new IllegalStateException("now < create");
        if (now - create < TIMEOUT) return tokenEmails.get(token);
        else return null;
    }

}
