package ApplicationFX;

import java.util.ArrayList;

public class Project {
    private ArrayList<String> devices = new ArrayList<String>();
    private ArrayList<String> scripts = new ArrayList<String>();
    private ArrayList<String> components = new ArrayList<String>();
    private String path;

    public Project(String projectPath){this.path = projectPath;}

    public Project(ArrayList<String> _devices, ArrayList<String> _scripts, ArrayList<String> _components, String projectPath){
        this.devices = _devices;
        this.scripts = _scripts;
        this.components = _components;
        this.path = projectPath;
    }

    public String GetPath(){
        return path;
    }

    public void AddDevice(String _device){devices.add(_device);}
    public void RemoveDevice(String _device){devices.remove(_device);}

    public void AddScript(String _script){scripts.add(_script);}
    public void RemoveScript(String _script){scripts.remove(_script);}

    public void AddComponent(String _component){components.add(_component);}
    public void RemoveComponent(String _component){components.remove(_component);}

    public ArrayList<String> GetDevices(){
        return this.devices;
    }

    public ArrayList<String> GetScript(){
        return this.scripts;
    }

    public ArrayList<String> GetComponents(){
        return this.components;
    }
}
