package org.praktikum;

import java.security.NoSuchAlgorithmException;
import java.util.logging.*;

public class InputPage extends BasePage
{
    // private String passwordTitle;
    // private String username;
    // private String password;
    // private int category;

    private Input nameInput;
    private Input usernameInput;
    private Input passInput;
    private SelectInput catInput;
    private PasswordStore passStr;

    public InputPage(int width)
    {
        super("Inputan Password", width);

        this.hline = new HLine(width);
        this.space = new Space(width);
        this.label = new Label(title.toUpperCase(), width);
    }

    @Override
    public void drawContent()
    {
        new Label("Inputan Password Baru", this.width).draw();

        nameInput = new Input("Judul Password: ");
        nameInput.draw();

        usernameInput = new Input("Username: ");
        usernameInput.draw();

        passInput = new Input("Password: ");
        passInput.draw();

        String[] category = {"Belum terkategori", "Aplikasi Web", "Aplikasi Mobile", "Akun Lainnya"};

        catInput = new SelectInput(
            "Kategori",
            category,
            this.width
        );

        catInput.draw();

        new Label("----- -----", this.width).draw();
        new Label("Input password berhasil dibuat", this.width).draw();

        space.draw();
        hline.draw();

        try
        {
            passStr = new PasswordStore(
                    nameInput.getValue(),
                    usernameInput.getValue(),
                    passInput.getValue(),
                    catInput.getValue()
                    );

            DataPassword.dataPass.add(passStr);
        }

        catch (Exception e)
        {
            Logger.getLogger(PasswordStore.class.getName()).log(Level.SEVERE, null, e);
        }

    }
}
