package org.praktikumtiga;

public class Space
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
