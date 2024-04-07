package org.praktikumtiga;

import java.util.Scanner;

public class Input
{
    private String label;
    private Scanner input = new Scanner(System.in);
    private String value;

    public Input(String label)
    {
        this.label = label;
    }

    public void draw()
    {
        System.out.print("|  " + this.label);
        this.value = input.nextLine();
    }

    public String getValue()
    {
        return this.value;
    }

    public int getValueInt()
    {
        return Integer.parseInt(this.value);
    }

    public double getValueDouble()
    {
        return Double.parseDouble(this.value);
    }
}
