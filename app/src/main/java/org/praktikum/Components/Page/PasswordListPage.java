package org.praktikum;

import java.security.NoSuchAlgorithmException;
import java.lang.IndexOutOfBoundsException;
import java.util.logging.*;

public class PasswordListPage extends BasePage
{
    private int size = DataPassword.dataPass.size();

    public PasswordListPage(int width)
    {
        super("List Password Tersimpan", width);

        this.hline = new HLine(width);
        this.space = new Space(width);
        this.label = new Label(title.toUpperCase(), width);
    }

    @Override
    public void drawContent()
    {
        try
        {
            new Label("Terdapat " + this.size + " tersimpan.", this.width).draw();
            new Label("----- ----- -----", this.width).draw();

            this.getData();

            space.draw();
            hline.draw();

            try
            {
                Input detail = new Input("Pilih index detail / [x - kembali ke main] : ");
                detail.draw();

                if (detail.getValue().contains("x"))
                {
                    new MainPage(this.width).draw();
                }

                else
                {
                    new DetailPage(detail.getValueInt() - 1, this.width).draw();
                }
            }

            catch (NumberFormatException nfe)
            {
                new Label("Inputan salah!", this.width).draw();
                new PasswordListPage(this.width).draw();
            }

            catch (IndexOutOfBoundsException ioobe)
            {
                new Label("Index tidak ditemukan!", this.width).draw();
                new PasswordListPage(this.width).draw();
            }
        }

        catch (Exception e)
        {
            System.out.println("Error: " + e);
        }
    }

    private void getData()
    {
        try
        {
            for (int i = 0; i < this.size; i++)
            {
                PasswordStore data = DataPassword.dataPass.get(i);

                String output = String.format("| %s | %-15s | %-15s | %-18s |", i + 1, data.name, data.username, data.getCategory());

                new Label(output, this.width).draw();
            }
        }
        
        catch (Exception e)
        {
            Logger.getLogger(DataPassword.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
