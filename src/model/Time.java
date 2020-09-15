package model;

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
}
