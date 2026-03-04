package datasource;

import jakarta.persistence.*;

public class MariaDbJpaConnection {

    private static EntityManagerFactory emf;
    private static EntityManager em;

    public static EntityManager getInstance() {
        if (em == null) {
            if (emf == null) {
                emf = Persistence.createEntityManagerFactory("CurrencyUnit");
            }
            em = emf.createEntityManager();
        }
        return em;
    }
}