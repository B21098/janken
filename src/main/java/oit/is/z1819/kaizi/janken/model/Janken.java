package oit.is.z1819.kaizi.janken.model;

import java.util.Random;

//@Controller
public class Janken {
  private String cpu;
  private String player1;
  private String player2;

  public void setPlayer1(String hand) {
    this.player1 = hand;
  }

  public String getPlayer1() {
    return this.player1;
  }

  public void setPlayer2(String hand) {
    this.player2 = hand;
  }

  public String getPlayer2() {
    return this.player2;
  }

  public void setCpu() {
    int rand = new Random().nextInt(3);
    switch (rand) {
      case 0:
        this.cpu = "Gu";
        break;
      case 1:
        this.cpu = "Choki";
        break;
      case 2:
        this.cpu = "Pa";
        break;
    }
  }

  public String getCpu() {
    return this.cpu;
  }

  public String jankenResult() {
    if (this.player1.equals(this.player2)) {
      return "Drow";
    }
    if ((this.player1.equals("Gu") && this.player2.equals("Choki")) ||
        (this.player1.equals("Choki") && this.player2.equals("Pa")) ||
        (this.player1.equals("Pa") && this.player2.equals("Gu"))) {
      return "You Win!";
    }
    return "You Lose";
  }
}
