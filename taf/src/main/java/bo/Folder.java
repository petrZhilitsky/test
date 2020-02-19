package bo;

public class Folder {
    private String name;

    public Folder() {
        setName();
    }

    public String getName() {
        return name;
    }

    private void setName() {
        name = "Folder" + System.currentTimeMillis();
    }
}
