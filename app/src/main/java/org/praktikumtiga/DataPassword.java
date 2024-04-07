package org.praktikumtiga;

import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

public class DataPassword
{
    public static final ArrayList<PasswordStore> dataPass = new ArrayList<>();
    private static final String csvPath = "./src/main/java/org/praktikumtiga/datapassword.csv";
    private static final String [] headers = {
        "name",
        "username",
        "password",
        "hashkey",
        "category",
        "score"
    };

    public static void saveCSVData()
    {
        if (dataPass.isEmpty())
        {
            System.out.println("Data kosong");
        }

        else
        {
            try
            {
                FileWriter writer = new FileWriter("./src/main/java/org/praktikumtiga/datapassword.csv");
                CSVFormat formatter = CSVFormat.DEFAULT.builder().setHeader(headers).build();
                CSVPrinter printer = new CSVPrinter(writer, formatter);

                for (PasswordStore pass: dataPass)
                {
                    printer.printRecord(
                        pass.name,
                        pass.username,
                        pass.getEncPassword(),
                        pass.getHashKey(),
                        pass.getCategoryCode(),
                        pass.getScore()
                    );
                }

                printer.flush();
            }

            catch (IOException ioe)
            {
                Logger.getLogger(DataPassword.class.getName()).log(Level.SEVERE, null, ioe);
            }
        }
    }

    public static ArrayList<PasswordStore> loadCSVData()
    {
        dataPass.clear();

        try
        {
            FileReader reader = new FileReader(csvPath);
            CSVFormat format = CSVFormat.DEFAULT.builder().setHeader(headers).setSkipHeaderRecord(true).build();
            Iterable<CSVRecord> data = format.parse(reader);

            for (CSVRecord record: data)
            {
                PasswordStore newPass;

                if (record.get("hashkey") == null)
                {
                    newPass = new PasswordStore(
                            record.get("name"),
                            record.get("username"),
                            record.get("password"),
                            Integer.parseInt(record.get("category"))
                    );
                }

                else
                {
                    newPass = new PasswordStore(
                            record.get("name"),
                            record.get("username"),
                            record.get("password"),
                            Integer.parseInt(record.get("category")),
                            record.get("hashkey"),
                            Double.parseDouble(record.get("score"))
                    );
                }

                dataPass.add(newPass);
            }
        }

        catch (FileNotFoundException fnfe)
        {
            System.out.println("Data password kosong ...");
        }

        catch (IOException ioe)
        {
            Logger.getLogger(DataPassword.class.getName()).log(Level.SEVERE, null, ioe);
        }

        return dataPass;
    }
}
