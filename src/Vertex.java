import java.util.Objects;

public class Vertex {

    //Name of value
    private final String label;
    // Flag value - visited true, not visited false
    private boolean wasVisited;

    /**
     * Method for setting value
     * @param label - name
     */
    public Vertex(String label) {
        this.label = label;
    }

    /**
     * Method for getting label
     * @return label name
     */
    public String getLabel() {
        return label;
    }

    /**
     * Method for checking if label has visited or not
     * @return True if yes and False if not
     */
    public boolean isWasVisited() {
        return wasVisited;
    }

    /**
     * Method for setting label flag wisited/not visited
     * @param wasVisited True if yes / False if not
     */
    public void setWasVisited(boolean wasVisited) {
        this.wasVisited = wasVisited;
    }

    /**
     * Method for get compare between two values
     * @param o - object
     * @return True if equals / False if not
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex vertex = (Vertex) o;
        return Objects.equals(label, vertex.label);
    }

    /**
     * Method for get hashcode of label name
     * @return Label hash
     */
    @Override
    public int hashCode() {
        return Objects.hash(label);
    }

    /**
     * Method for extract values from Vertex
     * @return String with data
     */
    @Override
    public String toString() {
        return "Vertex{" +
                "label='" + label + '\'' +
                '}';
    }
}