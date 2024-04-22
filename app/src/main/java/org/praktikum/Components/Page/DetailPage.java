package org.praktikum;

public class DetailPage extends BasePage
{
    private PasswordStore passStr;
    private String name;
    private int index;

    public DetailPage(int index, int width)
    {
        super(DataPassword.dataPass.get(index).name, width);
        this.name = name;
        this.index = index;
        passStr = DataPassword.dataPass.get(this.index);
    }

    @Override
    public void drawContent()
    {
        new Label(String.format("Category%6s%s", ": ", passStr.getCategory()), this.width).draw();
        new Label(String.format("Username%6s%s ", ": ", passStr.username), this.width).draw();
        new Label(String.format("Password%6s%s ", ": ", passStr.getPassword()), this.width).draw();
        new Label(String.format("Encrypt%7s%s ", ": ", passStr.getEncPassword()), this.width).draw();
        new Label(String.format("Score%9s%,.2f ", ": ", passStr.getScore()), this.width).draw();

        new Space(this.width).draw();
        new HLine(this.width).draw();

        String[] pilihanAksi = {
            "Hapus Password",
            "Kembali ke List Password",
            "Kembali ke Halaman Utama"
        };

        SelectInput aksi = new SelectInput("Pilih aksi", pilihanAksi, this.width);
        aksi.draw();

        switch(aksi.getValue())
        {
            case 1 -> {
                DataPassword.dataPass.remove(this.index);
                new PasswordListPage(this.width).draw();
            }

            case 2 -> {
                new PasswordListPage(this.width).draw();
            }

            case 3 -> {
                new MainPage(this.width).draw();
            }

            default -> {
                new MainPage(this.width).draw();
            }
        }
    }
}
