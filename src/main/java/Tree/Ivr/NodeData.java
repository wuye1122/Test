package Tree.Ivr;

import java.util.Map;

public class NodeData {
    private String id;
    private String parentId;
    private String value;
    private Map<String, String> map;
    private Map<String,Map<String,String>> globalMap;

    public NodeData() {
    }

    public NodeData(String id, String parentId, String value ) {
        this.id = id;
        this.parentId = parentId;
        this.value = value;
    }
    public NodeData(String id, String parentId, String value ,Map<String, String> map,Map<String,Map<String,String>> globalMap) {
        this.id = id;
        this.parentId = parentId;
        this.value = value;
        this.map=map;
        this.globalMap=globalMap;
    }

    public Map<String, Map<String, String>> getGlobalMap() {
        return globalMap;
    }

    public void setGlobalMap(Map<String, Map<String, String>> globalMap) {
        this.globalMap = globalMap;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }
}
