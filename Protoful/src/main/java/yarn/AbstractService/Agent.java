package yarn.AbstractService;

/**
 * Copyright @ 2018
 * All right reserved.
 *
 * @author Li Hao
 * @since 2019/1/15  20:26
 */
class Agent {
    private final int id;

    public Agent(int id) {
        this.id = id;
    }

    // hashCode and equals method must be implemented  if the object is used in HashMap as key or HashSet.
    @Override
    public int hashCode() {
        return id;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null) {
            return false;
        } else if (this.getClass() != obj.getClass()) {
            return false;
        } else {
            Agent other = (Agent) obj;
            return this.id == other.id;
        }
    }

    @Override
    public String toString() {
        return "Agent: " + id;
    }
}

