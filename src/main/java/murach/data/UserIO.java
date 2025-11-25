package murach.data;

import java.io.*;
import java.util.StringTokenizer;

import murach.business.User;

public class UserIO {

    public static void add(User user, String filepath) throws IOException {
        File file = new File(filepath);
        PrintWriter out = new PrintWriter(new FileWriter(file, true));
        out.println(user.getEmail() + "|" + user.getFirstName() + "|" + user.getLastName());
        out.close();
    }

    public static User getUser(String email, String filepath) throws IOException {
        File file = new File(filepath);
        BufferedReader in = new BufferedReader(new FileReader(file));
        User user = null;
        String line = in.readLine();
        while (line != null) {
            StringTokenizer t = new StringTokenizer(line, "|");
            if (t.countTokens() < 3) {
                continue;
            }
            String token = t.nextToken();
            if (token.equalsIgnoreCase(email)) {
                String firstName = t.nextToken();
                String lastName = t.nextToken();
                user = new User(firstName, lastName, email);
                break;
            }
            line = in.readLine();
        }
        in.close();
        if (user == null) {
            user = new User();
        }
        return user;
    }
}