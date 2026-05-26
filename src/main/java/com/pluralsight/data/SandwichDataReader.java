package com.pluralsight.data;

import com.pluralsight.model.MenuAdditionCategory;
import com.pluralsight.model.MenuAddition;
import com.pluralsight.model.Size;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SandwichDataReader {
    private List<Size> sizes;
    private List<MenuAdditionCategory> categories;
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
        data.categories = new ArrayList<>();
        String[] additionCategoriesFileHeader = bufAdditionCategoriesReader.readLine().split("\\|");
        while ((line = bufAdditionCategoriesReader.readLine()) != null) {
            if (line.isBlank()) { continue; }
            SandwichAdditionCategory fileCategory = SandwichAdditionCategory.fromCSVRow(additionCategoriesFileHeader, line.split("\\|"));
            categories.add(fileCategory);

            MenuAdditionCategory category = new MenuAdditionCategory(
                    fileCategory.getId(),
                    fileCategory.getName(),
                    new ArrayList<>(),
                    fileCategory.isCanMany()
            );
            data.categories.add(category);
        }

        data.additions = new ArrayList<>();
        String[] additionFileHeader = bufAdditionsReader.readLine().split("\\|");
        while ((line = bufAdditionsReader.readLine()) != null) {
            if (line.isBlank()) { continue; }
            SandwichAddition fileAddition = SandwichAddition.fromCSVRow(additionFileHeader, line.split("\\|"));
            SandwichAdditionCategory fileCategory = categories.get(fileAddition.getCategoryId());
            MenuAdditionCategory menuCategory = data.categories.stream()
                    .filter(x -> x.getId() == fileAddition.getCategoryId())
                    .findFirst()
                    .orElseThrow();

            boolean premium = fileCategory.getExtraPricesBySize() != null;
            MenuAddition menuAddition = new MenuAddition(
                    fileAddition.getName(),
                    menuCategory,
                    fileCategory.getPricesBySize(),
                    fileCategory.getExtraPricesBySize(),
                    premium
            );
            data.additions.add(menuAddition);

            menuCategory.getAdditions().add(menuAddition);
        }

        return data;
    }

    public List<Size> getSizes() {
        return sizes;
    }

    public List<MenuAdditionCategory> getCategories() {
        return categories;
    }

    public List<MenuAddition> getAdditions() {
        return additions;
    }
}
