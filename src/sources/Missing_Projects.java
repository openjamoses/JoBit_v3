/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sources;

import core.Create_Excel;
import core.File_Details;
import java.util.ArrayList;
import java.util.List;
import util.Constants;
import static util.Constants.cons.REPO_FILE_NAME;

/**
 *
 * @author john
 */
public class Missing_Projects {

    /**
     *
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        checkMissing();
    }

    private static void checkMissing() throws Exception {
        String[] files = {};
        Object[] datas = null;
        ArrayList< Object[]> allobj = new ArrayList<Object[]>();
        ArrayList< Object[]> allobj2 = new ArrayList<Object[]>();
        int count = 0;
        List<String> lists1 = File_Details.readReposNames(REPO_FILE_NAME);
        List<String> lists2 = new ArrayList<>();

        for (int i = 0; i < files.length; i++) {
            System.out.println(count + "\t" + files[i]);
            int number = File_Details.getWorksheets(files[i]);
            while (count < number) {
                String project = File_Details.setProjectName(files[i], count,"G2");
                if (project != null) {
                    lists2.add(project);
                }
                count++;
            }
            System.out.println("\t \n");
        }

        System.out.println();
        lists1.removeAll(lists2);

        System.out.println(lists1.size());
        for (int i = 0; i < lists1.size(); i++) {
            //System.out.println(lists1.get(i));
            datas = new Object[]{lists1.get(i)};
            allobj.add(datas);
        }
        for (int i = 0; i < lists2.size(); i++) {
            //System.out.println(lists1.get(i));
            datas = new Object[]{lists2.get(i)};
            allobj2.add(datas);
        }
        Create_Excel.createExcel(allobj, 0, Constants.cons.CREATE_MISSING_FILES, "missings");
        Create_Excel.createExcel(allobj2, 0, Constants.cons.CREATE_MISSING_FILES, "success");
    }
}
