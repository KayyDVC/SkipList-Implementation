*Hello,*

This program is a basic implementation of a skip list which is then used as a basis for a specific application completed for homework in my Algorithms and Data Structures course.

The gist of the application is an "Activity Tracker" that has a few keyword triggers that allow the user to store data based on the date and time the acitivity was preformed. Users can then search the list in Log(n) time for a specific time or range of times and receive the activity that is associated at the time via the console.

The format for input (see SampleIn text files) passed in via command line arguments is as follows:

------------

- AddActivity time activity
- RemoveActivity time
- GetActivity time
- GetActivitiesBetweenTimes startTime endTime
- GetActivitiesForOneDay date
- GetActivitiesFromEarlierInTheDay currentTime
- PrintSkipList

*All times values should be in mmddhhmm format.*

*In contrast, the argument for "GetActivitiesForOneDay" should be passed in with the mmdd format*

------------
The HW5.java file contains the main class and should be used to run the program. As a precaution, there are no relative imports in the file so, place HW5.java, FakeRandHeight.java, SkipList.java, and SkipNode.java in the same package to avoid any invalid reference errors.

The FakeRandHeight.java class was created by my professor for the course and is properly credited as a block comment within the file.

All SampleIn files were either provided by the professor or other classmates and has been shared with permission.

Thanks,

\-Kayy
