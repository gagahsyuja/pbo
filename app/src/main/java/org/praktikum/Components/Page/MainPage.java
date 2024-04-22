package org.praktikum;

public class MainPage extends BasePage
{
    SelectInput pageSelect;

    public MainPage(int width)
    {
        super("Aplikasi Penyimpanan Password", width);
        this.components.add(new Label("Selamat datang di aplikasi Password Vault", this.width));
        this.components.add(new Label("Simpan password anda dengan aman di sini", this.width));
        this.components.add(new Space(this.width));

        String [] pages = { "Input Password", "Tampil Password", "Keluar Aplikasi"};
        
        this.pageSelect = new SelectInput("Pilih halaman berikut:", pages, this.width);

        this.components.add(pageSelect);
    }

    @Override
    public void drawContent()
    {
        int select;

        for (Component widget: this.components)
        {
            widget.draw();
        }

        select = this.pageSelect.getValue() - 1;

        switch (select)
        {
            case 0 -> {
                drawFooter();
                new InputPage(this.width).draw();
                new MainPage(this.width).draw();
            }

            case 1 -> {
                drawFooter();
                new PasswordListPage(this.width).draw();
            }

            case 2 -> {
                new Label("Menyimpan data...", this.width).draw();
                DataPassword.saveCSVData();
                new Label("Terima kasih telah menggunakan aplikasi", this.width).draw();
                drawFooter();
                System.exit(0);
            }

            default -> {
                new MainPage(this.width).draw();
            }
        }
    }
}
