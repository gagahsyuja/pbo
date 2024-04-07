package org.praktikumtiga;

import java.security.NoSuchAlgorithmException;

public class PasswordStore {

    public String name, username;
    private String password, hashKey;
    private double score;
    private int category;

    public static final int UNCATEGORIZED = 0;
    public static final int CAT_WEBAPP = 1;
    public static final int CAT_MOBILEAPP = 2;
    public static final int CAT_OTHER = 3;

    public PasswordStore(String name, String username, String plainPass, int category)
    {
        try
        {
            this.hashKey = Encryptor.generateKey();
        }

        catch (Exception e)
        {
            System.out.println("Error: " + e);
        }

        this.name = name;
        this.username = username;
        
        setPassword(plainPass);
        setCategory(category);
    }

    public PasswordStore(String name, String username, String plainPass)
    {
        try
        {
            this.hashKey = Encryptor.generateKey();
        }

        catch (Exception e)
        {
            System.out.println("Error: " + e);
        }

        this.name = name;
        this.username = username;
        
        setPassword(plainPass);
        setCategory(UNCATEGORIZED);
    }

    public PasswordStore(String name, String username, String plainPass, int category, String hashKey, double score)
    {
        this.name = name;
        this.username = username;
        this.password = plainPass;
        this.hashKey = hashKey;
        this.score = score;
        
        setCategory(category);
    }

    public void setPassword(String plainPass)
    {
        try
        {
            this.password = Encryptor.encrypt(plainPass, hashKey);
        }

        catch (Exception e)
        {
            System.out.println("Error: " + e);
        }

        calculateScore(plainPass);
    }

    public String getPassword()
    {
        try
        {
            return Encryptor.decrypt(this.password, hashKey);
        }

        catch (Exception e)
        {
            return "Error: " + e;
        }
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
            case UNCATEGORIZED:
                category = "Belum terkategori";
                break;

            case CAT_WEBAPP:
                category = "Aplikasi web";
                break;

            case CAT_MOBILEAPP:
                category = "Aplikasi mobile";
                break;

            case CAT_OTHER:
                category = "Akun lainnya";
                break;
        }

        return category;
    }

    private void calculateScore(String plainPass)
    {
        double len = plainPass.length();

        double result = (len / 15) * 10;

        String strResult = String.format("%.2f", result);

        result = Double.parseDouble(strResult);

        this.score = (len >= 15)
            ? 10
            : result;
    }

    public String getEncPassword()
    {
        return this.password;
    }

    public String getHashKey()
    {
        return this.hashKey;
    }

    public int getCategoryCode()
    {
        return this.category;
    }

    public double getScore()
    {
        return this.score;
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
