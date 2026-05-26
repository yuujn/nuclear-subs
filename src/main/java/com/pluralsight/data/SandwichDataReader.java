package com.pluralsight.data;

import com.pluralsight.model.MenuAddition;
import com.pluralsight.model.Size;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SandwichDataReader {
    private List<Size> sizes;
    private List<MenuAddition> additions;

    private SandwichDataReader() {
    }

    public static SandwichDataReader readCSVFiles(String dirName) throws IOException {
        SandwichDataReader data = new SandwichDataReader();

        String sizesFileName = dirName + "/sizes.csv";
        String additionCategoriesFileName = dirName + "/addition_categories.csv";
        String additionsFileName = dirName + "/additions.csv";

        FileReader sizesFileReader = new FileReader(sizesFileName);
        FileReader additionCategoriesFileReader = new FileReader(additionCategoriesFileName);
        FileReader additionsFileReader = new FileReader(additionsFileName);

        BufferedReader bufSizesReader = new BufferedReader(sizesFileReader);
        BufferedReader bufAdditionCategoriesReader = new BufferedReader(additionCategoriesFileReader);
        BufferedReader bufAdditionsReader = new BufferedReader(additionsFileReader);


        data.sizes = new ArrayList<>();
        String[] sizeFileHeader = bufSizesReader.readLine().split("\\|");
        String line;
        while ((line = bufSizesReader.readLine()) != null) {
            if (line.isBlank()) { continue; }
            data.sizes.add(Size.fromCSVRow(sizeFileHeader, line.split("\\|")));
        }

        List<SandwichAdditionCategory> categories = new ArrayList<>();
        String[] additionCategoriesFileHeader = bufAdditionCategoriesReader.readLine().split("\\|");
        while ((line = bufAdditionCategoriesReader.readLine()) != null) {
            if (line.isBlank()) { continue; }
            categories.add(SandwichAdditionCategory.fromCSVRow(additionCategoriesFileHeader, line.split("\\|")));
        }

        data.additions = new ArrayList<>();
        String[] additionFileHeader = bufAdditionsReader.readLine().split("\\|");
        while ((line = bufAdditionsReader.readLine()) != null) {
            if (line.isBlank()) { continue; }
            SandwichAddition addition = SandwichAddition.fromCSVRow(additionFileHeader, line.split("\\|"));
            SandwichAdditionCategory category = categories.get(addition.getCategoryid());
            boolean premium = category.getExtraPricesBySize() != null;
            data.additions.add(new MenuAddition(
                    addition.getName(),
                    category.getName(),
                    category.getPricesBySize(),
                    category.getExtraPricesBySize(),
                    premium
            ));
        }

        return data;
    }

    public List<Size> getSizes() {
        return sizes;
    }

    public List<MenuAddition> getAdditions() {
        return additions;
    }
}
