package com.company.erasmusaga.web.screens;

import com.company.erasmusaga.entity.*;
import com.company.erasmusaga.exception.AGARuntimeException;
import com.haulmont.cuba.core.global.CommitContext;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.TextField;
import com.haulmont.cuba.gui.screen.Screen;
import com.haulmont.cuba.gui.screen.Subscribe;
import com.haulmont.cuba.gui.screen.UiController;
import com.haulmont.cuba.gui.screen.UiDescriptor;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.inject.Inject;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@UiController("erasmusaga_ImportScreen")
@UiDescriptor("import-screen.xml")
public class ImportScreen extends Screen {
    @Inject
    private TextField<String> input_txt;
    @Inject
    private DataManager dataManager;
    @Inject
    private TextField<String> inputSheet_txt;
    @Inject
    private TextField<Integer> startIndexTf;
    @Inject
    private TextField<Integer> endIndexTf;

    @Subscribe
    public void onInit(InitEvent event) {

    }


    @Subscribe("importBtn")
    public void onImportBtnClick(Button.ClickEvent event) throws IOException {
        if(input_txt.getValue()==null || input_txt.getValue().equals("")){
            throw new AGARuntimeException("Please enter the Local Path of the excel file.","Can't find Local Path");
        }
        else {
            FileInputStream fis = new FileInputStream(input_txt.getValue());
            CommitContext ccc = new CommitContext();
            // Load file, workbook and sheet.
            XSSFWorkbook wb = new XSSFWorkbook(fis);

            XSSFSheet ws = wb.getSheet(inputSheet_txt.getValue());

            // Declare row and cell variable.
            XSSFRow row;
            XSSFCell cells;

            // Get row and column count.
            int rowCount = ws.getLastRowNum();
            int colCount = ws.getRow(0).getLastCellNum();

            // Iterate over rows and columns.
            for (int r = 0; r <= rowCount; r++) {
                if (r >= startIndexTf.getValue()-1 && r <= endIndexTf.getValue()-1) {
                    Student student = dataManager.create(Student.class);
                    Application application = dataManager.create(Application.class);
                    ccc.addInstanceToCommit(student);
                    ccc.addInstanceToCommit(application);
                    List<University> universityList = new ArrayList<>();
                    student.setApplication(application);
                    row = ws.getRow(r);
                    System.out.println(r);
                    for (int c = 0; c < colCount; c++) {
                        cells = row.getCell(c);
                        switch (c) {
                            case 0:
                                break;

                            case 1:
                                student.setFirstName("A" + r);
                                break;
                            case 2:
                                student.setLastName("A" + r);
                                break;
                            case 3:
                                student.setBilkentID(r);
                                break;
                            case 5:
                                student.setDepartment(dataManager.load(Department.class)
                                        .query("e.name=:name")
                                        .parameter("name", cells.toString()).one());
                                break;
                            case 20:
                                student.setTotalGrade(Double.valueOf(cells.toString()));
                                break;
                            case 21:
                                //duration
                                application.setDuration(dataManager.load(Duration.class)
                                        .query("e.name=:name")
                                        .parameter("name", cells.toString()).one());
                                break;
                            case 22:
                                //uni 1
                                if(cells.toString() != null && !cells.toString().equals("")) {
                                    universityList.add(dataManager.load(University.class)
                                            .query("e.name=:name")
                                            .parameter("name", cells.toString()).one());
                                }
                                break;
                            case 23:
                                //uni 2
                                if(cells.toString() != null && !cells.toString().equals("")) {
                                    universityList.add(dataManager.load(University.class)
                                            .query("e.name=:name")
                                            .parameter("name", cells.toString()).one());
                                }
                                break;
                            case 24:
                                //uni 3
                                if(cells.toString() != null && !cells.toString().equals("")) {
                                    universityList.add(dataManager.load(University.class)
                                            .query("e.name=:name")
                                            .parameter("name", cells.toString()).one());
                                }
                                break;
                            case 25:
                                //uni 4
                                if(cells.toString() != null && !cells.toString().equals("")) {
                                    universityList.add(dataManager.load(University.class)
                                            .query("e.name=:name")
                                            .parameter("name", cells.toString()).one());
                                }
                                break;
                            case 26:
                                //uni 5
                                if(cells.toString() != null && !cells.toString().equals("")) {
                                    universityList.add(dataManager.load(University.class)
                                            .query("e.name=:name")
                                            .parameter("name", cells.toString()).one());
                                }
                                break;
                        }
                    }
                    student.setName(student.getFirstName() + "  " + student.getLastName());
                    student.setLogin(student.getBilkentID().toString());
                    student.setPassword("1");
                    application.setUniversities(universityList);
                }
            }
            dataManager.commit(ccc);
        }
    }

    @Subscribe("importUniBtn")
    public void onImportUniBtnClick(Button.ClickEvent event) throws IOException {
        FileInputStream fis = new FileInputStream("C:\\Users\\A.T.A\\Desktop\\cs319ExcelFiles\\courses_and_board_decisions.xlsx");
        CommitContext ccc = new CommitContext();
        // Load file, workbook and sheet.
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet ws = wb.getSheet("School List Of Grades");


        // Declare row and cell variable.
        XSSFRow row;
        XSSFCell cells;

        // Get row and column count.
        int rowCount = ws.getLastRowNum();
        int colCount = ws.getRow(0).getLastCellNum();

        // Iterate over rows and columns.
        for (int r = 0; r <= rowCount; r++) {
            if (r > 0 && r < 86) {
                University university = dataManager.create(University.class);
                row = ws.getRow(r);
                for (int c = 0; c < colCount; c++) {
                    cells = row.getCell(c);
                    switch (c) {
                        case 1:
                            university.setName(cells.toString());
                            break;
                        case 2:
                            university.setCountry(cells.toString());
                            break;
                        case 3:
                            university.setAgreementType(cells.toString());
                            break;
                        case 4:
                            university.setDepartment(cells.toString());
                            break;
                        case 5:
                            university.setLowestGrade(cells.toString());
                            break;
                        case 6:
                            university.setHighestGrade(cells.toString());
                            break;
                        case 7:
                            university.setPassingGrade(cells.toString());
                    }
                }
                ccc.addInstanceToCommit(university);
            }
        }
        dataManager.commit(ccc);
    }

//    @Subscribe("importCourseBtn")
//    public void onImportCourseBtnClick(Button.ClickEvent event) throws IOException {
//        FileInputStream fis = new FileInputStream("C:\\Users\\A.T.A\\Desktop\\cs319ExcelFiles\\courses_and_board_decisions.xlsx");
//        CommitContext ccc = new CommitContext();
//        // Load file, workbook and sheet.
//        XSSFWorkbook wb = new XSSFWorkbook(fis);
//        XSSFSheet ws = wb.getSheet("PRE APPROVAL ");
//
//
//        // Declare row and cell variable.
//        XSSFRow row;
//        XSSFCell cells;
//
//        // Get row and column count.
//        int rowCount = ws.getLastRowNum();
//        int colCount = ws.getRow(0).getLastCellNum();
//
//        // Iterate over rows and columns.
//        for (int r = 0; r <= rowCount; r++) {
//            if (r > 0 && r < 86) {
//                University university = dataManager.create(University.class);
//                row = ws.getRow(r);
//                for (int c = 0; c < colCount; c++) {
//                    cells = row.getCell(c);
//                    switch (c) {
//                        case 1:
//                            university.setName(cells.toString());
//                            break;
//                        case 2:
//                            university.setCountry(cells.toString());
//                            break;
//                        case 3:
//                            university.setAgreementType(cells.toString());
//                            break;
//                        case 4:
//                            university.setDepartment(cells.toString());
//                            break;
//                        case 5:
//                            university.setLowestGrade(cells.toString());
//                            break;
//                        case 6:
//                            university.setHighestGrade(cells.toString());
//                            break;
//                        case 7:
//                            university.setPassingGrade(cells.toString());
//                    }
//                }
//                ccc.addInstanceToCommit(university);
//            }
//        }
//        dataManager.commit(ccc);
//    }
}