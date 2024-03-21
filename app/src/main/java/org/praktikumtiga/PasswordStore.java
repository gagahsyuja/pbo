package org.praktikumtiga;

import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PasswordStore {

    public String name, username;
    private String password, hashKey;
    private double score;
    private int category;

    public static final int UNCATEGORIZED = 0;
    public static final int CAT_WEBAPP = 1;
    public static final int CAT_MOBILEAPP = 2;
    public static final int CAT_OTHER = 3;

    public PasswordStore(String name, String username, String plainPass, int category) throws Exception
    {
        this.hashKey = Encryptor.generateKey();
        this.name = name;
        this.username = username;
        
        setPassword(plainPass);
        setCategory(category);
    }

    public PasswordStore(String name, String username, String plainPass) throws Exception
    {
        this.hashKey = Encryptor.generateKey();
        this.name = name;
        this.username = username;
        
        setPassword(plainPass);
        setCategory(UNCATEGORIZED);
    }

    public void setPassword(String plainPass) throws Exception
    {
        this.password = Encryptor.encrypt(plainPass, hashKey);

        calculateScore(plainPass);
    }

    public String getPassword() throws Exception
    {
        return Encryptor.decrypt(this.password, hashKey);
    }

    public void setCategory(int category)
    {
        this.category = (category >= 0 && category <= 3)
            ? category
            : 0;
    }

    public String getCategory()
    {
        String category = "";

        switch (this.category)
        {
            case 0:
                category = "Belum terkategori";
                break;

            case 1:
                category = "Aplikasi web";
                break;

            case 2:
                category = "Aplikasi mobile";
                break;

            case 3:
                category = "Akun lainnya";
                break;
        }

        return category;
    }

    private void calculateScore(String plainPass)
    {
        double len = plainPass.length();

        this.score = (len >= 15)
            ? 10
            : (len / 15) * 10;
    }

    @Override
    public String toString()
    {
        return "Username\t: " + this.username +
            "\nPassword\t: " + this.password +
            "\nHash key\t: " + this.hashKey +
            "\nCategory\t: " + this.getCategory() +
            "\nPassword Score\t: " + this.score;
    }
}
