import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;

public class dd {
    static ArrayList<String> usernames = new ArrayList<String>();
    static ArrayList<String> passwords = new ArrayList<String>();
    static File usernameFile = new File("usernames.txt");
    static File passwordFile = new File("passwords.txt");

    public static void main(String[] args) {
        try {
            if (!usernameFile.exists()) {
                usernameFile.createNewFile();
            } else if (!passwordFile.exists()) {
                passwordFile.createNewFile();
            }

        Scanner  agha = new Scanner(System.in);
        System.out.println("please choose one option" );
            System.out.println("Login Or register by typing L or R");
        String walam = agha.nextLine();
        if (walam.equalsIgnoreCase("l")) {
            boolean repeat = true;
            while (repeat) {
                boolean valid = login();
                if (valid) {
                    typingTest();
                    repeat = false;
                } else {
                    System.err.println("Please enter a valid username and password");
                }
            }
        }
        else if (walam.equalsIgnoreCase("r")) {
            register();
        } else {
            System.out.println("Please Choose A Correct option");
        }
        } catch (IOException e) {
            System.out.println("failed to create file");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void typingTest() {
        Scanner pc = new Scanner(System.in);
        String[] easy = {"hello ", "guy ", "why not ", "from", "could", "said", "answer", "method", "yes", "never",
                "think",
                "sum",
                "kurd", "arab",
                "fars", "engliz", "barham", "mobile", "galaxy", "pen", "pencel", "saw", "deep", "hey", "come",
                "go",
                "luck",
                "second",};

        String[] medium = {"SOmE", "CacUlas", "" +
                "@COria", "qpRt", "$MxEvn"
                , "WHY ? ", "WArawa", "asana ?", "essaY?", "/;lx", "zmpgxncv", "tryIt!23",
            };


        String[] hard = {"kxcnsodfi", "dsfkj", "sdjf", "Whom <", "Colonel", " ASD?Dfaw",
                "sdfgrdfs , ", "SDFBgdg.", "YrbddTY ?", "\"Enormity\"", "AsEyQb", " OnePlusTwoEquals4%",
                "<Handkerchief>",
                };


        String[] showedText = new String[0];
        boolean repeatExternal = true;
        boolean repeatInternal;
        while (repeatExternal) {
            repeatInternal = true;
            while (repeatInternal) {

                System.out.println("Choose your level : ");
                System.out.println("e = Easy ");
                System.out.println("m = Medium ,");
                System.out.println("h = Hard\\r ");

                String choice = pc.nextLine();

                try {


                    System.out.print("dastpeka" );
                    System.out.println("    ");
                    System.out.println("     ");
                    Thread.sleep(1000);

                } catch (InterruptedException ignored) {
                }
                if (choice.equalsIgnoreCase("e")) {
                    showedText = scrambler(easy);
                    repeatInternal = false;
                } else if (choice.equalsIgnoreCase("m")) {
                    showedText = scrambler(medium);
                    repeatInternal = false;
                } else if (choice.equalsIgnoreCase("h")) {
                    showedText = scrambler(hard);
                    repeatInternal = false;
                } else {
                    System.err.println("HAlbzhardnakat halaya ");
                }
            }



            double start = System.nanoTime();
            System.out.print(" ");
            System.out.println("  ");
            String input = pc.nextLine();

            //wpm
            String[] Array = input.split(" ");
            double finish = System.nanoTime();
            double duration = finish - start;
            double second = (duration / 1000000000);
            double mint = second / 60;
            double wpm = (Array.length / mint);
            //accuracy
            int correct = 0;
            int incorrect = 0;
            String[] Array1 = input.split(" ");
            StringBuilder arrayToString = new StringBuilder();
            double length = Array1.length;
            for (int x = 0; x < Array1.length; x++) {
                if (showedText[x].equals(Array1[x])) {
                    correct++;
                } else {
                    incorrect++;
                }
            }

            double Accuracy = (correct / length);

            System.out.println("Accuracy %" + Math.round(Accuracy * 100) + "\n speed " + wpm + " w/m" + "\nw/m-Error:" +
                    " " + Math.round(wpm * Accuracy) + "w/m");
            System.out.println();
            System.out.println("  give up ? ");
            String answer = pc.nextLine();
            if (answer.equalsIgnoreCase("yes")) {
                System.out.println( "  GAME OVER  ");

                repeatExternal = false;
            }
        }
    }
    static String[] scrambler(String[] text) {
        String[] showedText = new String[text.length];
        for (int i = 0; i < showedText.length; i++) {
            int random = (int) (Math.random() * showedText.length);
            showedText[i] = text[random];
            System.out.print(text[random] + " ");
            if (i % 10 == 0 && i != 0) {
                System.out.println();

            }
        }
        return showedText;
    }

    static boolean login() throws IOException {
        reader();
        Scanner scanner = new Scanner(System.in);
        System.out.print("name of the user: ");
        String username = md5Hasher(scanner.nextLine());
        System.out.print("password: ");

        String password = md5Hasher(scanner.nextLine());
        for (int i = 0; i < usernames.size(); i++)
            if (password.equals(passwords.get(i)) && username.equals(usernames.get(i)))
                return true;

        return false;

    }
    static void register() throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        reader();
        int flag = 0;
        String username = "";
        System.out.println( " tkaya hazhmarek drost bka");
        while (flag == 0) {
            System.out.print("name of the user  : ");
            username = scanner.nextLine();
            if (usernames.size() == 0)
                flag = 1;
            for (int i = 0; i < usernames.size(); i++) {
                if (!(md5Hasher(username).equals(usernames.get(i))) && i == usernames.size() - 1) {
                    flag = 1;
                }
                else if (usernames.size() - 1 == i) {

                    System.err.println("This username is already taken");
                }

            }

        }

        System.out.print("secret code : ");
        String password = scanner.nextLine();
        writer(username, usernameFile);
        writer(password, passwordFile);

        typingTest();
    }
        public static String md5Hasher(String input) {
        String toBeHashed = "";
        try {
            MessageDigest digestion = MessageDigest.getInstance("MD5");
            byte[] messageDigest = digestion.digest(input.getBytes());
            BigInteger num = new BigInteger(1, messageDigest);
            toBeHashed = num.toString(16);
            if (toBeHashed.length() < 32) {
                do {
                    toBeHashed = "0" + toBeHashed;
                } while (toBeHashed.length() < 32);
            }
        } catch (NoSuchAlgorithmException ex) {
            System.out.println(" wrong!");
        }
        return toBeHashed;
    }
    static void writer(String write, File file) throws IOException {
        FileWriter writer = new FileWriter(file, true);
        writer.write(md5Hasher(write) + "\n");
        writer.close();
    }
    static void reader() throws IOException {
        BufferedReader usernameReader = new BufferedReader(new FileReader(usernameFile));
        BufferedReader passwordReader = new BufferedReader(new FileReader(passwordFile));
        String user;
        String pass;
        while (((user = usernameReader.readLine()) != null) && (pass = passwordReader.readLine()) != null) {
            usernames.add(user);
            passwords.add(pass);
        }
        usernameReader.close();
        passwordReader.close();
    }
}

