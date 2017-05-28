package timelineManager.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Random;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;

/**
 *  This class holds an ArrayList of Timelines.
 *  It also has variables and methods that is needed for the Timeline Manager
 *  that is relevant to the model.
 */
public class TimelineModel{
    
    public static Color red = Color.rgb(232, 127, 129);
    public static Color orange = Color.rgb(228,171,115);
    public static Color yellow = Color.rgb(225,199,115);
    public static Color green = Color.rgb(141,187,122);
    public static Color blue = Color.rgb(124,183,225);
    public static Color purple = Color.rgb(172,152,224);
    public Color[] colorbar = {red,orange,yellow,green,blue,purple};
    public static Color[][] taskColorbar = {{Color.rgb(232, 147, 149),Color.rgb(232, 166, 169),Color.rgb(232, 115, 125),Color.rgb(232, 132, 143)},/*red bar*/
            {Color.rgb(228, 150, 115),Color.rgb(228,129,115),Color.rgb(228, 108, 95),Color.rgb(228, 115, 105)},/*orange bar*/
            {Color.rgb(225,169,115),Color.rgb(220,160,120),Color.rgb(225,139,115),Color.rgb(225,184,115)},/*yellow bar*/
            {Color.rgb(141,191,141),Color.rgb(141,196,159),Color.rgb(141,200,178),Color.rgb(121,205,199)},/*green bar*/
            {Color.rgb(134,172,225),Color.rgb(139,162,225),Color.rgb(143, 151, 225),Color.rgb(120, 141, 225)},/*blue bar*/
            {Color.rgb(170,160,240),Color.rgb(177,177,240),Color.rgb(163,163,240),Color.rgb(177,135,240)}};/*purple bar*/
    
    public ObservableList<Timeline> timelineList=FXCollections.observableArrayList();
    int current=0;
    
    /**
     * Add a Timeline to the observable list.
     * @param timeline The timeline to be added
     */
    public void addTimelineToList(Timeline timeline){
        timelineList.add(timeline);
        randomColor(timeline);
        current++;
    }
    
    /**
     * This function set a color to the timeline chosen randomly
     * among an array of colors.
     * It also sets the colorbar which holds the colors the task
     * that belongs to the timeline can have.
     * @param timeline The timeline to get a color.
     */
    public void randomColor(Timeline timeline){
        Random r = new Random();
        int i = r.nextInt(6);
        timeline.setColor(colorbar[i]);
        timeline.colorbar = taskColorbar[i];
    }
    
    /**
     * Iterates though all timelines and filter those that are are within the
     * input start and end.
     * @param start The first date that will show the timelines.
     * @param end The last date that will show the timelines.
     * @return an ArrayList of Timelines that are within the start and end.
     */
    public ArrayList<Timeline> getTimelinesToDisplay(LocalDate start, LocalDate end) {
        Iterator iter=timelineList.iterator();
        ArrayList<Timeline> list=new ArrayList();
        
        Timeline timeline;
        while(iter.hasNext()){
            timeline=(Timeline) iter.next();
            if(!start.isAfter(timeline.getEndTime()) && !timeline.getStartTime().isAfter(end)){
                list.add(timeline);
            }
        }
        list.sort(new Comparator<Timeline>()
        {
            @Override
            public int compare(Timeline t1, Timeline t2)
            {
                if(t1.getEndTime().equals(t2.getEndTime()))
                {
                    return t1.getStartTime().compareTo(t2.getStartTime());
                }
                else
                {
                    return t1.getEndTime().compareTo(t2.getEndTime());  // end time is primarty for sort
                }
                
            }
        });
        return list;
    }
    
    /**
     * This function checks which of all tasks in a timeline that occures
     * in between two dates. All that occures is returned in an ArrayList
     * @param timeline The timeline that holds the tasks
     * @param start start time
     * @param end end time
     * @return an ArrayList of Task that are happening between the start and end.
     */
    public ArrayList<Task> getTaskToDisplay(Timeline timeline,LocalDate start, LocalDate end){
        Iterator iter=timeline.taskList.iterator();
        ArrayList<Task> list=new ArrayList();
        Task task;
        while(iter.hasNext()){
            task=(Task) iter.next();
            if(!start.isAfter(task.getEndTime()) && !task.getStartTime().isAfter(end)){
                list.add(task);
            }
        }
        list.sort(new Comparator<Task>()
        {
            @Override
            public int compare(Task t1, Task t2)
            {
                if(t1.getEndTime().equals(t2.getEndTime()))
                {
                    
                    return t1.getStartTime().compareTo(t2.getStartTime());
                }
                else
                {
                    return t1.getEndTime().compareTo(t2.getEndTime());  // end time is primarty for sort
                }
                
            }
        });
        return list;
    }
}