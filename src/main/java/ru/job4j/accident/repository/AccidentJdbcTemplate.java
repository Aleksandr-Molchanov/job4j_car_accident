package ru.job4j.accident.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.sql.PreparedStatement;
import java.util.HashSet;
import java.util.List;

/*@Repository*/
public class AccidentJdbcTemplate {

    private final JdbcTemplate jdbc;

    private AccidentTypeJdbcTemplate accidentTypeRepository;

    private RuleJdbcTemplate ruleRepository;

    public AccidentJdbcTemplate(JdbcTemplate jdbc, AccidentTypeJdbcTemplate accidentTypeRepository, RuleJdbcTemplate ruleRepository) {
        this.jdbc = jdbc;
        this.accidentTypeRepository = accidentTypeRepository;
        this.ruleRepository = ruleRepository;
    }

    public Accident add(Accident accident) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(connection -> {
                    PreparedStatement ps = connection.prepareStatement(
                            "insert into accident (name, text, address, accident_type_id) values (?, ?, ?, ?)",
                            new String[]{"id"}
                    );
                    ps.setString(1, accident.getName());
                    ps.setString(2, accident.getText());
                    ps.setString(3, accident.getAddress());
                    ps.setInt(4, accident.getAccidentType().getId());
                    return ps;
                }, keyHolder
        );
        for (Rule rule : accident.getRules()) {
            jdbc.update("insert into accident_rule(accident_id, rule_id) values(?, ?)",
                    keyHolder.getKey(),
                    rule.getId());
        }
        return accident;
    }

    public List<Accident> getAll() {
        return jdbc.query("select ac.id, ac.name, ac.text, ac.address, ac.accident_type_id, at.id, at.name "
                        + "from accident as ac "
                        + "join accident_type as at on ac.accident_type_id = at.id order by ac.id ",
                (rs, row) -> {
                    Accident accident = new Accident();
                    accident.setId(rs.getInt("id"));
                    accident.setName(rs.getString("name"));
                    accident.setText(rs.getString("text"));
                    accident.setAddress(rs.getString("address"));
                    accident.setAccidentType(accidentTypeRepository.findById(rs.getInt("accident_type_id")));
                    accident.setRules(new HashSet<>(ruleRepository.findRuleByAccident(accident.getId())));
                    return accident;
                });
    }

    public Accident findById(int id) {
        return jdbc.queryForObject("select ac.id, ac.name, ac.text, ac.address, ac.accident_type_id, at.id, at.name "
                        + "from accident as ac "
                        + "join accident_type as at on ac.accident_type_id = at.id where ac.id = ?",
                (rs, row) -> {
                    Accident accident = new Accident();
                    accident.setId(rs.getInt("id"));
                    accident.setName(rs.getString("name"));
                    accident.setText(rs.getString("text"));
                    accident.setAddress(rs.getString("address"));
                    AccidentType accidentType = new AccidentType();
                    accidentType.setId(rs.getInt("id"));
                    accidentType.setName(rs.getString("name"));
                    accident.setAccidentType(accidentType);
                    accident.setRules(new HashSet<>(ruleRepository.findRuleByAccident(accident.getId())));
                    return accident;
                }, id);
    }

    public Accident update(Accident accident) {
        jdbc.update("update accident set name = ?, text = ?, address = ? "
                        + "where accident.id = ?",
                accident.getName(),
                accident.getText(),
                accident.getAddress(),
                accident.getId()
        );
        jdbc.update("delete from accident_rule where accident_id = ?",
                accident.getId());
        for (Rule rule : accident.getRules()) {
            jdbc.update("insert into accident_rule(accident_id, rule_id) values(?, ?)",
                    accident.getId(),
                    rule.getId());
        }
        return accident;
    }
}