package god.hu.model;

import god.hu.usage.abs.State;

public class DVD {
    private Time time;
    private State state;
    private Integer id;
    private String name;

    @Override
    public String toString() {
        return "DVD{" +
                "time=" + time.getSerial() +
                ", state=" + state +
                ", id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DVD() {
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Time getTime() {
        return time;
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public static class Builder {
        private DVD dvd;
        private Time time;
        private State state;
        private String name;
        private Integer id;

        public Builder() {
        }

        public Builder setTime(Time time) {
            this.time = time;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setState(State state) {
            this.state = state;
            return this;
        }

        public Builder setID(Integer id){
            this.id = id;
            return this;
        }
        public DVD build() {
            dvd = new DVD();
            dvd.setState(state);
            dvd.setTime(time);
            dvd.setName(name);
            dvd.setId(id);
            return dvd;
        }
    }

}
