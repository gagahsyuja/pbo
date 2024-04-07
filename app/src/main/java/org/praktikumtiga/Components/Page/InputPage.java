package org.praktikumtiga;

import java.security.NoSuchAlgorithmException;

public class InputPage
{
    private String title;
    private String passwordTitle;
    private String username;
    private String password;
    private int category;
    private int width;

    private final HLine hline;
    private final Space space;
    private final Label label;

    public InputPage(String title, int width)
    {
        this.title = title;
        this.width = width;
        this.hline = new HLine(width);
        this.space = new Space(width);
        this.label = new Label(title, width);
    }

    public void draw()
    {
        hline.draw();

        space.draw();
        label.draw();
        space.draw();

        hline.draw();

        space.draw();
        new Label("Inputan Password Baru", this.width).draw();

        Input inputPasswordTitle = new Input("Judul Password: ");
        inputPasswordTitle.draw();
        this.passwordTitle = inputPasswordTitle.getValue();

        Input inputUsername = new Input("Username: ");
        inputUsername.draw();
        this.username = inputUsername.getValue();

        Input inputPassword = new Input("Password: ");
        inputPassword.draw();
        this.password = inputPassword.getValue();

        String category [] = {"Belum terkategori", "Aplikasi Web", "Aplikasi Mobile", "Akun Lainnya"};

        SelectInput select = new SelectInput(
            "Kategori",
            category,
            this.width
        );
        select.draw();
        this.category = select.getValue();

        new Label("----- -----", this.width).draw();
        new Label("Input password berhasil dibuat", this.width).draw();

        space.draw();
        hline.draw();

        try
        {
            PasswordStore store = new PasswordStore(
                    this.passwordTitle,
                    this.username,
                    this.password,
                    this.category
                    );

            DataPassword.dataPass.add(store);
        }

        catch (Exception e)
        {
            System.out.println("Error: " + e);
        }

    }
}
