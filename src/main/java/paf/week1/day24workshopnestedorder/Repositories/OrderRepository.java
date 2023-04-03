package paf.week1.day24workshopnestedorder.Repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepository extends BasicCrud{
    @Autowired
    JdbcTemplate template;
}
