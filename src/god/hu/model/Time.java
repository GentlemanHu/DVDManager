package god.hu.model;

public class Time{
  private String borrowTime;
  private String revertTime;
  private String renewTime;
  private Integer id;

  public String getBorrowTime() {
    return borrowTime;
  }

  public void setBorrowTime(String borrowTime) {
    this.borrowTime = borrowTime;
  }

  public String getRevertTime() {
    return revertTime;
  }

  public void setRevertTime(String revertTime) {
    this.revertTime = revertTime;
  }

  public String getRenewTime() {
    return renewTime;
  }

  public void setRenewTime(String renewTime) {
    this.renewTime = renewTime;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getSerial() {
    return serial;
  }

  public void setSerial(String serial) {
    this.serial = serial;
  }

  private String serial;

  public static class Builder{
    private String borrowTime;
    private String revertTime;
    private String renewTime;
    private Integer id;
    private Time time;
    private String serial;

    public Builder setBorrowTime(String time){
      this.borrowTime = time;
      return this;
    }
    public Builder setRevertTime(String time){
      this.revertTime = time;
      return this;
    }
    public Builder setRenewTime(String time){
      this.renewTime = time;
      return this;
    }
    public Builder setId(Integer id){
      this.id = id;
      return this;
    }

    public Builder setSerial(String serial){
      this.serial = serial;
              return this;
    }
    public Time build(){
      this.time = new Time();
      time.setBorrowTime(borrowTime);
      time.setRenewTime(renewTime);
      time.setRevertTime(revertTime);
      time.setId(id);
      time.setSerial(serial);
      return time;
    }
  }
}
