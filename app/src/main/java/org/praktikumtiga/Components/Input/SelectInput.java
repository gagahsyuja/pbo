package org.praktikumtiga;

public class SelectInput
{
    private String [] selection;
    private int width;
    private String label;
    private int value;
    private Input input;

    public SelectInput(String label, String [] selection, int width)
    {
        this.label = label;
        this.selection = selection;
        this.width = width;
    }

    public void draw()
    {
        Label header = new Label(this.label, this.width);
        header.draw();
        
        for (int i = 0; i < this.selection.length; i++)
        {
            String output = "  [" + (i + 1) + "] " + this.selection[i];

            Label option = new Label(output, this.width);
            option.draw();
        }

        Input selection = new Input("Pilihan: ");
        selection.draw();
        this.value = selection.getValueInt();
    }

    public int getValue()
    {
        return this.value;
    }
}
