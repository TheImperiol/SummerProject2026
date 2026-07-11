package ApplicationFX;

import java.io.File;
import java.util.ArrayList;

public class Project {
    private ArrayList<File> devices = new ArrayList<File>();
    private ArrayList<File> scripts = new ArrayList<File>();
    private ArrayList<File> components = new ArrayList<File>();

    public Project(){}

    public Project(ArrayList<File> _devices, ArrayList<File> _scripts, ArrayList<File> _components){
        this.devices = _devices;
        this.scripts = _scripts;
        this.components = _components;
    }

    public void AddDevice(File _device){devices.add(_device);}
    public void RemoveDevice(File _device){devices.remove(_device);}

    public void AddScript(File _script){scripts.add(_script);}
    public void RemoveScript(File _script){scripts.remove(_script);}

    public void AddComponent(File _component){components.add(_component);}
    public void RemoveComponent(File _component){components.remove(_component);}

    public ArrayList<File> GetDevices(){
        return this.devices;
    }

    public ArrayList<File> GetScript(){
        return this.scripts;
    }

    public ArrayList<File> GetComponents(){
        return this.components;
    }
}
