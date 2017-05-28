package timelineManager.view;

import java.time.LocalDate;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import timelineManager.controller.AbstractController;
import timelineManager.controller.AddTaskController;
import timelineManager.controller.AddTimelineController;
import timelineManager.controller.EditTaskController;
import timelineManager.controller.EditTimelineController;
import timelineManager.controller.HelpManualController;
import timelineManager.controller.MainWindowController;
import timelineManager.controller.ModelAccess;
import timelineManager.controller.MoveTimelineController;
import timelineManager.helpClasses.DateViewer;
import timelineManager.helpClasses.TimelineViewer;
import timelineManager.model.Timeline;

/**
 * This class is a helper class for loading the views from .fxml files.
 * Also initilazes controllers to the fxml files with common reference to ModelAccess Class
 * @author beysimeryalmaz
 */
public class ViewFactory {
    
    public static ViewFactory defaultFactory=new ViewFactory();
    private ModelAccess modelAccess=new ModelAccess();
    private TableView<Timeline> timelineTable = new TableView<>();
    private TimelineViewer timelineViewer = new TimelineViewer(timelineTable);
    private DateViewer dateViewer = new DateViewer(LocalDate.now());
    private AddTaskController addTaskController;
    private AddTimelineController addTimelineController;
    private MainWindowController mainWindowController;
    private EditTimelineController editTimelineController;
    private EditTaskController editTaskController;
    private MoveTimelineController moveTimelineController;
    private HelpManualController   helpManualController;
    private final String MAIN_FXML="/timelineManager/view/MainView.fxml";
    private final String ADD_TIMELINE_FXML="/timelineManager/view/AddTimelineView.fxml";
    private final String ADD_TASK_FXML="/timelineManager/view/AddTaskView.fxml";
    private final String EDIT_TIMELINE_FXML="/timelineManager/view/EditTimelineView.fxml";
    private final String EDIT_TASK_FXML="/timelineManager/view/EditTaskView.fxml";
    private final String MOVE_TIMELINE_FXML="/timelineManager/view/MoveTimelineView.fxml";
    private final String HELP_MANUAL_FXML="/timelineManager/view/HelpView.fxml";
    
    public Scene getMainScene(){
        mainWindowController=new MainWindowController(modelAccess,timelineViewer, dateViewer, timelineTable);
        return initilazeScene(MAIN_FXML, mainWindowController);
    }
    
    public Scene getAddTimelineScene(){
        addTimelineController=new AddTimelineController(modelAccess,timelineViewer, dateViewer, timelineTable);
        return initilazeScene(ADD_TIMELINE_FXML, addTimelineController);
        
        
    }
    
    public Scene getAddTaskScene(){
        addTaskController=new AddTaskController(modelAccess,timelineViewer, dateViewer, timelineTable);
        return initilazeScene(ADD_TASK_FXML, addTaskController);
        
    }
    
    public Scene getEditTimelineScene(){
        editTimelineController=new EditTimelineController(modelAccess,timelineViewer, dateViewer, timelineTable);
        return initilazeScene(EDIT_TIMELINE_FXML, editTimelineController);
        
    }
    
    public Scene getMoveTimelineScene(){
        moveTimelineController=new MoveTimelineController(modelAccess,timelineViewer, timelineTable,dateViewer );
        return initilazeScene(MOVE_TIMELINE_FXML, moveTimelineController);
        
    }
    
    public Scene getHelpScene(){
        helpManualController=new HelpManualController(modelAccess,timelineViewer, timelineTable,dateViewer );
        return initilazeScene(HELP_MANUAL_FXML, helpManualController);
        
    }
    
    public Scene getEditTaskScene(){
        editTaskController=new EditTaskController(modelAccess,timelineViewer, dateViewer, timelineTable);
        return initilazeScene(EDIT_TASK_FXML, editTaskController);
    }
    
    public Scene initilazeScene(String fxmlPath, AbstractController abstractController){
        FXMLLoader loader;
        Parent parent;
        Scene scene;
        try {
            loader = new FXMLLoader(getClass().getResource(fxmlPath));
            loader.setController(abstractController);
            parent = loader.load();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        
        scene = new Scene(parent);
        scene.getStylesheets().add(getClass().getResource("/timelineManager/css/stylesheet.css").toExternalForm());
        return scene;
    }
}
