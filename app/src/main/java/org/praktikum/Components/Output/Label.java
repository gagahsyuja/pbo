package org.praktikum;

public class Label implements Component
{
    private int width;
    public String text;

    public Label(String text, int width)
    {
        this.width = width;
        this.text = text;
    }

    public void draw()
    {
        int textLength = this.text.length();

        System.out.println("|  " + this.text + " ".repeat(this.width - textLength - 2) + "|");
    }
}
