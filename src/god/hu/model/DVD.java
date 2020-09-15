package god.hu.model;

import god.hu.usage.State;

public class DVD {
    private Time time;
    private State state;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

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

        public DVD build() {
            dvd = new DVD();
            dvd.setState(State.ON_SHELF);
            dvd.setTime(time);
            dvd.setName(name);
            return dvd;
        }
    }

}
