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

  @GetMapping("/match")
  public String match(@RequestParam String id , ModelMap model ) {
      User opp = usermapper.selectById(id);
      model.addAttribute("opponent", opp);
      return "match.html";
  }
  
  @GetMapping("/fight")
  public String game(@RequestParam String id, @RequestParam String Player1, ModelMap model, Principal prin) {
    Match match = new Match();
    Janken janken = new Janken();
    janken.setPlayer(Player1);
    janken.setCpu();

    match.setUser1(usermapper.selectByName(prin.getName()).getId());
    match.setUser2(Integer.parseInt(id));
    match.setUser1Hand(janken.getPlayer());
    match.setUser2Hand(janken.getCpu());
    matchmapper.insertMatch(match);

    model.addAttribute("opponent", usermapper.selectById(id));
    model.addAttribute("player_hand", janken.getPlayer());
    model.addAttribute("cpu_hand", janken.getCpu());
    model.addAttribute("result", janken.jankenResult());
    return "match.html";
  }
}
