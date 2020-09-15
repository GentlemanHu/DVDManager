package god.hu.model;

public class Time{
  public Integer getBorrowTime() {
    return borrowTime;
  }

  public void setBorrowTime(Integer borrowTime) {
    this.borrowTime = borrowTime;
  }

  public Integer getRevertTime() {
    return revertTime;
  }

  public void setRevertTime(Integer revertTime) {
    this.revertTime = revertTime;
  }

  public Integer getRenewTime() {
    return renewTime;
  }

  public void setRenewTime(Integer renewTime) {
    this.renewTime = renewTime;
  }

  private Integer borrowTime;
  private Integer revertTime;
  private Integer renewTime;

  public static class Builder{
    private Integer borrowTime;
    private Integer revertTime;
    private Integer renewTime;
    private Time time;
    public Builder setBorrowTime(Integer time){
      this.borrowTime = time;
      return this;
    }
    public Builder setRevertTime(Integer time){
      this.revertTime = time;
      return this;
    }
    public Builder setRenewTime(Integer time){
      this.renewTime = time;
      return this;
    }

    public Time build(){
      this.time = new Time();
      time.setBorrowTime(borrowTime);
      time.setRenewTime(renewTime);
      time.setRevertTime(revertTime);
      return time;
    }
  }
}
