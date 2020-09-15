public class DVD{
  private Time dvd_time;
  private State state;

  public DVD(){}

  public void setTime(Time time){
    this.time = time;
  }

  public Time getTime(){
    return time;
  }

  public void setState(State state){
    this.state = state;
  }

  public State getState(){
    return state;
  }

}
