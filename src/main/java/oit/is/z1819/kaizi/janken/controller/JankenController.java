package oit.is.z1819.kaizi.janken.controller;

import java.security.Principal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import oit.is.z1819.kaizi.janken.model.Janken;
import oit.is.z1819.kaizi.janken.model.User;
import oit.is.z1819.kaizi.janken.model.UserMapper;
import oit.is.z1819.kaizi.janken.model.Match;
import oit.is.z1819.kaizi.janken.model.MatchMapper;

@Controller
public class JankenController {

  @Autowired
  private UserMapper usermapper;
  @Autowired
  private MatchMapper matchmapper;

  @GetMapping("/janken")
  public String janken(Principal prin, ModelMap model) {
    ArrayList<User> users = usermapper.selectAllUsers();
    model.addAttribute("users", users);
    ArrayList<Match> matches = matchmapper.selectAllMatches();
    model.addAttribute("matches", matches);
    return "janken.html";
  }

  @GetMapping("/jankengame")
  public String jankengame(@RequestParam String hand, ModelMap model) {
    Janken janken = new Janken();
    janken.setPlayer(hand);
    janken.setCpu();
    model.addAttribute("player_hand", janken.getPlayer());
    model.addAttribute("cpu_hand", janken.getCpu());
    model.addAttribute("result",janken.jankenResult());
    return "janken.html";
  }
}
