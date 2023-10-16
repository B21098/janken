package oit.is.z1819.kaizi.janken.controller;

import java.security.Principal;

import oit.is.z1819.kaizi.janken.model.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import oit.is.z1819.kaizi.janken.model.Janken;

@Controller
@RequestMapping("/janken")
public class JankenController {

  @Autowired
  private Entry entry;

  @GetMapping
  public String janken(ModelMap model, Principal prin) {
    String loginUser = prin.getName(); // ログインユーザ情報
    this.entry.addUser(loginUser);
    model.addAttribute("name", this.entry);
    return "janken.html";
  }

  /*/
  @PostMapping
  public String janken(@RequestParam String name, ModelMap model) {
    model.addAttribute("name", name);
    return "janken.html";
  }
  */

  @GetMapping("Gu")
  public String Gu(Model model) {
    String playerChoice = "Gu";
    String cpuChoice = "Gu";
    Janken janken = new Janken();
    String result = janken.determineWinner(playerChoice, cpuChoice);
    model.addAttribute("playerChoice", playerChoice);
    model.addAttribute("cpuChoice", cpuChoice);
    model.addAttribute("result", result);
    return "janken.html";
  }

  @GetMapping("Cho")
  public String Cho(Model model) {
    String playerChoice = "Cho";
    String cpuChoice = "Gu";
    Janken janken = new Janken();
    String result = janken.determineWinner(playerChoice, cpuChoice);
    model.addAttribute("playerChoice", playerChoice);
    model.addAttribute("cpuChoice", cpuChoice);
    model.addAttribute("result", result);
    return "janken.html";
  }

  @GetMapping("Pa")
  public String Pa(Model model) {
    String playerChoice = "Pa";
    String cpuChoice = "Gu";
    Janken janken = new Janken();
    String result = janken.determineWinner(playerChoice, cpuChoice);
    model.addAttribute("playerChoice", playerChoice);
    model.addAttribute("cpuChoice", cpuChoice);
    model.addAttribute("result", result);
    return "janken.html";
  }

}