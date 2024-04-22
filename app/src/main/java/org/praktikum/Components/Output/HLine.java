package org.praktikum;

public class HLine implements Component
{
    private int width;

    public HLine(int width)
    {
        this.width = width;
    }

    public void draw()
    {
        System.out.println("+" + "=".repeat(this.width) + "+");
    }
}
