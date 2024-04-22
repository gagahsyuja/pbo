package org.praktikum;

public class Space implements Component
{
    private int width;

    public Space(int width)
    {
        this.width = width;
    }

    public void draw()
    {
        System.out.println("|" + " ".repeat(this.width) + "|");
    }
}
