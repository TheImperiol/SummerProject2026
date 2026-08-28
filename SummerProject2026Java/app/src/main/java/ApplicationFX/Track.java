package ApplicationFX;
import javafx.scene.Group;
import javafx.scene.shape.Line;

public class Track extends Group{
    private TrackSnapPoint startPoint = new TrackSnapPoint(this);
    private TrackSnapPoint endPoint = new TrackSnapPoint(this);

    private Line track;

    public Line getTrack(){return track;}

    protected PCB owningPCB;

    public Track(double sx,double sy, double ex, double ey, PCB owner){
        track = new Line(sx, sy, ex, ey);
        startPoint.setCenterX(sx);
        startPoint.setCenterY(sy);
        endPoint.setCenterX(ex);
        endPoint.setCenterY(ey);
        owningPCB = owner;
        this.getChildren().addAll(track, startPoint, endPoint);
    }

    public void UpdateEndPoint(double x, double y){
        endPoint.setCenterX(x);
        endPoint.setCenterY(y);
        track.setEndX(x);
        track.setEndY(y);
    }
}
