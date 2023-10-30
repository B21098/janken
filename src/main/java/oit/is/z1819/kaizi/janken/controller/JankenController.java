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
import oit.is.z1819.kaizi.janken.model.MatchInfo;
import oit.is.z1819.kaizi.janken.model.MatchInfoMapper;
import oit.is.z1819.kaizi.janken.model.MatchMapper;

@Controller
public class JankenController {

  @Autowired
  private UserMapper usermapper;
  @Autowired
  private MatchMapper matchmapper;
  @Autowired
  private MatchInfoMapper matchinfomapper;

  @GetMapping("/janken")
  public String janken(ModelMap model) {
      ArrayList<User> users = usermapper.selectAllUsers();
      ArrayList<Match> matches = matchmapper.selectAllMatches();

      ArrayList<MatchInfo> activeMatches = matchinfomapper.selectActiveMatches();
      ArrayList<Result> results  = new ArrayList<>();

      for (Match match : matches) {
          Result result = new Result(match);
          results.add(result);
      }
      model.addAttribute("users", users);
      model.addAttribute("history", results);

      model.addAttribute("activematches", activeMatches);

      return "janken.html";
  }

  @GetMapping("/match")
  public String match(@RequestParam String id , ModelMap model ) {
      User opp = usermapper.selectById(id);
      model.addAttribute("opponent", opp);
      return "match.html";
  }

  @GetMapping("/wait")
  public String wait(@RequestParam String id, @RequestParam String hand, ModelMap model, Principal prin) {
      String user1name = prin.getName();
      int user1 = usermapper.selectByName(user1name).getId();
      int user2 = Integer.parseInt(id);

      MatchInfo matchinfo = new MatchInfo();
      matchinfo.setUser1(user1);
      matchinfo.setUser2(user2);
      matchinfo.setUser1Hand(hand);
      matchinfo.setActive(true);
      matchinfomapper.insertMatchInfo(matchinfo);

      return "wait.html";
  }
  
  @GetMapping("/fight")
  public String game(@RequestParam String id, @RequestParam String hand , ModelMap model, Principal prin) {
      Match match = new Match();
      Janken janken = new Janken();
      janken.setPlayer1(hand);

      match.setUser1(usermapper.selectByName(prin.getName()).getId());
      match.setUser2(Integer.parseInt(id));
      match.setUser1Hand(janken.getPlayer1());
      match.setUser2Hand(janken.getPlayer2());
      match.setisActive(false);
      matchmapper.insertMatch(match);

      model.addAttribute("opponent", usermapper.selectById(id));
      model.addAttribute("player1", janken.getPlayer1());
      model.addAttribute("player2", janken.getPlayer2());
      model.addAttribute("result", janken.jankenResult());

      return "match.html";
  }
}

/**
 * じゃんけんの結果を格納するクラス
 */
class Result extends Match {
    private String result;
    public Result(Match match) {
        super.setId(match.getId());
        super.setUser1(match.getUser1());
        super.setUser2(match.getUser2());
        super.setUser1Hand(match.getUser1Hand());
        super.setUser2Hand(match.getUser2Hand());
        Janken game = new Janken();
        game.setPlayer1(match.getUser1Hand());
        game.setPlayer2(match.getUser2Hand());
        if (game.jankenResult() == null) {
            this.setResult("引き分け");
        } else {
            this.setResult(game.jankenResult() + "の勝利");
        }
    }
    public void setResult(String result) { this.result = result; }
    public String getResult() { return this.result; }
}