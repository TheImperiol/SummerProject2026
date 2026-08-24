package ApplicationFX;
import javafx.scene.Group;
import javafx.scene.shape.Line;

public class Track extends Group{
    private TrackSnapPoint startPoint = new TrackSnapPoint(); 
    private TrackSnapPoint endPoint = new TrackSnapPoint();
    private Line track;
    public Line getTrack(){return track;}
    public Track(double sx,double sy, double ex, double ey){
        track = new Line(sx, sy, ex, ey);
        startPoint.setCenterX(sx);
        startPoint.setCenterY(sy);
        endPoint.setCenterX(ex);
        endPoint.setCenterY(ey);
        this.getChildren().addAll(track, startPoint, endPoint);
    }
    public void UpdateEndPoint(double x, double y){
        endPoint.setCenterX(x);
        endPoint.setCenterY(y);
        track.setEndX(x);
        track.setEndY(y);
    }
}
