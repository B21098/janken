package oit.is.z1819.kaizi.janken.model;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

//import java.util.Random;

@Controller
@Component
public class Janken {

  /*
   * public String cpuChoices() {
   * // CPUの手をランダムに選ぶ
   * String[] cpuChoices = { "Gu", "Cho", "Pa" };
   * Random random = new Random();
   * int cpuIndex = random.nextInt(cpuChoices.length);
   * String cpuChoice = cpuChoices[cpuIndex];
   * return cpuChoice;
   * }
   */

  public String determineWinner(String playerChoice, String cpuChoice) {
    if (playerChoice.equals(cpuChoice)) {
      return "Drow";
    } else if ((playerChoice.equals("Gu") && cpuChoice.equals("Cho")) ||
        (playerChoice.equals("Cho") && cpuChoice.equals("Pa")) ||
        (playerChoice.equals("Pa") && cpuChoice.equals("Gu"))) {
      return "You Win!";
    } else {
      return "You Lose";
    }
  }
}
