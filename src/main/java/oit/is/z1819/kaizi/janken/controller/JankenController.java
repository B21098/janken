package oit.is.z1819.kaizi.janken.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import oit.is.z1819.kaizi.janken.model.Janken;
import oit.is.z1819.kaizi.janken.model.Entry;

@RequestMapping("/janken")
@Controller
public class JankenController {

  @Autowired
  private Entry entry;

  @GetMapping
  public String janken(ModelMap model, Principal prin) {
    String loginUser = prin.getName(); // ログインユーザ情報
    this.entry.addUser(loginUser);
    model.addAttribute("login_user", loginUser);
    model.addAttribute("entry", this.entry);
    return "janken.html";
  }

  @GetMapping("/jankengame")
  public String jankengame(@RequestParam String hand, ModelMap model, Principal prin) {
    Janken janken = new Janken();
    janken.setPlayer(hand);

    String result = janken.determineWinner();
    model.addAttribute("users", this.entry.getUsers());
    model.addAttribute("num_users", this.entry.getNumUsers());
    model.addAttribute("player_hand", janken.getPlayer());
    model.addAttribute("cpu_hand", janken.getCpu());
    model.addAttribute("result",result);

    return "janken.html";
  }
}
