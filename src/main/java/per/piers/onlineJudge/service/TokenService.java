package per.piers.onlineJudge.service;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class TokenService {

    private static final long TIMEOUT = 1000 * 60 * 5;
    private static ConcurrentHashMap<String, String> tokenEmails = new ConcurrentHashMap<>();

    // TODO: not a good code, maybe not thread safe
//    private static int clearTokensThread = 0;
//    private static Thread clearTokens = new Thread(() -> {
//        while (true) {
//            long now = System.currentTimeMillis();
//            for (String token : tokenEmails.keySet()) {
//                long create = Long.parseLong(token.substring(0, token.length() - 3));
//                if (now < create) throw new IllegalStateException("now < create");
//                if (now - create > TIMEOUT) {
//                    tokenEmails.remove(token);
//                }
//            }
//            try {
//                TimeUnit.HOURS.sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    });

    private TokenService() {
    }

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
        if (!tokenEmails.containsKey(token)) return null;
        long create = Long.parseLong(token.substring(0, token.length() - 3));
        long now = System.currentTimeMillis();
        if (now < create) throw new IllegalStateException("now < create");
        if (now - create < TIMEOUT) return tokenEmails.get(token);
        else return null;
    }

}
