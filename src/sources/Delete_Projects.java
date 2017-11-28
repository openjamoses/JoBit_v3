/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sources;

import core.Create_Excel;
import core.File_Details;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import picks.Pick_GeneralNumeric;
import picks.Pick_GeneralStrings;
import util.Constants;

/**
 *
 * @author john
 */
public class Delete_Projects {

    public static void main(String[] args) throws Exception {
        deleteRepos();
    }

    private static void deleteRepos() throws Exception {
        Object[] datas = null;

        //todo:
        String[] files = {Constants.cons.PERCENTAGE_FILE1,
            Constants.cons.PERCENTAGE_FILE2,
            Constants.cons.PERCENTAGE_FILE3,
            Constants.cons.PERCENTAGE_FILE4,
            Constants.cons.PERCENTAGE_FILE5
        };

        for (int a = 0; a < files.length; a++) {
            ArrayList< Object[]> allobj2 = new ArrayList<Object[]>();

            List<String> delete_projectList = new ArrayList<>();

            List<String> delete_loginList = new ArrayList<>();
            List<String> delete_loginList_name = new ArrayList<>();
            List<String> delete_loginList_email = new ArrayList<>();
            List<String> delete_loginList_login = new ArrayList<>();
            
            datas = new Object[]{"PROJECT NAME", "NAME",
                "EMAIL", "LOGIN", "PR OPEN", "PR CLOSSED", "IS OPEN", "IS CLOSED", "FORKS", "PERCENTAGE_COMITS( % )", "PERCENTAGE_CHANGED( % )", "80%", " ", "85%", "", "90%"
            };// end of assigning the header to the object..

            int numbers = File_Details.getWorksheets(Constants.cons.FILE_PATH + files[a]);
            int count = 0;
            while (count < numbers) {
                List<Integer> flag_list = new ArrayList<>();
                ArrayList< Object[]> allobj = new ArrayList<Object[]>();
                allobj.add(datas);

                List<String> nameList = Pick_GeneralStrings.pick(Constants.cons.FILE_PATH + files[a], count, "NAME", 1);
                List<String> emailList = Pick_GeneralStrings.pick(Constants.cons.FILE_PATH + files[a], count, "EMAIL", 2);
                List<String> loginList = Pick_GeneralStrings.pick(Constants.cons.FILE_PATH + files[a], count, "LOGIN", 3);

                List<Double> pr_openList = Pick_GeneralNumeric.pick(Constants.cons.FILE_PATH + files[a], count, 4);
                List<Double> pr_clossedList = Pick_GeneralNumeric.pick(Constants.cons.FILE_PATH + files[a], count, 5);
                List<Double> is_openList = Pick_GeneralNumeric.pick(Constants.cons.FILE_PATH + files[a], count, 6);
                List<Double> is_clossedList = Pick_GeneralNumeric.pick(Constants.cons.FILE_PATH + files[a], count, 7);
                List<Double> forkList = Pick_GeneralNumeric.pick(Constants.cons.FILE_PATH + files[a], count, 8);

                List<Double> commitsList = Pick_GeneralNumeric.pick(Constants.cons.FILE_PATH + files[a], count, 9);
                List<Double> changesList = Pick_GeneralNumeric.pick(Constants.cons.FILE_PATH + files[a], count, 10);

                List<String> categoryList1 = Pick_GeneralStrings.pick(Constants.cons.FILE_PATH + files[a], count, "80%", 11);
                List<String> categoryList2 = Pick_GeneralStrings.pick(Constants.cons.FILE_PATH + files[a], count, "85%", 13);

                String project = File_Details.setProjectName(Constants.cons.FILE_PATH + files[a], count, "A2");
                String sheet = File_Details.getWorksheetName(Constants.cons.FILE_PATH + files[a], count);
                int flag = 0;

                for (int i = 0; i < loginList.size(); i++) {
                    if (loginList.get(i).equals(Constants.cons.EMPTY_LOGIN) && categoryList1.get(i).equals("Major")) {
                        flag = 1;
                        delete_projectList.add(project);

                        delete_loginList_name.add(nameList.get(i));
                        delete_loginList_email.add(emailList.get(i));
                        delete_loginList_login.add(loginList.get(i));
                    } else if (loginList.get(i).equals(Constants.cons.EMPTY_LOGIN) && categoryList1.get(i).equals("Minor")) {
                        //flag = 0;
                        
                        delete_projectList.add(project);
                        delete_loginList_name.add(nameList.get(i));
                        delete_loginList_email.add(emailList.get(i));
                        delete_loginList_login.add(loginList.get(i));
                        //Now remove the items from each arrayList...!!!
                        nameList.remove(nameList.get(i));
                        emailList.remove(emailList.get(i));
                        loginList.remove(loginList.get(i));
                        pr_openList.remove(pr_openList.get(i));
                        pr_clossedList.remove(pr_clossedList.get(i));
                        is_openList.remove(is_openList.get(i));
                        is_clossedList.remove(is_clossedList.get(i));
                        forkList.remove(forkList.get(i));
                        categoryList1.remove(categoryList1.get(i));
                        categoryList2.remove(categoryList2.get(i));
                        //categoryList3.remove(categoryList3.get(i));
                        commitsList.remove(commitsList.get(i));
                        changesList.remove(changesList.get(i));
                    }
                    flag_list.add(flag);
                }

                datas = new Object[]{project, "", "", "", "", "", "",
                        "", "", "", "", "", " ", "", "", ""
                    };// end of assigning the header to the object..
                    allobj.add(datas);
                    
                if (!flag_list.contains(1)) {
                    for (int i = 0; i < loginList.size(); i++) {
                        datas = new Object[]{"", nameList.get(i),
                            emailList.get(i),
                            loginList.get(i),
                            pr_openList.get(i),
                            pr_clossedList.get(i),
                            is_openList.get(i),
                            is_clossedList.get(i),
                            forkList.get(i),
                            commitsList.get(i),
                            changesList.get(i),
                            categoryList1.get(i), " ",
                            categoryList2.get(i), "", ""
                        };// end of assigning the header to the object..
                        allobj.add(datas);
                    }

                    datas = new Object[]{delete_loginList_login.size(), "", "", "", "", "", "",
                        "", "", "", "", "", " ", "", "", ""
                    };// end of assigning the header to the object..
                    allobj.add(datas);

                } else {
                    

                    datas = new Object[]{"Has been deleted", "", "", "", "", "", "",
                        "", "", "", "", "", " ", "", "", ""
                    };// end of assigning the header to the object..
                    allobj.add(datas);
                }
                System.out.println(project + "***************\t\t ******************");

                Create_Excel.createExcel(allobj, 0, Constants.cons.FILE_PATH2 + "del_" + files[a], sheet);

                count++;
            }

            datas = new Object[]{"PROJECT NAMEs", "NAME",
                "EMAIL", "LOGIN"};// end of assigning the header to the object..
            allobj2.add(datas);

            if (delete_loginList_name.size() > delete_projectList.size()) {
                for (int i = 0; i < delete_loginList.size(); i++) {
                    if (i < delete_projectList.size()) {
                        datas = new Object[]{delete_projectList.get(i), delete_loginList_name.get(i),
                            delete_loginList_email.get(i), delete_loginList_login.get(i)};// end of assigning the header to the object..
                        allobj2.add(datas);
                    } else {
                        datas = new Object[]{"", delete_loginList_name.get(i),
                            delete_loginList_email.get(i), delete_loginList_login.get(i)};// end of assigning the header to the object..
                        allobj2.add(datas);
                    }
                }
            } else if (delete_loginList_name.size() < delete_projectList.size()) {
                for (int i = 0; i < delete_projectList.size(); i++) {
                    if (i < delete_projectList.size()) {
                        datas = new Object[]{delete_projectList.get(i), delete_loginList_name.get(i),
                            delete_loginList_email.get(i), delete_loginList_login.get(i)};// end of assigning the header to the object..
                        allobj2.add(datas);
                    } else {
                        datas = new Object[]{delete_projectList.get(i), "",
                            "", ""};// end of assigning the header to the object..
                        allobj2.add(datas);
                    }
                }
            } else if (delete_loginList_name.size() < delete_projectList.size()) {
                for (int i = 0; i < delete_projectList.size(); i++) {
                    datas = new Object[]{delete_projectList.get(i), delete_loginList_name.get(i),
                        delete_loginList_email.get(i), delete_loginList_login.get(i)};// end of assigning the header to the object..
                    allobj2.add(datas);
                }
            }

            Create_Excel.createExcel(allobj2, 0, Constants.cons.FILE_PATH2 + "del_" + files[a], "results");
            System.out.println("\n" + a + "\n");
        }
    }
}
