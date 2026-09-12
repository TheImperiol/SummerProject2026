package ApplicationFX;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Base64;
import javafx.event.EventHandler;
import javafx.scene.control.Control;
import javafx.scene.input.*;


public abstract class DraggableCard extends GeneralCard implements Serializable{
    protected float[] position = new float[2];

    public ArrayList<Control> children = new ArrayList<Control>();
    public float GetX(){
        return position[0];
    }
    private double[] drawnTrackStart = new double[2];

    public void setTrackStart(double x, double y) {drawnTrackStart[0] = x; drawnTrackStart[1] = y;}

    public DraggableCard GetCard(){
        return this;
    }

    public void SetX(float x){
        position[0] = x;
    }

    public float GetY(){
        return position[1];
    }

    public void SetY(float y){
        position[1] = y;
    }


    private DraggableCard getSelf(){return this;}
    
    private Track currentTrack;

    public DraggableCard(float x, float y){
        super();
        SetX(x);
        SetY(y);
        

        this.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent event) {
                if(!MainSummerApp.app.frontend.emulatorWindow.DevicePresent()){
                   
                    return;
                }
                Dragboard db = startDragAndDrop(TransferMode.ANY);
                ClipboardContent content = new ClipboardContent();

                try(ByteArrayOutputStream bytStream = new ByteArrayOutputStream();
                    ObjectOutputStream objStream = new ObjectOutputStream(bytStream)){
                    objStream.writeObject(wrapper);
                    content.putString(Base64.getEncoder().encodeToString(bytStream.toByteArray()));
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                db.setContent(content);
                event.consume();
            }
        });

        this.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent event){
                if(MainSummerApp.app.GetDrawingMode() == true){
                    setTrackStart(event.getX(),event.getY());
                }
                event.consume();
            }
        });
        
        this.setOnDragDetected(event ->{startFullDrag(); event.consume();});

        this.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent event){
                if(MainSummerApp.app.GetDrawingMode() == true){
                    double endX = Math.clamp(event.getX(), 0,getSelf().getPrefWidth());
                    double endY = Math.clamp(event.getY(), 0, getSelf().getPrefHeight());
                    
                    if(currentTrack == null){
                        
                        currentTrack = new Track(
                            drawnTrackStart[0],
                            drawnTrackStart[1],
                            endX,
                            endY,
                            MainSummerApp.app.frontend.emulatorWindow.pcb
                        );
                        MainSummerApp.app.frontend.emulatorWindow.pcb.getChildren().add(currentTrack);

                    }
                    else{
                        currentTrack.UpdateEndPoint(endX, endY);
                    }
                }
                //event.consume();
            }
        });

        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent event){
                if(MainSummerApp.app.GetDrawingMode() == true){
                    if(currentTrack != null){
                        MainSummerApp.app.frontend.emulatorWindow.pcb.AddTrack(currentTrack);
                        currentTrack = null;
                        drawnTrackStart = new double[2];
                    }
                }
            }
        });
    }
}
