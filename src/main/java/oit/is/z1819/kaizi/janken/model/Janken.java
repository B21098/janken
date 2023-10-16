package oit.is.z1819.kaizi.janken.model;

import java.util.Random;

//@Controller
public class Janken {
  private String cpu;
  private String player;

public void setPlayer(String hand) {
    this.player = hand;
  }

  public String getPlayer() {
    return this.player;
  }

  public String getCpu() {
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
    return this.cpu;
  }

  public String determineWinner() {
    if (this.player.equals(this.cpu))
      return "Drow";
    if ((this.player.equals("Gu") && this.cpu.equals("Choki")) ||
        (this.player.equals("Choki") && this.cpu.equals("Pa")) ||
        (this.player.equals("Pa") && this.cpu.equals("Gu")))
      return "You Win!";
    return "You Lose";
  }
}
