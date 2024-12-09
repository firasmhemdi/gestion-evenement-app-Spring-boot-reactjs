    package com.example.projetd.integration.repository;

    import com.example.projetd.integration.Entity.Category;
    import com.example.projetd.integration.Entity.Event;
    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.data.jpa.repository.Query;
    import org.springframework.data.repository.query.Param;

    import java.util.List;

    public interface EventRepository extends JpaRepository<Event, Long> {

        // Méthode pour rechercher des événements par catégorie
        List<Event> findByCategory(Category category);

        // Requête personnalisée pour rechercher des événements par mot-clé
        @Query("SELECT e FROM Event e WHERE e.title LIKE %:keyword% OR e.category.name LIKE %:keyword%")
        List<Event> searchByKeyword(@Param("keyword") String keyword);
    }
