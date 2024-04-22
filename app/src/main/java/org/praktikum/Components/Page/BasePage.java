package org.praktikum;

import java.util.ArrayList;

public abstract class BasePage
{
    public String title;
    public int width;
    protected HLine hline;
    protected Space space;
    protected Label label;
    protected ArrayList<Component> components = new ArrayList<Component>();

    public BasePage(String title, int width)
    {
        this.title = title;
        this.width = width;
        hline = new HLine(width);
        space = new Space(width);
        label = new Label(title.toUpperCase(), width);
    }

    public void draw()
    {
        this.drawHeader();
        this.space.draw();
        this.drawContent();
    }

    public void drawHeader()
    {
        this.hline.draw();
        this.space.draw();
        this.label.draw();
        this.space.draw();
        this.hline.draw();
    }

    public void drawFooter()
    {
        this.space.draw();
        this.hline.draw();
    }

    public abstract void drawContent();
}
