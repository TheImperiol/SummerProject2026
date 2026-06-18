package ApplicationFX;

public abstract class DraggableCard extends GeneralCard{
    protected float[] position = new float[2];

    public float GetX(){
        return position[0];
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
    public DraggableCard(float x, float y){
        super();
        SetX(x);
        SetY(y);
    }
}
