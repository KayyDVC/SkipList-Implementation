/*
Author: KayyDVC
Email: kayydv1016@gmail.com
Description: This file contains the file IO as well as code instructing the
compiler of what to do with the plain text it reads in. In accordance with
the assignment requirements, it contains function redirects for each keyword
: AddActivity, RemoveActivity, GetActivity, GetActivitiesBetweenTimes,
GetActivitiesForOneDay, GetActivitiesFromEarlierInTheDay, and PrintSkipList
*/


import java.io.*;


public class HW5 {

    public static void main(String[] args) throws IOException {
        /*
        testing validity of SkipList Operations before adding assignment
         required features.
                SkipList skipList = new SkipList();

                skipList.insertNode(new SkipNode("Eat", Integer.valueOf("03161500")));

                skipList.insertNode(new SkipNode("Sleep", Integer.valueOf("03211600")));

                skipList.insertNode(new SkipNode("Wake Up",
                        Integer.valueOf("03242100")));

                skipList.insertNode(new SkipNode("Work", Integer.valueOf("03252300")));

                skipList.insertNode(new SkipNode("School Again",
                        Integer.valueOf("06182000")));

                skipList.printList();

                SkipNode test = skipList.searchList(Integer.valueOf("03252300"));
                System.out.println();
                System.out.println("Test");
                System.out.println("Date and Time of Upper:  " + test.getUpper().getDateTime());
                System.out.println();

               skipList.removeNode(Integer.valueOf("03252300"));

                skipList.printList();}
         Read in functionality
        */

        SkipList sL = new SkipList();


        File in = null;
        if (args.length > 0) {
            in = new File(args[0]);
        } else {
            System.err.println("Did not recieve a file from command line;");
            System.exit(0);
        }

        // file in for queries
        BufferedReader reader = new BufferedReader(new FileReader(in));

        String readLine = null;

        while ((readLine = reader.readLine()) != null) {
            System.out.print(readLine + " ");
            //split each line into an array of space-seperated words
            String[] iW = readLine.split(" ");
            switch (iW[0]) {
                case "AddActivity": {
                    Integer timeDate = Integer.valueOf(iW[1]);
                    String activity = iW[2];

                    sL.insertNode(new SkipNode(activity, timeDate));
                    System.out.print("\n");

                    break;
                }

                case "RemoveActivity": {
                    Integer timeDate = Integer.valueOf(iW[1]);

                    sL.removeNode(timeDate);
                    System.out.print("\n");


                    break;
                }

                case "GetActivity": {
                    Integer timeDate = Integer.valueOf(iW[1]);

                    SkipNode n = sL.searchList(timeDate);

                    System.out.println(n.getActivity());

                    break;
                }

                case "GetActivitiesBetweenTimes": {
                    Integer start = Integer.valueOf(iW[1]);
                    Integer end = Integer.valueOf(iW[2]);

                    sL.subMap(start, end);

                    break;
                }

                case "GetActivitiesForOneDay": {
                    //turn date into proper dateTime format mmddhhmm
                    String s = iW[1];
                    Integer start = Integer.valueOf(s + "0000");

                    //add 1 to dd, then properly format to mmddhhmm
                    Integer end = Integer.valueOf(s);
                    end += 1;
                    s = String.valueOf(end);
                    end = Integer.valueOf(s + "0000");

                    sL.subMap(start, end);

                    break;
                }

                case "GetActivitiesFromEarlierInTheDay": {
                    Integer end = Integer.valueOf(iW[1]);

                    //get the proper format for the beginning of the day
                    // mmdd0000
                    String s = iW[1];
                    s = s.substring(0, 4);
                    s += "0000";
                    Integer start = Integer.valueOf(s);

                    sL.subMap(start, end);

                    break;
                }

                case "PrintSkipList": {
                    System.out.println();
                    sL.printList();

                    break;
                }

                default: {
                    System.out.println("No Option For Command Given");
                }


            }
        }
        reader.close();
    }


}



