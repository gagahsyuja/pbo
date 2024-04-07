package org.praktikumtiga;

import java.security.NoSuchAlgorithmException;

public class PasswordListPage
{
    private String title;
    private int width;
    private int size = DataPassword.dataPass.size();

    private final HLine hline;
    private final Space space;
    private final Label label;

    public PasswordListPage(String title, int width)
    {
        this.title = title;
        this.width = width;
        this.hline = new HLine(width);
        this.space = new Space(width);
        this.label = new Label(title.toUpperCase(), width);
    }

    public void draw() throws Exception
    {
        hline.draw();
        space.draw();
        label.draw();
        space.draw();
        hline.draw();
        space.draw();

        new Label("Terdapat " + this.size + " tersimpan.", this.width).draw();
        new Label("----- ----- -----", this.width).draw();

        this.getData();

        space.draw();
        hline.draw();
    }

    private void getData() throws Exception
    {
        for (int i = 0; i < this.size; i++)
        {
            PasswordStore data = DataPassword.dataPass.get(i);

            String password = data.getPassword();

            String output = String.format("| %-18s | %-18s | %-18s |", data.name, password, data.getCategory());

            new Label(output, this.width).draw();
        }
    }
}
